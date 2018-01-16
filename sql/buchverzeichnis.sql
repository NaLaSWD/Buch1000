 CREATE TABLE AUTOR (
       ID SERIAL NOT NULL,
       VORNAME varchar(255),
       NACHNAME varchar(255),
       GEB_DATUM date,
       FK_EINZELBUERO INT NOT NULL,
       FK_VERLAG INT NOT NULL,
       PRIMARY KEY (ID));

  CREATE TABLE VERLAG (
       ID SERIAL NOT NULL,
       NAME varchar(255),
       STRASSE varchar(255),
       ORT varchar(255),
       PLZ int4,
       PRIMARY KEY (ID));

 CREATE TABLE EINZELBUERO (
       ID SERIAL NOT NULL,
       STRASSE varchar(255),
       ORT varchar(255),
       PLZ int4,
       PRIMARY KEY (ID));

CREATE TABLE BUCH_AUTOR (
       ID SERIAL NOT NULL,
       BUCH_ID int4 NOT NULL,
       AUTOR_ID int4 NOT NULL,
       PRIMARY KEY (ID));

 CREATE TABLE BUCH (
       ID SERIAL NOT NULL,
       ISBN int4,
       TITEL varchar(255),
       JAHR int4,
       FK_GENRE int4,
       PRIMARY KEY (ID));

 CREATE TABLE GENRE (
       ID SERIAL NOT NULL,
       GENRE varchar(255),
       PRIMARY KEY (ID));

 ALTER TABLE AUTOR
       ADD CONSTRAINT FK_AUTOR_EINZELBUERO FOREIGN KEY (FK_EINZELBUERO)
                                           REFERENCES EINZELBUERO (ID);

 ALTER TABLE AUTOR ADD CONSTRAINT FK_AUTOR_VERLAG FOREIGN KEY (FK_VERLAG)
                                           REFERENCES VERLAG (ID);

 ALTER TABLE BUCH ADD CONSTRAINT FK_BUCH_GENRE FOREIGN KEY (FK_GENRE)
                                           REFERENCES GENRE (ID);