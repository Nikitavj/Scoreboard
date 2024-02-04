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
VALUES ('Igor'),
       ('Oleg'),
       ('Fedor'),
       ('Uliana'),
       ('Elena'),
       ('Marta'),
       ('Vladimir'),
       ('Innokenti'),
       ('Zhora'),
       ('Petr');

INSERT INTO Matches (player1, player2, winner)
VALUES (1, 2, 2),
       (6, 3, 3),
       (4, 9, 4),
       (7, 3, 3),
       (10, 8, 8),
       (1, 6, 1);
