INSERT INTO topic (topic_title)
VALUES ('ENGLISH');
INSERT INTO topic (topic_title)
VALUES ('PROGRAMMING');
INSERT INTO topic (topic_title)
VALUES ('CHEMISTRY');

INSERT INTO quiz (question, answer, is_remembered, topic_id)
VALUES ('RED', 'КРАСНЫЙ', false, 1);
INSERT INTO quiz (question, answer, is_remembered, topic_id)
VALUES ('BLACK', 'ЧЕРНЫЙ', false, 1);
INSERT INTO quiz (question, answer, is_remembered, topic_id)
VALUES ('YELLOW', 'ЖЕЛТЫЙ', false, 1);

INSERT INTO quiz (question, answer, is_remembered, topic_id)
VALUES ('DOG', 'СОБАКА', true, 1);
INSERT INTO quiz (question, answer, is_remembered, topic_id)
VALUES ('CAT', 'КОТ', true, 1);
INSERT INTO quiz (question, answer, is_remembered, topic_id)
VALUES ('HORSE', 'ЛОШАДЬ', false, 1);

INSERT INTO quiz (question, answer, is_remembered, topic_id)
VALUES ('TOMATO', 'ТОМАТ', false, 1);
INSERT INTO quiz (question, answer, is_remembered, topic_id)
VALUES ('POTATO', 'КАРТОФЕЛЬ', false, 1);
INSERT INTO quiz (question, answer, is_remembered, topic_id)
VALUES ('PEPPER', 'ПЕРЕЦ', false, 1);

INSERT INTO quiz (question, answer, is_remembered, topic_id)
VALUES ('ARRAY', 'МАССИВ', false, 2);
INSERT INTO quiz (question, answer, is_remembered, topic_id)
VALUES ('LIST', 'ЛИСТ', false, 2);
INSERT INTO quiz (question, answer, is_remembered, topic_id)
VALUES ('ABSTRACTION', 'АБСТРАКЦИЯ', false, 2);

INSERT INTO quiz (question, answer, is_remembered, topic_id)
VALUES ('REFERENCE', 'ССЫЛКА', false, 2);
INSERT INTO quiz (question, answer, is_remembered, topic_id)
VALUES ('STRUCT', 'СТРУКТУРА', false, 2);
INSERT INTO quiz (question, answer, is_remembered, topic_id)
VALUES ('EOF', 'КОНЕЦ ФАЙЛА', false, 2);

INSERT INTO quiz (question, answer, is_remembered, topic_id)
VALUES ('С2H5OH', 'СПИРТ', false, 3);
INSERT INTO quiz (question, answer, is_remembered, topic_id)
VALUES ('H2O', 'ВОДА', false, 3);

INSERT INTO quiz (question, answer, is_remembered, topic_id)
VALUES ('H2', 'ВОДОРОД', false, 3);
INSERT INTO quiz (question, answer, is_remembered, topic_id)
VALUES ('Au', 'ЗОЛОТО', false, 3);
INSERT INTO quiz (question, answer, is_remembered, topic_id)
VALUES ('Na', 'НАТРИЙ', false, 3);
INSERT INTO quiz (question, answer, is_remembered, topic_id)
VALUES ('Zn', 'ЦИНК', false, 3);

-- Список тем
SELECT t.topic_id    AS id,
       t.topic_title AS title
FROM topic as t;

-- Список тем со счётчиками
SELECT t.topic_id                                      AS id,
       t.topic_title                                   AS title,
       count(q.topic_id) FILTER ( WHERE q.is_remembered ) AS learned_count,
       count(q.topic_id)                                    AS total_count
FROM topic t
            LEFT JOIN quiz as q ON t.topic_id = q.topic_id
GROUP BY t.topic_id;

-- Добавить тему
INSERT INTO topic (topic_title)
VALUES (?);

-- Получить все квизы
SELECT q.topic_id      AS id,
       q.question      AS question,
       q.answer        AS answer,
       q.is_remembered AS remembered
FROM quiz q
WHERE q.topic_id = ?;

-- Удаление темы
DELETE
FROM topic t
WHERE t.topic_id = ?;

-- Новое задание
INSERT INTO quiz (topic_id, question, answer, is_remembered)
VALUES (?, ?, ?, ?);

-- Удаление задания
DELETE
FROM quiz q
WHERE q.quiz_id = 4;

-- Проверка знания 1

SELECT q.quiz_id       AS id,
       q.question AS question,
       q.answer   AS answer,
       q.is_remembered  AS remembered
FROM quiz q
WHERE q.quiz_id = ?
  AND NOT q.is_remembered
ORDER BY q.quiz_id
LIMIT 1 OFFSET ?;

-- изменение состояния вопроса
UPDATE quiz q
SET is_remembered = TRUE
WHERE q.quiz_id = 14;

