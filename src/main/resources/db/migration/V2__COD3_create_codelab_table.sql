CREATE TABLE CODELAB
(
    ID BIGINT PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL
);
CREATE SEQUENCE codelab_seq start with 1 increment by 1;

INSERT INTO CODELAB (ID, NAME)
VALUES (
        nextval('codelab_seq'),
        'Codelab01'
       );
INSERT INTO CODELAB (ID, NAME)
VALUES (
           nextval('codelab_seq'),
           'Codelab02'
       );
