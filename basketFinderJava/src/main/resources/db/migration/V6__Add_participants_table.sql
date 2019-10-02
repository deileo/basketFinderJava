CREATE TABLE event_participants (
    id SERIAL NOT NULL,
    user_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
    event_id INTEGER REFERENCES events(id) ON DELETE CASCADE,
    PRIMARY KEY(id)
);