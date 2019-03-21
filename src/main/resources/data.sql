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