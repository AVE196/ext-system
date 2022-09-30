INSERT INTO cr_district (district_code, district_name)
VALUES (1, 'Выборгский');
	
INSERT INTO cr_street (street_code, street_name)
VALUES (1, 'Сампсоньевский проспект');

INSERT INTO cr_address (district_code, street_code, building, extension, apartment)
VALUES (1, 1, '10', '2', '192');

INSERT INTO cr_address (district_code, street_code, building, extension, apartment)
VALUES (1, 1, '230', null, '13');
	
INSERT INTO cr_person (sur_name, given_name, patronymic, date_of_birth, passport_seria, passport_number, passport_date, certificate_number, certificate_date)
VALUES ('Васильев', 'Иван', 'Петрович', '1991-07-23', '1234', '123456', '2015-03-17', null, null);

INSERT INTO cr_person (sur_name, given_name, patronymic, date_of_birth, passport_seria, passport_number, passport_date, certificate_number, certificate_date)
VALUES ('Васильева', 'Ирина', 'Николаевна', '1993-01-26', '4321', '654321', '2017-05-06', null, null);

INSERT INTO cr_person (sur_name, given_name, patronymic, date_of_birth, passport_seria, passport_number, passport_date, certificate_number, certificate_date)
VALUES ('Васильев', 'Олег', 'Иванович', '2006-09-06', null, null, null, '123465', '2006-09-15');

INSERT INTO cr_person (sur_name, given_name, patronymic, date_of_birth, passport_seria, passport_number, passport_date, certificate_number, certificate_date)
VALUES ('Васильева', 'Юлия', 'Ивановна', '2011-01-28', null, null, null, '654213', '2011-02-03');
	
INSERT INTO cr_address_person (person_id, address_id, start_date, end_date, temporal)
VALUES (1, 1, '2003-05-22', null, false);

INSERT INTO cr_address_person (person_id, address_id, start_date, end_date)
VALUES (2, 1, '2001-05-22', '2005-05-15');

INSERT INTO cr_address_person (person_id, address_id, start_date, end_date)
VALUES (2, 2, '2005-05-22', null);

INSERT INTO cr_address_person (person_id, address_id, start_date, end_date)
VALUES (3, 1, '2006-09-18', null);

INSERT INTO cr_address_person (person_id, address_id, start_date, end_date)
VALUES (4, 1, '2011-02-17', null);