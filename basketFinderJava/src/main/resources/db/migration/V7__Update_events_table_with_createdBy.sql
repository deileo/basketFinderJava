ALTER TABLE events ADD created_by INTEGER NOT NULL;

ALTER TABLE events ADD CONSTRAINT fk_event_created_by FOREIGN KEY (created_by) REFERENCES users(id);