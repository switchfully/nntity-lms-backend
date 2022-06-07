CREATE TABLE course
(
    ID   BIGINT PRIMARY KEY,
    NAME VARCHAR(100)
);

CREATE SEQUENCE course_seq START WITH 1 INCREMENT BY 1;

ALTER TABLE codelab
    ADD FK_COURSE_ID BIGINT;

ALTER TABLE codelab
    ADD CONSTRAINT FK_COURSE_ID FOREIGN KEY (FK_COURSE_ID) REFERENCES course (ID);

INSERT INTO course (ID, NAME)
VALUES (nextval('course_seq'), 'Composition');

INSERT INTO course (ID, NAME)
VALUES (nextval('course_seq'), 'Polymorphism');

UPDATE codelab
SET FK_COURSE_ID = 1
WHERE ID = 1;

UPDATE codelab
SET FK_COURSE_ID = 2
WHERE ID = 2;

UPDATE codelab
SET FK_COURSE_ID = 2
WHERE ID = 3;

ALTER TABLE codelab
    ALTER COLUMN FK_COURSE_ID SET NOT NULL;