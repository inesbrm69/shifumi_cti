CREATE SEQUENCE auto_increment_user
START WITH 1
INCREMENT BY 1;

DROP SEQUENCE auto_increment_user;

CREATE OR REPLACE TRIGGER auto_increment_user
BEFORE INSERT ON UTILISATEUR
FOR EACH ROW
BEGIN
    select auto_increment_user.nextval INTO :new.id FROM DUAL;
END;

INSERT INTO UTILISATEUR (username, password, perso, score) VALUES ('admin','password', 2, 0);
