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
INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin'),
  ('UserAdmin', 'useradmin@hotmail.com', 'useradmin_pwd');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100008),
  ('ROLE_ADMIN', 100009),
  ('ROLE_USER', 100010),
  ('ROLE_ADMIN', 100010);
INSERT INTO price (restaurant_id, dish_id, start_date, price) VALUES
  (100005, 100000, '2017-11-09', 10.00),
  (100005, 100001, '2017-11-09', 11.00),
  (100005, 100002, '2017-11-09', 12.00),

  (100005, 100001, '2017-11-10', 10.11),
  (100005, 100002, '2017-11-10', 10.12),
  (100005, 100003, '2017-11-10', 10.13),

  (100006, 100000, '2017-11-09', 10.00),
  (100006, 100003, '2017-11-09', 11.00),

  (100006, 100000, '2017-11-10', 12.00),
  (100006, 100001, '2017-11-10', 14.00),
  (100006, 100003, '2017-11-10', 16.00);
