DROP TABLE IF EXISTS dish CASCADE;
DROP TABLE IF EXISTS price CASCADE;
DROP TABLE IF EXISTS restaurant CASCADE;
DROP TABLE IF EXISTS user_roles CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS vote CASCADE;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE dish (
  id      INT4         NOT NULL DEFAULT nextval('global_seq'),
  name    VARCHAR(255) NOT NULL,
  deleted BOOLEAN      NOT NULL DEFAULT FALSE,
  PRIMARY KEY (id)
);


CREATE TABLE restaurant (
  id      INT4         NOT NULL DEFAULT nextval('global_seq'),
  name    VARCHAR(255) NOT NULL,
  deleted BOOLEAN      NOT NULL DEFAULT FALSE,
  PRIMARY KEY (id)
);

CREATE TABLE users (
  id       INT4              NOT NULL DEFAULT nextval('global_seq'),
  username VARCHAR(255),
  password VARCHAR(60)       NOT NULL,
  PRIMARY KEY (id)
);
CREATE UNIQUE INDEX users_unique_username_idx
  ON users (lower(username));

CREATE TABLE user_roles (
  user_id INT4         NOT NULL,
  role    VARCHAR(255) NOT NULL,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE vote (
  date          DATE NOT NULL,
  user_id       INT4 NOT NULL,
  restaurant_id INT4 NOT NULL,
  PRIMARY KEY (date, user_id),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE price (
  id            INT4           NOT NULL DEFAULT nextval('global_seq'),
  date          DATE           NOT NULL,
  price         NUMERIC(19, 2) NOT NULL,
  restaurant_id INT4           NOT NULL,
  dish_id       INT4           NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (dish_id) REFERENCES dish (id) ON DELETE CASCADE,
  FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX price_restaurant_id_dish_id_date_idx
  ON price (dish_id, restaurant_id, date)
