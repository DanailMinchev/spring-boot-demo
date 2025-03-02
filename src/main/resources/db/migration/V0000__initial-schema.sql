CREATE TABLE IF NOT EXISTS posts
(
    id      BIGSERIAL PRIMARY KEY,
    title   VARCHAR(255),
    body    TEXT
);
