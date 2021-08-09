INSERT INTO USER(name, email, password) VALUES('Player1', 'player1@email.com', '$2a$10$Uv1Z6jFD1NoqkgSDjxVHFOj3cyVpSaBMyMuPBjNVGSl/prcxLCMrK');
INSERT INTO USER(name, email, password) VALUES('Player2', 'player2@email.com', '$2a$10$Uv1Z6jFD1NoqkgSDjxVHFOj3cyVpSaBMyMuPBjNVGSl/prcxLCMrK');
INSERT INTO USER(name, email, password) VALUES('Player3', 'player3@email.com', '$2a$10$Uv1Z6jFD1NoqkgSDjxVHFOj3cyVpSaBMyMuPBjNVGSl/prcxLCMrK');
INSERT INTO USER(name, email, password) VALUES('Player4', 'player4@email.com', '$2a$10$Uv1Z6jFD1NoqkgSDjxVHFOj3cyVpSaBMyMuPBjNVGSl/prcxLCMrK');

INSERT INTO GAME(name, game_master_id) VALUES('Game1', '1');
INSERT INTO GAME(name, game_master_id) VALUES('Game2', '2');

INSERT INTO CHARACTER(owner_id, game_id, name, race, job, level, hp, strength, intelligence, wisdom, dexterity, constitution, charisma) VALUES('1', '1', 'Character1', 'HUMAN', 'PALADIN', '1', '100', '5', '5', '5', '5', '5', '5');
INSERT INTO CHARACTER(owner_id, game_id, name, race, job, level, hp, strength, intelligence, wisdom, dexterity, constitution, charisma) VALUES('2', '1', 'Character2', 'DWARF', 'CLERIC', '1', '100', '5', '5', '5', '5', '5', '5');
INSERT INTO CHARACTER(owner_id, game_id, name, race, job, level, hp, strength, intelligence, wisdom, dexterity, constitution, charisma) VALUES('3', '2', 'Character3', 'ELF', 'RANGER', '1', '100', '5', '5', '5', '5', '5', '5');
INSERT INTO CHARACTER(owner_id, game_id, name, race, job, level, hp, strength, intelligence, wisdom, dexterity, constitution, charisma) VALUES('4', '2', 'Character4', 'HALFLING', 'BARD', '1', '100', '5', '5', '5', '5', '5', '5');