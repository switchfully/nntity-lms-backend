CREATE TABLE student
(
    ID           UUID PRIMARY KEY,
    DISPLAY_NAME VARCHAR(255) NOT NULL,
    EMAIL        VARCHAR(320) NOT NULL UNIQUE,
    PASSWORD     VARCHAR(255) NOT NULL
);
