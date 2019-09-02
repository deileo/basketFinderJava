ALTER TABLE courts
       ADD created_at DATETIME NOT NULL,
       ADD updated_at DATETIME DEFAULT NULL,
       ADD deleted_at DATETIME DEFAULT NULL;

ALTER TABLE events
    ADD created_at DATETIME NOT NULL,
    ADD updated_at DATETIME DEFAULT NULL,
    ADD deleted_at DATETIME DEFAULT NULL;