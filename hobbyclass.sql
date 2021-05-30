drop database if exists hobby_class;
create database hobby_class;
use hobby_class;

CREATE TABLE `users` (
    `ID` int NOT NULL AUTO_INCREMENT,
    `Login` text,
    `Password` text,
    `Mail` text,
    `Authority` text,
    PRIMARY KEY (`ID`)
);

CREATE TABLE `teacher` (
     `ID` int NOT NULL AUTO_INCREMENT,
     `Name` text,
     PRIMARY KEY (`ID`)
);

CREATE TABLE `category` (
     `ID` int NOT NULL AUTO_INCREMENT,
     `Name` text,
     PRIMARY KEY (`ID`)
);

CREATE TABLE `hobbyclass` (
    `ID` int NOT NULL AUTO_INCREMENT,
    `Name` text,
    `ID_Teacher` int not null,
    `ID_Category` int not null,
    PRIMARY KEY (`ID`),
    KEY `ID_Teacher` (`ID_Teacher`),
    KEY `ID_Category` (`ID_Category`),
    CONSTRAINT `hobbyclass1` FOREIGN KEY (`ID_Teacher`) REFERENCES `teacher` (`ID`),
    CONSTRAINT `hobbyclass2` FOREIGN KEY (`ID_Category`) REFERENCES `category` (`ID`)
);

INSERT INTO teacher (Name) values
    ('Калинин Виталий Эдуардович'),('Морозов Евдоким Тарасович'),('Суворова Татьяна Павловна'),
    ('Герасимова Версавия Богуславовна'),('Орехов Эльдар Мартынович'),('Трухан Сергей Дмитриевич');

INSERT INTO category (Name) values
    ('Программирование'),('Вокал'),('Рукоделие'),
    ('Хореография'),('Рисование');

INSERT INTO hobbyclass (Name, ID_Teacher, ID_Category) values
    ('Умелые ручки', 3, 3),('Артстудио', 4, 5),('Мир танцулек', 1, 4),
    ('ITChild', 6, 1),('Микрофон', 5, 2),('Юный аниматор', 2, 1);

INSERT INTO users (Login, Password, Mail, Authority) values
    ('admin', '$2y$12$xkOgR9cIkYrmQVuTo.Nks.zFZRSlGgka0K5tROOxPrgfKzU8a5qTa', 'admin@example.com', 'ROLE_ADMIN'),
    ('user', '$2y$12$sK4AggiYLkz1WMmNNbFk0O3t1F3UWcwg0vadq9Q3fevHxX754opJy', 'user@example.com', 'ROLE_GUEST');
