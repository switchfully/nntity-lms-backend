INSERT INTO LMS_USER (id, display_name, email, password, role)
VALUES (
        '2812b4ba-90ea-497d-9185-16772cc475f6',
        'Tarzan',
        'tarzan@jungle.com',
        'Jane',
        'STUDENT'
        );

INSERT INTO CODELAB (id, name)
VALUES (
           nextval('codelab_seq'),
        'CodelabTest1'
       );

INSERT INTO CODELAB (id, name)
VALUES (
           nextval('codelab_seq'),
           'CodelabTest2'
       );