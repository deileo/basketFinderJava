CREATE TABLE events (
    id SERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
    needed_players INTEGER NOT NULL DEFAULT '0',
    description VARCHAR(255) DEFAULT NULL,
    price NUMERIC(11, 2) DEFAULT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP DEFAULT NULL,
    court_id INTEGER NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE events ADD CONSTRAINT fk_court_events FOREIGN KEY (court_id) REFERENCES courts(id);