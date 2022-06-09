INSERT INTO lms_user (ID, DISPLAY_NAME, EMAIL, PASSWORD)
VALUES ('bd39d3fc-d101-4865-aa2e-bac55a5d4321', 'Ruben Lommelen', 'ruben@mail.com', '123456');

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
        'Herbert Coenen',
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

UPDATE LMS_USER
SET EMAIL = 'bob@nn.com'
WHERE ID = 'bd39d3fc-d101-4865-aa2e-bac55a5d4321';


INSERT INTO COURSE (ID, NAME)
VALUES (3,
        'Objects'
       );

INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (4, 'Basic Codelab 01', 3);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (5, 'Basic Codelab 02', 3);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (6, 'Basic Codelab 03', 3);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (7, 'Basic Codelab 04', 3);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (8, 'Basic Codelab 05', 3);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (9, 'Basic Codelab 06', 3);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (10, 'Advanced Codelab 01', 3);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (11, 'Advanced Codelab 02', 3);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (12, 'Advanced Codelab 03', 3);

INSERT INTO COURSE (ID, NAME)
VALUES (4,
        'Encapsulation'
       );

INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (13, 'Basic Codelab 01', 4);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (14, 'Basic Codelab 02', 4);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (15, 'Basic Codelab 03', 4);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (16, 'Basic Codelab 04', 4);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (17, 'Basic Codelab 05', 4);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (18, 'Advanced Codelab 01', 4);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (19, 'Advanced Codelab 02', 4);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (20, 'Advanced Codelab 03', 4);



INSERT INTO COURSE (ID, NAME)
VALUES (5,
        'Inheritance'
       );

INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (21, 'Basic Codelab 01', 5);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (23, 'Basic Codelab 02', 5);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (24, 'Basic Codelab 03', 5);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (25, 'Basic Codelab 04', 5);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (26, 'Basic Codelab 05', 5);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (27, 'Advanced Codelab 01', 5);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (28, 'Advanced Codelab 02', 5);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (29, 'Advanced Codelab 03', 5);

INSERT INTO COURSE (ID, NAME)
VALUES (6,
        'Interfaces'
       );

INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (30, 'Basic Codelab 01', 6);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (31, 'Basic Codelab 02', 6);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (32, 'Basic Codelab 03', 6);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (33, 'Basic Codelab 04', 6);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (34, 'Advanced Codelab 01', 6);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (35, 'Advanced Codelab 02', 6);

UPDATE CODELAB SET NAME = 'Basic Codelab 01' WHERE ID = 1;
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (36, 'Basic Codelab 02', 1);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (37, 'Basic Codelab 03', 1);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (38, 'Basic Codelab 04', 1);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (39, 'Basic Codelab 05', 1);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (40, 'Advanced Codelab 01', 1);

UPDATE CODELAB SET NAME = 'Basic Codelab 01' WHERE ID = 2;
UPDATE CODELAB SET NAME = 'Basic Codelab 02' WHERE ID = 3;
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (41, 'Basic Codelab 03', 2);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (42, 'Advanced Codelab 01', 2);
INSERT INTO CODELAB (ID, NAME, fk_course_id)
VALUES (43, 'Advanced Codelab 02', 2);