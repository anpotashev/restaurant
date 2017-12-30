DELETE FROM dish;
DELETE FROM restaurant;
DELETE FROM price;
DELETE FROM vote;
DELETE FROM user_roles;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;
--100000
INSERT INTO dish (name) VALUES
  ('блюдо1'),
  ('блюдо2'),
  ('блюдо3'),
  ('Блюдо4');
--100004
INSERT INTO restaurant (name) VALUES
  ('Ресторан1'),
  ('Ресторан2'),
  ('Ресторан3'),
  ('ресторан4');
--100008
INSERT INTO users (username, password) VALUES
  ('User', '$2a$10$WFjLUAwPEQy30ftAmkrl8.G/b/KFTiKwFujU8nJbwImfFP5BKkUt2'),
  ('Admin', '$2a$10$WFjLUAwPEQy30ftAmkrl8.G/b/KFTiKwFujU8nJbwImfFP5BKkUt2'),
  ('UserAdmin', '$2a$10$WFjLUAwPEQy30ftAmkrl8.G/b/KFTiKwFujU8nJbwImfFP5BKkUt2'),
  ('User2', '$2a$10$WFjLUAwPEQy30ftAmkrl8.G/b/KFTiKwFujU8nJbwImfFP5BKkUt2'),
  ('User3', '$2a$10$WFjLUAwPEQy30ftAmkrl8.G/b/KFTiKwFujU8nJbwImfFP5BKkUt2');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100008),
  ('ROLE_ADMIN', 100009),
  ('ROLE_USER', 100010),
  ('ROLE_ADMIN', 100010);
INSERT INTO price (restaurant_id, dish_id, date, price) VALUES
  (100004, 100000, 'yesterday', 10.00),
  (100004, 100001, 'yesterday', 11.00),
  (100004, 100002, 'yesterday', 12.00),

  (100004, 100001, 'today', 10.11),
  (100004, 100002, 'today', 10.12),
  (100004, 100003, 'today', 10.13),

  (100005, 100000, 'yesterday', 10.00),
  (100005, 100003, 'yesterday', 11.00),

  (100005, 100000, 'today', 12.00),
  (100005, 100001, 'today', 14.00),
  (100005, 100003, 'today', 16.00);

INSERT INTO vote (date, user_id, restaurant_id) VALUES
  ('today', 100008, 100004),
  ('today', 100010, 100005),
  ('today', 100011, 100004);

INSERT INTO vote (date, user_id, restaurant_id) VALUES
  ('yesterday', 100008, 100004),
  ('yesterday', 100010, 100005);