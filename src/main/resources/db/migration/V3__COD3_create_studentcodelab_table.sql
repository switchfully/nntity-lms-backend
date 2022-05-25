CREATE TABLE STUDENT_CODELAB
(
    ID BIGINT PRIMARY KEY,
    FK_STUDENT_ID UUID NOT NULL,
    FK_CODELAB_ID BIGINT NOT NULL,
    PROGRESS VARCHAR(100) NOT NULL,
    CONSTRAINT FK_STUDENT_ID foreign key (FK_STUDENT_ID) references STUDENT (ID),
    CONSTRAINT FK_CODELAB_ID foreign key (FK_CODELAB_ID) references CODELAB (ID)
);
CREATE SEQUENCE student_codelab_seq start with 1 increment by 1;

INSERT INTO STUDENT_CODELAB (ID, FK_STUDENT_ID, FK_CODELAB_ID, PROGRESS)
VALUES (
        nextval('student_codelab_seq'),
        'bd39d3fc-d101-4865-aa2e-bac55a5d4321',
        1,
        'DONE'
       );

INSERT INTO STUDENT_CODELAB (ID, FK_STUDENT_ID, FK_CODELAB_ID, PROGRESS)
VALUES (
           nextval('student_codelab_seq'),
           'bd39d3fc-d101-4865-aa2e-bac55a5d4321',
           2,
           'BUSY'
       );