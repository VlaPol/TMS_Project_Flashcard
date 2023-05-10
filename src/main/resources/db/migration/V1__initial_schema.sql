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
    is_remembered BOOLEAN NOT NULL,
    subtopic_id   BIGINT       NOT NULL REFERENCES SUBTOPIC (subtopic_id)
);

INSERT INTO topic (topic_title)
VALUES ('ENGLISH');
INSERT INTO topic (topic_title)
VALUES ('PROGRAMMING');
INSERT INTO topic (topic_title)
VALUES ('CHEMISTRY');

INSERT INTO subtopic (subtopic_title, topic_id)
VALUES ('COLOR', 1);
INSERT INTO subtopic (subtopic_title, topic_id)
VALUES ('ANIMAL', 1);
INSERT INTO subtopic (subtopic_title, topic_id)
VALUES ('VEGETABLES', 1);

INSERT INTO subtopic (subtopic_title, topic_id)
VALUES ('JAVA', 2);
INSERT INTO subtopic (subtopic_title, topic_id)
VALUES ('C', 2);
INSERT INTO subtopic (subtopic_title, topic_id)
VALUES ('PYTHON', 2);

INSERT INTO subtopic (subtopic_title, topic_id)
VALUES ('ORGANIC', 3);
INSERT INTO subtopic (subtopic_title, topic_id)
VALUES ('INORGANIC', 3);

INSERT INTO quiz (question, answer, is_remembered, subtopic_id)
VALUES ('RED', 'КРАСНЫЙ', false, 1);
INSERT INTO quiz (question, answer, is_remembered, subtopic_id)
VALUES ('BLACK', 'ЧЕРНЫЙ', false, 1);
INSERT INTO quiz (question, answer, is_remembered, subtopic_id)
VALUES ('YELLOW', 'ЖЕЛТЫЙ', false, 1);

INSERT INTO quiz (question, answer, is_remembered, subtopic_id)
VALUES ('DOG', 'СОБАКА', true, 2);
INSERT INTO quiz (question, answer, is_remembered, subtopic_id)
VALUES ('CAT', 'КОТ', true, 2);
INSERT INTO quiz (question, answer, is_remembered, subtopic_id)
VALUES ('HORSE', 'ЛОШАДЬ', false, 2);

INSERT INTO quiz (question, answer, is_remembered, subtopic_id)
VALUES ('TOMATO', 'ТОМАТ', false, 3);
INSERT INTO quiz (question, answer, is_remembered, subtopic_id)
VALUES ('POTATO', 'КАРТОФЕЛЬ', false, 3);
INSERT INTO quiz (question, answer, is_remembered, subtopic_id)
VALUES ('PEPPER', 'ПЕРЕЦ', false, 3);

INSERT INTO quiz (question, answer, is_remembered, subtopic_id)
VALUES ('ARRAY', 'МАССИВ', false, 4);
INSERT INTO quiz (question, answer, is_remembered, subtopic_id)
VALUES ('LIST', 'ЛИСТ', false, 4);
INSERT INTO quiz (question, answer, is_remembered, subtopic_id)
VALUES ('ABSTRACTION', 'АБСТРАКЦИЯ', false, 4);

INSERT INTO quiz (question, answer, is_remembered, subtopic_id)
VALUES ('REFERENCE', 'ССЫЛКА', false, 5);
INSERT INTO quiz (question, answer, is_remembered, subtopic_id)
VALUES ('STRUCT', 'СТРУКТУРА', false, 5);
INSERT INTO quiz (question, answer, is_remembered, subtopic_id)
VALUES ('EOF', 'КОНЕЦ ФАЙЛА', false, 5);

INSERT INTO quiz (question, answer, is_remembered, subtopic_id)
VALUES ('С2H5OH', 'СПИРТ', false, 7);
INSERT INTO quiz (question, answer, is_remembered, subtopic_id)
VALUES ('H2O', 'ВОДА', false, 7);

INSERT INTO quiz (question, answer, is_remembered, subtopic_id)
VALUES ('H2', 'ВОДОРОД', false, 8);
INSERT INTO quiz (question, answer, is_remembered, subtopic_id)
VALUES ('Au', 'ЗОЛОТО', false, 8);
INSERT INTO quiz (question, answer, is_remembered, subtopic_id)
VALUES ('Na', 'НАТРИЙ', false, 8);
INSERT INTO quiz (question, answer, is_remembered, subtopic_id)
VALUES ('Zn', 'ЦИНК', false, 8);


SELECT *
FROM topic;

SELECT *
FROM subtopic
WHERE topic_id = 1;

SELECT *
FROM quiz q
WHERE q.is_remembered = 1
SELECT COUNT(*)
FROM quiz
WHERE is_remembered = 1;

SELECT t.topic_title, st.subtopic_title, COUNT(q.question) AS total_questions
FROM topic t
         LEFT JOIN subtopic st on t.topic_id = st.topic_id
         LEFT JOIN quiz Q on st.subtopic_id = Q.subtopic_id
GROUP BY t.topic_title, st.subtopic_title
ORDER BY topic_title;

SELECT t.topic_title, st.subtopic_title, COUNT(q.is_remembered) AS ready
FROM topic t
         LEFT JOIN subtopic st on t.topic_id = st.topic_id
         LEFT JOIN quiz Q on st.subtopic_id = Q.subtopic_id
WHERE q.is_remembered = 'true'
GROUP BY t.topic_title, st.subtopic_title
ORDER BY topic_title;

alter table quiz
drop column isremembered;

alter table quiz
    add column is_remembered boolean not null default false;