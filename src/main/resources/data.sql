INSERT INTO role (name)
VALUES
  ('ROLE_MC'),
  ('ROLE_MM'),
  ('ROLE_S'),
  ('ROLE_ADMIN');

INSERT INTO supplier (name, status, lead_time)
VALUES
  ('Supplier A', 'CLEAR', 24),
  ('Supplier B', 'BLOCKED', 48),
  ('Supplier C', 'PENDING', 36);

INSERT INTO supplier_contact (supplier_id, phone, address, email)
VALUES
(1, '031', 'NGB', 'supplier_a@gmail.com'),
(2, '011', 'KND', 'supplier_b@gmail.com'),
(3, '091', 'CMB', 'supplier_c@gmail.com');

INSERT INTO item (supplier_id, name, price, quantity, description)
VALUES
(1, 'Seat', 250, 22, 'Mountain Bicycle Seat'),
(1, 'Handle', 500, 30, 'Mountain Bicycle Handle'),
(2, 'Tyre', 650, 35, 'Mountain Bicycle Tyre'),
(3, 'Brake', 400, 50, 'Mountain Bicycle Brake');
