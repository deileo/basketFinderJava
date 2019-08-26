CREATE TABLE courts (
    id INT(11) AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    description VARCHAR(255) DEFAULT NULL,
    lat DOUBLE(11, 6) NOT NULL,
    lng DOUBLE(11, 6) NOT NULL,
    is_enabled TINYINT(1) DEFAULT 0 NOT NULL,
    is_new TINYINT(1) DEFAULT 0 NOT NULL,
    renovation_year INT(11) DEFAULT NULL,
    conditions VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;