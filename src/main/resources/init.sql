CREATE TABLE Players
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR NOT NULL
);
CREATE INDEX ix_gamedb_name on PLAYERS (name);


CREATE TABLE Matches
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    Player1 INT,
    Player2 INT,
    Winner  INT,
    FOREIGN KEY (Player1) REFERENCES PLAYERS (ID),
    FOREIGN KEY (Player2) REFERENCES PLAYERS (ID),
    FOREIGN KEY (Winner) REFERENCES PLAYERS (ID)
);

INSERT INTO Players (name)
VALUES ('Igor Igorevich'),
       ('Oleg Olegovich'),
       ('Федор Александров'),
       ('Uliana Smirnova'),
       ('Elena Ivanova'),
       ('Marta'),
       ('Vladimir'),
       ('Инокентий Ильин'),
       ('Zhora Poddubni'),
       ('Петр Петров');

INSERT INTO Matches (player1, player2, winner)
VALUES (1, 2, 2),
       (2, 3, 3),
       (5, 6, 5),
       (1, 3, 3),
       (3, 10, 10),
       (9, 8, 8),
       (4, 1, 1),
       (2, 3, 2),
       (1, 5, 5),
       (1, 3, 1),
       (6, 7, 7),
       (4, 3, 4),
       (8, 2, 2),
       (10, 9, 9),
       (3, 5, 3),
       (4, 3, 4),
       (5, 8, 8),
       (8, 1, 1),
       (3, 7, 3),
       (10, 4, 10),
       (1, 8, 1),
       (5, 6, 6),
       (2, 3, 2),
       (3, 9, 9),
       (6, 1, 6),
       (10, 5, 5),
       (1, 2, 1);
