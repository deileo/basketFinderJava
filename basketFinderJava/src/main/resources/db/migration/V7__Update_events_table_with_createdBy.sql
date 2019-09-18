ALTER TABLE events ADD created_by INT(11) NOT NULL;

ALTER TABLE events ADD CONSTRAINT fk_event_created_by FOREIGN KEY (created_by) REFERENCES users(id);