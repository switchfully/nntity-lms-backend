INSERT INTO LMS_USER (id, display_name, email, password, role)
VALUES ('2812b4ba-90ea-497d-9185-16772cc475f6',
        'Tarzan',
        'tarzan@jungle.com',
        'Jane',
        'STUDENT');

INSERT INTO CODELAB (id, name)
VALUES (nextval('codelab_seq'),
        'CodelabTest1',
        1);

INSERT INTO CODELAB (id, name)
VALUES (nextval('codelab_seq'),
        'CodelabTest2',
        2);

INSERT INTO STUDENT_CODELAB (id, fk_student_id, fk_codelab_id, progress)
VALUES (nextval('student_codelab_seq'),
        '2812b4ba-90ea-497d-9185-16772cc475f6',
        3,
        'DONE');

INSERT INTO STUDENT_CODELAB (id, fk_student_id, fk_codelab_id, progress)
VALUES (nextval('student_codelab_seq'),
        '2812b4ba-90ea-497d-9185-16772cc475f6',
        4,
        'BUSY');