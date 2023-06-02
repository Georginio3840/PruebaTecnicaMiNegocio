CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    dni_customer VARCHAR(255)
);

CREATE TABLE address (
    id SERIAL PRIMARY KEY,
    address VARCHAR(255),
    is_main BOOLEAN,
    customer_id INTEGER,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

ALTER TABLE customer
ADD COLUMN main_address_id INTEGER,
ADD FOREIGN KEY (main_address_id) REFERENCES address(id);
