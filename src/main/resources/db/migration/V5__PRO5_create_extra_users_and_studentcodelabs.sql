INSERT INTO lms_user (ID, DISPLAY_NAME, EMAIL, PASSWORD, ROLE)
VALUES (
        '422ec13a-e0e2-11ec-9d64-0242ac120002',
        'Wendy',
        'wendy@nn.com',
        '12345',
        'COACH');

INSERT INTO lms_user (ID, DISPLAY_NAME, EMAIL, PASSWORD, ROLE)
VALUES (
           '123e4567-e89b-12d3-a456-426614174002',
           'Herbert',
           'herbert@nn.com',
           'oI23456!',
           'STUDENT');

INSERT INTO CODELAB (ID, NAME)
VALUES (
           nextval('codelab_seq'),
           'Codelab03'
       );

INSERT INTO STUDENT_CODELAB (ID, FK_STUDENT_ID, FK_CODELAB_ID, PROGRESS)
VALUES (
           nextval('student_codelab_seq'),
           '123e4567-e89b-12d3-a456-426614174002',
           1,
           'NOT_STARTED'
       );

INSERT INTO STUDENT_CODELAB (ID, FK_STUDENT_ID, FK_CODELAB_ID, PROGRESS)
VALUES (
           nextval('student_codelab_seq'),
           '123e4567-e89b-12d3-a456-426614174002',
           2,
           'NOT_STARTED'
       );

INSERT INTO STUDENT_CODELAB (ID, FK_STUDENT_ID, FK_CODELAB_ID, PROGRESS)
VALUES (
           nextval('student_codelab_seq'),
           '123e4567-e89b-12d3-a456-426614174002',
           3,
           'NOT_STARTED'
       );