CREATE TABLE event_participants (
    id INT AUTO_INCREMENT NOT NULL,
    user_id INT DEFAULT NULL,
    event_id INT DEFAULT NULL,
    INDEX IDX_event_participants_user_id (user_id),
    INDEX IDX_event_participants_event_id (event_id),
    PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;