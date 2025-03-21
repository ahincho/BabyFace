-- V1 - Create Users Table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(32) NOT NULL,
    phone VARCHAR(9) NOT NULL UNIQUE CHECK (phone ~ '^[0-9]{9}$'),
    photo VARCHAR(256) NULL,
    avatar VARCHAR(256) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE INDEX idx_users_phone ON users(phone);
