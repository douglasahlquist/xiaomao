
DROP TABLE IF EXISTS user CASCADE;
DROP TABLE IF EXISTS attribute CASCADE;

CREATE TABLE `user` ( 
  id integer CONSTRAINT user_details_pk PRIMARY KEY,
  name varchar(32),
  passportnumber varchar(32),

);

CREATE TABLE attribute ( 
  id integer CONSTRAINT attribute_details_pk PRIMARY KEY,
  twitterid integer,
  type varchar(32),
  value varchar(32),
  creationtime date,
  FOREIGN KEY (twitterid) REFERENCES user (id)
);

insert into user values(10001,'Ranga', 'E1234567');

insert into user values(10002,'Ravi', 'A1234568');