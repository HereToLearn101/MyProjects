USE SuperHeroSightings;

INSERT INTO Organizations (org_name, org_description, phone, email, location_id) VALUES ('Hero League', 'League of Heroes', '555-5555', 'hero@league.com', 1),
('Villain League', 'League of Villains', '111-1111', 'villain@league.com', 2);

INSERT INTO HVOrganizations (hv_id, org_id) VALUES (1, 1),(3,1),(2,2),(4,2);