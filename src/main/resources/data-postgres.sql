INSERT INTO lms_user (ID, DISPLAY_NAME, EMAIL, PASSWORD)
VALUES ('bd39d3fc-d101-4865-aa2e-bac55a5d4321', 'Bob The Builder', 'bob@tity.com', '123456');

INSERT INTO course (ID, NAME)
VALUES (1, 'Composition');

INSERT INTO course (ID, NAME)
VALUES (2, 'Polymorphism');

INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (1, 'Codelab01', 1);

INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (2, 'Codelab02', 2);

INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (3, 'Codelab03', 2);

INSERT INTO STUDENT_CODELAB (ID, FK_STUDENT_ID, FK_CODELAB_ID, PROGRESS, COMMENT)
VALUES (1,
        'bd39d3fc-d101-4865-aa2e-bac55a5d4321',
        1,
        'DONE',
        'Comment for codelab 1');

INSERT INTO STUDENT_CODELAB (ID, FK_STUDENT_ID, FK_CODELAB_ID, PROGRESS, COMMENT)
VALUES (2,
        'bd39d3fc-d101-4865-aa2e-bac55a5d4321',
        2,
        'BUSY',
        'Comment for codelab 2');

INSERT INTO STUDENT_CODELAB (ID, FK_STUDENT_ID, FK_CODELAB_ID, PROGRESS, COMMENT)
VALUES (3,
        'bd39d3fc-d101-4865-aa2e-bac55a5d4321',
        3,
        'STUCK',
        'Comment for codelab 3');

UPDATE LMS_USER
SET ROLE = 'STUDENT'
WHERE ID = 'bd39d3fc-d101-4865-aa2e-bac55a5d4321';

INSERT INTO lms_user (ID, DISPLAY_NAME, EMAIL, PASSWORD, ROLE)
VALUES ('422ec13a-e0e2-11ec-9d64-0242ac120002',
        'Wendy',
        'wendy@nn.com',
        '12345',
        'COACH');

INSERT INTO lms_user (ID, DISPLAY_NAME, EMAIL, PASSWORD, ROLE)
VALUES ('123e4567-e89b-12d3-a456-426614174002',
        'Herbert',
        'herbert@nn.com',
        'oI23456!',
        'STUDENT');

INSERT INTO STUDENT_CODELAB (ID, FK_STUDENT_ID, FK_CODELAB_ID, PROGRESS, COMMENT)
VALUES (4,
        '123e4567-e89b-12d3-a456-426614174002',
        1,
        'NOT_STARTED',
        'Comment for codelab 1');

INSERT INTO STUDENT_CODELAB (ID, FK_STUDENT_ID, FK_CODELAB_ID, PROGRESS, COMMENT)
VALUES (5,
        '123e4567-e89b-12d3-a456-426614174002',
        2,
        'NOT_STARTED',
        'Comment for codelab 2');

INSERT INTO STUDENT_CODELAB (ID, FK_STUDENT_ID, FK_CODELAB_ID, PROGRESS, COMMENT)
VALUES (6,
        '123e4567-e89b-12d3-a456-426614174002',
        3,
        'NOT_STARTED',
        'Comment for codelab 3');

