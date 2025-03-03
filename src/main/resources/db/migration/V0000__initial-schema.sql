CREATE TABLE IF NOT EXISTS posts
(
    id    BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    body  TEXT         NOT NULL
);

CREATE TABLE IF NOT EXISTS post_comments
(
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    email   VARCHAR(255) NOT NULL,
    body    TEXT         NOT NULL,
    post_id BIGINT
);

ALTER TABLE post_comments
    ADD CONSTRAINT FK_POST_COMMENTS_ON_POST FOREIGN KEY (post_id) REFERENCES posts (id);
