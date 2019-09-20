CREATE TABLE comments (
    id INT(11) AUTO_INCREMENT NOT NULL,
    comment VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    deleted_at DATETIME DEFAULT NULL,
    event_id INT DEFAULT NULL,
    court_id INT DEFAULT NULL,
    created_by INT NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE comments ADD CONSTRAINT fk_comment_event FOREIGN KEY (event_id) REFERENCES events(id);
ALTER TABLE comments ADD CONSTRAINT fk_comment_court FOREIGN KEY (court_id) REFERENCES courts(id);
ALTER TABLE comments ADD CONSTRAINT fk_comment_created_by FOREIGN KEY (created_by) REFERENCES users(id);