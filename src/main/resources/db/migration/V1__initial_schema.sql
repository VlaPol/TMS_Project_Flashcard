CREATE TABLE topic
(
    topic_id    BIGSERIAL PRIMARY KEY,
    topic_title VARCHAR(200) NOT NULL,
);
CREATE TABLE quiz
(
    quiz_id       BIGSERIAL PRIMARY KEY,
    question      VARCHAR(400) NOT NULL,
    answer        VARCHAR(400) NOT NULL,
    is_remembered BOOLEAN      NOT NULL DEFAULT FALSE,
    topic_id      BIGINT       NOT NULL REFERENCES topic (subtopic_id) ON DELETE CASCADE
);