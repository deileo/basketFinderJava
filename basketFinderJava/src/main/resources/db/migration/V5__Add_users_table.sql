CREATE TABLE users (
    id SERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) DEFAULT NULL,
    image_url VARCHAR(255) DEFAULT NULL,
    email_verified BOOLEAN NOT NULL,
    provider VARCHAR(255) DEFAULT NULL,
    provider_id VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
);