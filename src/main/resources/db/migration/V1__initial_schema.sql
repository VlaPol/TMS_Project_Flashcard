CREATE TABLE topic
(
    topic_id    BIGSERIAL PRIMARY KEY,
    topic_title VARCHAR(200) NOT NULL,
);
CREATE TABLE subtopic
(
    subtopic_id    BIGSERIAL PRIMARY KEY,
    subtopic_title VARCHAR(200) NOT NULL,
    topic_id       BIGINT       NOT NULL REFERENCES TOPIC (topic_id)
);
CREATE TABLE quiz
(
    quiz_id       BIGSERIAL PRIMARY KEY,
    question      VARCHAR(400) NOT NULL,
    answer        VARCHAR(400) NOT NULL,
    is_remembered BOOLEAN      NOT NULL,
    subtopic_id   BIGINT       NOT NULL REFERENCES SUBTOPIC (subtopic_id)
);

alter table quiz
drop
column isremembered;

alter table quiz
    add column is_remembered boolean not null default false;