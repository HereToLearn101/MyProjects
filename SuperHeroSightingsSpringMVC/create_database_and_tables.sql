-- DROP DATABASE IF EXISTS superherosightings_test;
DROP DATABASE IF EXISTS superherosightings;
CREATE DATABASE IF NOT EXISTS SuperHeroSightings;
-- CREATE DATABASE IF NOT EXISTS SuperHeroSightings_Test;

-- USE SuperHeroSightings_Test;
USE SuperHeroSightings;

CREATE TABLE IF NOT EXISTS Locations
(
	`location_id` INT NOT NULL auto_increment,
    `location_name` VARCHAR(30) NOT NULL,
    `location_description` VARCHAR(100) NOT NULL,
    `address` VARCHAR(50) NOT NULL,
    `lat` DECIMAL(10,6) NOT NULL,
    `lng` DECIMAL(10,6) NOT NULL,
    PRIMARY KEY(`location_id`)
) auto_increment=1;

CREATE TABLE IF NOT EXISTS Sightings
(
	`sighting_id` INT NOT NULL auto_increment,
    `date` DATE NOT NULL,
    `location_id` INT NOT NULL,
    PRIMARY KEY(`sighting_id`),
    FOREIGN KEY(`location_id`) REFERENCES Locations(`location_id`)
) auto_increment=1;

CREATE TABLE IF NOT EXISTS HeroesVillains
(
	`hv_id` INT NOT NULL auto_increment,
    `hv_name` VARCHAR(30) NOT NULL,
    `hv_description` VARCHAR(100) NOT NULL,
    `isVillain` BOOLEAN NOT NULL,
    PRIMARY KEY(`hv_id`)
) auto_increment=1;

CREATE TABLE IF NOT EXISTS Powers
(
	`power_id` INT NOT NULL auto_increment,
    `power_name` VARCHAR(30) NOT NULL,
    PRIMARY KEY(`power_id`)
) auto_increment=1;

CREATE TABLE IF NOT EXISTS HVPowers
(
	`hv_id` INT NOT NULL,
    `power_id` INT NOT NULL,
    PRIMARY KEY(`hv_id`, `power_id`)
);

ALTER TABLE HVPowers
ADD CONSTRAINT fk_HVPowers_HeroesVillains
FOREIGN KEY(`hv_id`) REFERENCES HeroesVillains(`hv_id`);

ALTER TABLE HVPowers
ADD CONSTRAINT fk_HVPowers_Powers
FOREIGN KEY(`power_id`) REFERENCES Powers(`power_id`);

CREATE TABLE IF NOT EXISTS HVSightings
(
	`hv_id` INT NOT NULL,
    `sighting_id`INT NOT NULL,
    PRIMARY KEY(`hv_id`, `sighting_id`)
);

ALTER TABLE HVSightings
ADD CONSTRAINT fk_HVSightings_HeroesVillains
FOREIGN KEY(`hv_id`) REFERENCES HeroesVillains(`hv_id`);

ALTER TABLE HVSightings
ADD CONSTRAINT fk_HVSightings_Sightings
FOREIGN KEY(`sighting_id`) REFERENCES Sightings(`sighting_id`);

CREATE TABLE IF NOT EXISTS Organizations
(
	`org_id` INT NOT NULL auto_increment,
    `org_name` VARCHAR(30) NOT NULL,
    `org_description` VARCHAR(100) NOT NULL,
    `phone` VARCHAR(30) NULL,
    `email` VARCHAR(50) NOT NULL,
    `location_id` INT NOT NULL,
    PRIMARY KEY(`org_id`),
    FOREIGN KEY(`location_id`) REFERENCES Locations(`location_id`)
) auto_increment=1;

CREATE TABLE IF NOT EXISTS HVOrganizations
(
	`hv_id` INT NOT NULL,
    `org_id` INT NOT NULL,
    PRIMARY KEY(`hv_id`, `org_id`)
);

ALTER TABLE HVOrganizations
ADD CONSTRAINT fk_HVOrganizations_HeroesVillains
FOREIGN KEY(`hv_id`) REFERENCES HeroesVillains(`hv_id`);

ALTER TABLE HVOrganizations
ADD CONSTRAINT fk_HVOrganizations_Organizations
FOREIGN KEY(`org_id`) REFERENCES Organizations(`org_id`);


INSERT INTO Powers (power_name) VALUES ('Flight'), ('Magic'), ('Superhuman Strength'), ('Phsychic'), ('Shapeshifting'), ('Flexibility'), ('Nine Lives');
INSERT INTO HeroesVillains (hv_name, hv_description, isVillain) VALUES ('Shazam', 'Master Of Magic', 0), ('Black Adam', 'Uses magic for evil', 1), ('Wonder Woman', 'Amazonian Princess Warrior', 0), ('Cat Woman', 'Loves Batman', 1);
INSERT INTO HVPowers (hv_id, power_id) VALUES (1,1),(1,2),(2,1),(2,2),(3,1),(3,3),(4,6),(4,7);

insert into Locations (location_name, location_description, address, lat, lng) values ('Littel and Sons', 'Integer ac leo. Pellentesque ultrices mattis odio.', '0638 Annamark Plaza', -15.373458, 3.37124);
insert into Locations (location_name, location_description, address, lat, lng) values ('Connelly-Schiller', 'Aenean auctor gravida sem. Praesent id massa id nisl venenatis lacinia. Aenean sit amet justo.', '91 Mcbride Avenue', -5.960008, 10.404877);
insert into Locations (location_name, location_description, address, lat, lng) values ('Corkery LLC', 'Nulla tellus. In sagittis dui vel nisl.', '7835 Kropf Road', -28.497467, -26.870953);
insert into Locations (location_name, location_description, address, lat, lng) values ('Williamson-Boehm', 'In hac habitasse platea dictumst.', '8506 Valley Edge Plaza', -2.457003, -43.808757);
insert into Locations (location_name, location_description, address, lat, lng) values ('Dicki, Runolfsson and Abshire', 'Morbi ut odio.', '18651 Gerald Lane', -43.368249, -34.945494);

INSERT INTO Organizations (org_name, org_description, phone, email, location_id) VALUES ('Hero League', 'League of Heroes', '555-5555', 'hero@league.com', 1),
('Villain League', 'League of Villains', '111-1111', 'villain@league.com', 2);

INSERT INTO HVOrganizations (hv_id, org_id) VALUES (1, 1),(3,1),(2,2),(4,2);

INSERT INTO Sightings (date, location_id) VALUES ('2017-01-10', 1),('2017-01-20', 1),('2017-02-01', 2),
('2017-02-11', 2),('2017-02-22', 3),('2017-03-05', 3),('2017-03-01', 4),('2017-03-15', 5),('2017-03-20', 5),('2017-03-25', 5);

INSERT INTO hvsightings (hv_id, sighting_id) VALUES (3,1),(4,1),(3,2),
(1,3),(2,4),(1,5),(2,5),(1,6),(1,7),(2,7),(3,7),(4,7),(1,8),(4,8),(2,9),(3,9),(1,10);