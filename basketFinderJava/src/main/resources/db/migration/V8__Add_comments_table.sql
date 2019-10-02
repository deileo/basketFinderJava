CREATE TABLE comments (
    id SERIAL NOT NULL,
    comment VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    deleted_at TIMESTAMP DEFAULT NULL,
    event_id INTEGER DEFAULT NULL,
    court_id INTEGER DEFAULT NULL,
    created_by INTEGER NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE comments ADD CONSTRAINT fk_comment_event FOREIGN KEY (event_id) REFERENCES events(id);
ALTER TABLE comments ADD CONSTRAINT fk_comment_court FOREIGN KEY (court_id) REFERENCES courts(id);
ALTER TABLE comments ADD CONSTRAINT fk_comment_created_by FOREIGN KEY (created_by) REFERENCES users(id);