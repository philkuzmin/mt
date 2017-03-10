INSERT INTO Users (login, password, first_name, last_name, country)
  VALUES ('admin', 'admin', 'Phil', 'Kuzmin', 'RUSSIA');
INSERT INTO Users (login, password, first_name, last_name, country)
  VALUES ('Jagger', 'qwe', 'Mick', 'Jagger', 'USA');
INSERT INTO Users (login, password, first_name, last_name, country)
  VALUES ('Bono', 'qwe', 'Bono', 'U2', 'USA');

INSERT INTO Instruments (instrument_name) VALUES ('guitar');
INSERT INTO Instruments (instrument_name) VALUES ('bass');
INSERT INTO Instruments (instrument_name) VALUES ('vocals');

INSERT INTO Users_Instruments (user_id, instrument_id) VALUES (1 , 1);
INSERT INTO Users_Instruments (user_id, instrument_id) VALUES (1 , 2);
INSERT INTO Users_Instruments (user_id, instrument_id) VALUES (1 , 3);

INSERT INTO Subscriptions (user_id, subscripted_user_id) VALUES (1, 2);
INSERT INTO Subscriptions (user_id, subscripted_user_id) VALUES (1, 3);

INSERT INTO Messages (user_id, message_date, message_text) VALUES (2, '2017-01-01', 'This is my first music tweet!!');
INSERT INTO Messages (user_id, message_date, message_text) VALUES (2, '2017-02-01', 'Second tweet! Nice service you''ve got here');
INSERT INTO Messages (user_id, message_date, message_text) VALUES (3, '2017-02-07', 'What''s up?');