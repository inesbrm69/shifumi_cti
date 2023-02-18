CREATE SEQUENCE auto_increment_user
START WITH 1
INCREMENT BY 1;

DROP SEQUENCE auto_increment_user;

--Auto-incrementation des users
CREATE OR REPLACE TRIGGER auto_increment_user
BEFORE INSERT ON UTILISATEUR
FOR EACH ROW
BEGIN
    select auto_increment_user.nextval INTO :new.id FROM DUAL;
END;

--Ajout d'un utilisateur (premier utilisateur)
INSERT INTO UTILISATEUR (username, password, perso, score) VALUES ('admin','password', 2, 0);

--Initialisation des perso au perso 0 (la blonde)
ALTER TABLE utilisateur
MODIFY perso DEFAULT 0;

--Initialisation des scores à 0
ALTER TABLE utilisateur
MODIFY score DEFAULT 0;