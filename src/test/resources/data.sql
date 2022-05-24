INSERT INTO STUDENT (id, display_name, email, password)
VALUES (
        'ce330ca0-d83a-11ec-9d64-0242ac120002',
        'Tarzan',
        'Tarzan@Jungle.com',
        'JaneIsTheLoveOfMyLife'
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