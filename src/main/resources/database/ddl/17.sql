CREATE TABLE `ScriptState` (
    ID TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT
    , NAME VARCHAR(50) NOT NULL
    , OPEN BOOLEAN NOT NULL DEFAULT FALSE
);