INSERT INTO role (name)
VALUES
  ('ROLE_MC'),
  ('ROLE_MM'),
  ('ROLE_SUPPLIER'),
  ('ROLE_EXTERNAL'),
  ('ROLE_ADMIN');

INSERT INTO supplier (name, status, lead_time, performance)
VALUES
  ('Supplier A', 'CLEAR', 24, 'Good'),
  ('Supplier B', 'BLOCKED', 48, 'Average'),
  ('Supplier C', 'PENDING', 36, 'Poor');

INSERT INTO supplier_contact (supplier_id, phone, email, location_main, location_delivery, location_collection)
VALUES
(1, '0311234567', 'supplier_a@gmail.com', '123 York Street Negombo', '123 Street Negombo', '123 York Negombo'),
(2, '0111234567', 'supplier_b@gmail.com', '456 Marine Drive Kandy', '456 Drive Kandy', '456 Marine Kandy'),
(3, '0911234567', 'supplier_c@gmail.com', '789 Alfred Road Colombo', '789 Road Colombo', '789 Alfred Colombo');

INSERT INTO item_raw (supplier_id, name, price, quantity, description)
VALUES
  (1, 'Seat', 250, 20, 'Mountain Bicycle Seat'),
  (1, 'Handle', 500, 100, 'Mountain Bicycle Handle'),
  (2, 'Tyre', 650, 20, 'Mountain Bicycle Tyre'),
  (2, 'Pedal', 650, 20, 'Mountain Bicycle Pedal'),
  (3, 'Brake', 400, 150, 'Mountain Bicycle Brake');

INSERT INTO item_raw_reorder (item_raw_id, level, quantity)
VALUES
  (1, 50, 150),
  (2, 50, 150),
  (3, 50, 150),
  (4, 50, 150),
  (5, 50, 150);

INSERT INTO item_complete (name, price, quantity, description)
VALUES
  ('Mountain Bicycle', 10000, 80, 'Mountain Bicycle Blue'),
  ('Ladies Bicycle', 8000, 30, 'Ladies Bicycle Green');

INSERT INTO item_complete_reorder (item_complete_id, level, quantity)
VALUES
  (1, 50, 100),
  (2, 50, 100);


INSERT INTO supplier_order (supplier_id, created_date, status)
VALUES
  (1, '2019-04-14 11:42:50', 'SENT'),
  (3, '2019-04-14 12:42:50', 'RECEIVED');

INSERT INTO supplier_order_item (supplier_order_id, item_raw_id, quantity)
VALUES
  (1, 1, 150),
  (1, 2, 150),
  (2, 4, 150);

