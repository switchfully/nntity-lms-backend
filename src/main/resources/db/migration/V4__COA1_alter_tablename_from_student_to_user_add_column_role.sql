ALTER TABLE IF EXISTS student RENAME TO LMS_USER;

ALTER TABLE IF EXISTS LMS_USER
    ADD ROLE VARCHAR (100);
