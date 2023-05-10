DROP TABLE IF EXISTS characters;

CREATE TABLE characters (
  id int unsigned AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  age integer(3),
  PRIMARY KEY(id)
);

INSERT INTO characters (name,age) VALUES ("月島雫",14);
INSERT INTO characters (name,age) VALUES ("松崎海",16);
INSERT INTO characters (name,age) VALUES ("草壁タツオ",32);
INSERT INTO characters (name,age) VALUES ("里見菜穂子",23);
INSERT INTO characters (name,age) VALUES ("荻野千尋",10);


