USE SuperHeroSightings;

INSERT INTO Powers (power_name) VALUES ('Flight'), ('Magic'), ('Superhuman Strength'), ('Phsychic'), ('Shapeshifting'), ('Flexibility'), ('Nine Lives');
INSERT INTO HeroesVillains (hv_name, hv_description, isVillain) VALUES ('Shazam', 'Master Of Magic', 0), ('Black Adam', 'Uses magic for evil', 1), ('Wonder Woman', 'Amazonian Princess Warrior', 0), ('Cat Woman', 'Loves Batman', 1);
INSERT INTO HVPowers (hv_id, power_id) VALUES (1,1),(1,2),(2,1),(2,2),(3,1),(3,3),(4,6),(4,7);