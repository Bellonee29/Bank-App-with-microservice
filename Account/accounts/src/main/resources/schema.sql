CREATE TABLE customer (
                customer_id SERIAL PRIMARY KEY,
                name VARCHAR(255) NOT NULL,
                email VARCHAR(255),
                phone_number VARCHAR(20),
                created_at TIMESTAMP,
                created_by VARCHAR(255),
               updated_at TIMESTAMP,
               updated_by VARCHAR(255)
);

CREATE TABLE accounts (
                customer_id BIGINT,
                account_number BIGINT PRIMARY KEY,
                account_type VARCHAR(255),
                branch_address VARCHAR(255),
                created_at TIMESTAMP,
                created_by VARCHAR(255),
               updated_at TIMESTAMP,
               updated_by VARCHAR(255)
);
