INSERT INTO STUDENT (id, display_name, email, password)
VALUES (
        '2812b4ba-90ea-497d-9185-16772cc475f6',
        'Tarzan',
        'tarzan@jungle.com',
        'Jane'
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