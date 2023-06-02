INSERT INTO customer (name, dni_customer) VALUES ('Customer 1', '1234567890');
INSERT INTO customer (name, dni_customer) VALUES ('Customer 2', '2345678901');
INSERT INTO customer (name, dni_customer) VALUES ('Customer 3', '3456789012');

INSERT INTO address (address, is_main, customer_id) VALUES ('Street 1, City 1', true, 1);
INSERT INTO address (address, is_main, customer_id) VALUES ('Street 2, City 2', false, 1);
INSERT INTO address (address, is_main, customer_id) VALUES ('Street 3, City 3', true, 2);
INSERT INTO address (address, is_main, customer_id) VALUES ('Street 4, City 4', true, 3);
INSERT INTO address (address, is_main, customer_id) VALUES ('Street 5, City 5', false, 3);

UPDATE customer SET main_address_id = 1 WHERE id = 1;
UPDATE customer SET main_address_id = 3 WHERE id = 2;
UPDATE customer SET main_address_id = 4 WHERE id = 3;
