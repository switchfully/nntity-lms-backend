INSERT INTO LMS_USER (id, display_name, email, password, role)
VALUES ('2812b4ba-90ea-497d-9185-16772cc475f6',
        'Tarzan',
        'tarzan@jungle.com',
        'Jane',
        'STUDENT');

INSERT INTO LMS_USER (id, display_name, email, password, role)
VALUES ('bc9091ba-c0b7-412b-ad7e-eb7665e06078',
        'HarryPotter',
        'harry_potter@hogwarts.uk',
        '0uiddi!ch',
        'STUDENT');

INSERT INTO course (ID, NAME)
VALUES (nextval('course_seq'), 'Composition');

INSERT INTO course (ID, NAME)
VALUES (nextval('course_seq'), 'Polymorphism');

INSERT INTO CODELAB (id, name, fk_course_id)
VALUES (nextval('codelab_seq'),
        'CodelabTest1',
        1);

INSERT INTO CODELAB (id, name, fk_course_id)
VALUES (nextval('codelab_seq'),
        'CodelabTest2',
        2);

INSERT INTO CODELAB (id, name, fk_course_id)
VALUES (nextval('codelab_seq'),
        'CodelabTest3',
        2);

INSERT INTO STUDENT_CODELAB (id, fk_student_id, fk_codelab_id, progress)
VALUES (nextval('student_codelab_seq'),
        '2812b4ba-90ea-497d-9185-16772cc475f6',
        1,
        'DONE');

INSERT INTO STUDENT_CODELAB (id, fk_student_id, fk_codelab_id, progress)
VALUES (nextval('student_codelab_seq'),
        '2812b4ba-90ea-497d-9185-16772cc475f6',
        2,
        'BUSY');

INSERT INTO STUDENT_CODELAB (id, fk_student_id, fk_codelab_id, progress)
VALUES (nextval('student_codelab_seq'),
        'bc9091ba-c0b7-412b-ad7e-eb7665e06078',
        1,
        'DONE');

INSERT INTO STUDENT_CODELAB (id, fk_student_id, fk_codelab_id, progress)
VALUES (nextval('student_codelab_seq'),
        'bc9091ba-c0b7-412b-ad7e-eb7665e06078',
        2,
        'STUCK');

INSERT INTO STUDENT_CODELAB (id, fk_student_id, fk_codelab_id, progress)
VALUES (nextval('student_codelab_seq'),
        'bc9091ba-c0b7-412b-ad7e-eb7665e06078',
        3,
        'FEEDBACK_NEEDED');