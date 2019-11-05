CREATE TABLE courts(
    id SERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    description VARCHAR(255) DEFAULT NULL,
    lat NUMERIC(11, 6) NOT NULL,
    lng NUMERIC(11, 6) NOT NULL,
    is_enabled BOOLEAN NOT NULL,
    is_new BOOLEAN NOT NULL,
    renovation_year INTEGER DEFAULT NULL,
    conditions VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
);
