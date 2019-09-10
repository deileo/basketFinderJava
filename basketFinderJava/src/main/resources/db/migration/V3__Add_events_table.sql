CREATE TABLE events (
    id INT(11) AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    needed_players INT NOT NULL DEFAULT '0',
    description VARCHAR(255) DEFAULT NULL,
    price DOUBLE(11, 2) DEFAULT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME DEFAULT NULL,
    court_id INT NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE events ADD CONSTRAINT fk_court_events FOREIGN KEY (court_id) REFERENCES courts(id);