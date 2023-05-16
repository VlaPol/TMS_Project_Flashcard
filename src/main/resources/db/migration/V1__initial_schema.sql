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
    is_remembered BOOLEAN      NOT NULL,
    topic_id      BIGINT       NOT NULL REFERENCES topic (subtopic_id) ON DELETE CASCADE
);

alter table quiz
drop
column isremembered;

alter table quiz
    add column is_remembered boolean not null default false;