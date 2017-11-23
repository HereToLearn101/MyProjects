/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.HeroVillain;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Power;
import com.sg.superherosightings.model.Sighting;
import java.util.Comparator;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tedis
 */
public class SuperHeroSightingsDaoJdbcTemplateImpl implements SuperHeroSightingsDao {
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //********************LOCATION METHODS AND PREPARED STATEMENTS*********************************************
    //****************************************************************************************************
    private static final String SQL_INSERT_LOCATION
            = "INSERT INTO Locations (location_name, "
            + "location_description, address, lat, lng) values (?, ?, ?, ?, ?)";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getAddress(),
                location.getLat(),
                location.getLng());
        
        int locationId = jdbcTemplate.queryForObject("Select LAST_INSERT_ID()",
                Integer.class);
        
        location.setId(locationId);
    }

    private static final String SQL_GET_SIGHTING_LOCATION_ID
            = "SELECT * FROM Sightings WHERE location_id = ?";
    private static final String SQL_GET_ORGANIZATION_LOCATION_ID
            = "SELECT * FROM Organizations WHERE location_id = ?";
    private static final String SQL_DELETE_LOCATION
            = "DELETE FROM Locations WHERE location_id = ?";
    @Override
    public void deleteLocation(int locationId) {
        List<Sighting> sightings = jdbcTemplate.query(SQL_GET_SIGHTING_LOCATION_ID, new SightingMapper(), locationId);
        List<Organization> orgs = jdbcTemplate.query(SQL_GET_ORGANIZATION_LOCATION_ID, new OrganizationMapper(), locationId);
        
        for (Sighting sighting : sightings) {
            this.deleteSighting(sighting.getId());
        }
        
        for (Organization org : orgs) {
            this.deleteOrganization(org.getId());
        }
        
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
    }

    private static final String SQL_UPDATE_LOCATION
            = "UPDATE Locations SET location_name = ?, location_description = ?, "
            + "address = ?, lat = ?, lng = ? WHERE location_id = ?";
    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getAddress(),
                location.getLat(), // latitude
                location.getLng(), // longitude
                location.getId());
    }

    private static final String SQL_SELECT_LOCATION
            = "SELECT * FROM Locations WHERE location_id = ?";
    @Override
    public Location getLocationById(int locationId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION, new LocationMapper(), locationId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final String SQL_SELECT_ALL_LOCATIONS
            = "SELECT * FROM Locations";
    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
    }
    //****************************************************************************************************
    //****************************************************************************************************
    
    //************************SIGHTING METHODS AND PREPARED STATEMENTS************************************
    //****************************************************************************************************
    
    private static final String SQL_INSERT_SIGHTING
            = "INSERT INTO Sightings (date, location_id) VALUES (?, ?)";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getDate().toString(),
                sighting.getLocation().getId());
        
        int sightingId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", 
                Integer.class);
        
        sighting.setId(sightingId);
        
        insertHVSightings(sighting);
    }
    
    private static final String SQL_DELETE_HVSIGHTING
            = "DELETE FROM HVSightings WHERE sighting_id = ?";
    private static final String SQL_DELETE_SIGHTING
            = "DELETE FROM Sightings WHERE sighting_id = ?";
    @Override
    public void deleteSighting(int sightingId) {
        // delete HVSighting relationship for sighting
        jdbcTemplate.update(SQL_DELETE_HVSIGHTING,
                sightingId);
        // delete sighting
        jdbcTemplate.update(SQL_DELETE_SIGHTING,
                sightingId);
    }
    
    private static final String SQL_UPDATE_SIGHTING
            = "UPDATE Sightings SET date = ?, location_id = ? WHERE sighting_id = ?";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING, 
                sighting.getDate().toString(),
                sighting.getLocation().getId(), 
                sighting.getId());
        
        jdbcTemplate.update(SQL_DELETE_HVSIGHTING, sighting.getId());
        
        insertHVSightings(sighting);
    }
    
    private static final String SQL_SELECT_SIGHTING
            = "SELECT * FROM Sightings WHERE sighting_id = ?";
    @Override
    public Sighting getSightingById(int sightingId) {
        try {
            Sighting sighting = jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING, 
                    new SightingMapper(), sightingId);
            sighting.setLocation(findLocationForSighting(sighting));
            sighting.sethVList(findHeroesVillainsForSighting(sighting));
            return sighting;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    private static final String SQL_SELECT_SIGHTINGS_BY_LOCATION_ID
            = "SELECT * FROM Sightings WHERE location_id = ?";
    @Override
    public List<Sighting> getSightingsByLocationId(int locationId) {
        List<Sighting> sightingList = jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_LOCATION_ID, 
                new SightingMapper(), locationId);
        
        return associateLocationAndHeroesVillainsWithSightings(sightingList);
    }
    
    private static final String SQL_SELECT_SIGHTINGS_BY_HEROVILLAIN_ID
            = "SELECT s.sighting_id, s.date FROM Sightings AS s INNER JOIN "
            + "HVSightings AS hvs on s.sighting_id = hvs.sighting_id WHERE "
            + "hv_id = ?";
    @Override
    public List<Sighting> getSightingsByHeroVillainId(int hvId) {
        List<Sighting> sightingList = jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_HEROVILLAIN_ID, 
                new SightingMapper(), hvId);
        
        return associateLocationAndHeroesVillainsWithSightings(sightingList);
    }
    
    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "SELECT * FROM SIGHTINGS";
    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> sightingList = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS, new SightingMapper());
        
        return associateLocationAndHeroesVillainsWithSightings(sightingList);
    }
    
    //*************************HELPER METHODS FOR SIGHTINGS***********************************************
    
    private static final String SQL_INSERT_HVSIGHTINGS
            = "INSERT INTO HVSightings (hv_id, sighting_id) VALUES (?, ?)";
    private void insertHVSightings(Sighting sighting) {
        int sightingId = sighting.getId();
        List<HeroVillain> hVList = sighting.gethVList();
        
        // update HVSightings table
        for (HeroVillain currentHV : hVList) {
            jdbcTemplate.update(SQL_INSERT_HVSIGHTINGS, currentHV.getId(), sightingId);
        }
    }
    
    private static final String SQL_SELECT_LOCATION_BY_SIGHTING_ID
            = "SELECT l.location_id, l.location_name, l.location_description, "
            + "l.address, l.lat, l.lng FROM Locations AS l INNER JOIN Sightings AS s ON "
            + "l.location_id = s.location_id WHERE s.sighting_id = ?";
    private Location findLocationForSighting(Sighting sighting) {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING_ID, 
                new LocationMapper(), sighting.getId());
    }
    
    private static final String SQL_SELECT_HEROESVILLAINS_BY_SIGHTING_ID
            = "SELECT hv.hv_id, hv.hv_name, hv.hv_description, hv.isVillain "
            + "FROM HeroesVillains AS hv INNER JOIN HVSightings as hvs ON "
            + "hv.hv_id = hvs.hv_id WHERE hvs.sighting_id = ?";
    private List<HeroVillain> findHeroesVillainsForSighting(Sighting sighting) {
        List<HeroVillain> hvList = jdbcTemplate.query(SQL_SELECT_HEROESVILLAINS_BY_SIGHTING_ID, new HeroVillainMapper(), 
                sighting.getId());
        
        // borrowed the helper method, findPowerForHeroVillain(), from HeroesVillains 
        // section to get all powers associated with the HeroVillain
        for (HeroVillain hv : hvList) {
            hv.setPowers(findPowersForHeroVillain(hv));
        }
        
        return hvList;
    }
    
    private List<Sighting> associateLocationAndHeroesVillainsWithSightings(List<Sighting> sightingList) {
        for (Sighting sighting : sightingList) {
            sighting.setLocation(findLocationForSighting(sighting));
            sighting.sethVList(findHeroesVillainsForSighting(sighting));
        }
        return sightingList;
    }
    //*****************************************************************************************************
    //*****************************************************************************************************
    
    //************************POWERS METHODS AND PREPARED STATEMENTS***************************************
    //*****************************************************************************************************
    
    private static final String SQL_INSERT_POWER
            = "INSERT INTO Powers (power_name) VALUES (?)";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPower(Power power) {
        jdbcTemplate.update(SQL_INSERT_POWER,
                power.getPowerName());
        
        int powerId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", 
                Integer.class);
        
        power.setId(powerId);
    }
    
    private static final String SQL_DELETE_POWER
            = "DELETE FROM Powers WHERE power_id = ?";
    @Override
    public void deletePower(int powerId) {
        jdbcTemplate.update(SQL_DELETE_POWER, powerId);
    }
    
    private static final String SQL_UPDATE_POWER
            = "UPDATE Powers SET power_name = ? WHERE power_id = ?";
    @Override
    public void updatePower(Power power) {
        jdbcTemplate.update(SQL_UPDATE_POWER,
                power.getPowerName(), power.getId());
    }
    
    private static final String SQL_SELECT_POWER
            = "SELECT * FROM Powers WHERE power_id = ?";
    @Override
    public Power getPowerById(int powerId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_POWER, new PowerMapper(),
                    powerId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    private static final String SQL_SELECT_ALL_POWERS
            = "SELECT * FROM Powers";
    @Override
    public List<Power> getAllPowers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_POWERS, new PowerMapper());
    }
    
    //************************HEROESVILLAINS METHODS AND PREPARED STATEMENTS******************************
    //****************************************************************************************************
    private static final String SQL_INSERT_HEROVILLAIN
            = "INSERT INTO HeroesVillains (hv_name, hv_description, isVillain) "
            + "VALUES (?, ?, ?)";
    @Override
    public void addHeroVillain(HeroVillain hV) {
        jdbcTemplate.update(SQL_INSERT_HEROVILLAIN,
                hV.gethVName(),
                hV.gethVDescription(),
                hV.isIsVillain());
        
        int hVId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", 
                Integer.class);
        
        hV.setId(hVId);
        
        // updating the HVPowers table
        insertHVPowers(hV);
    }
    
    private static final String SQL_DELETE_HVPOWERS_HV_ID
            = "DELETE FROM HVPowers WHERE hv_id = ?";
    private static final String SQL_DELETE_HVSIGHTINGS_HV_ID
            = "DELETE FROM HVSightings WHERE hv_id = ?";
    private static final String SQL_DELETE_HVORGANIZATIONS_HV_ID
            = "DELETE FROM HVOrganizations WHERE hv_id = ?";
    private static final String SQL_DELETE_HEROVILLAIN
            = "DELETE FROM HeroesVillains WHERE hv_id = ?";
    @Override
    public void deleteHeroVillain(int id) {
        // delete HVPowers relationship for HeroVillain
        jdbcTemplate.update(SQL_DELETE_HVPOWERS_HV_ID, id);
        
        // delete HVSightings relationship for HeroVillain
        jdbcTemplate.update(SQL_DELETE_HVSIGHTINGS_HV_ID, id);
        
        // delete HVOrganizations relationship for HeroVillain
        jdbcTemplate.update(SQL_DELETE_HVORGANIZATIONS_HV_ID, id);
        
        // delete HV
        jdbcTemplate.update(SQL_DELETE_HEROVILLAIN, id);
    }
    
    private static final String SQL_UPDATE_HEROVILLAIN
            = "UPDATE HeroesVillains SET hv_name = ?, hv_description = ?, "
            + "isVillain = ? WHERE hv_id = ?";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateHeroVillain(HeroVillain hV) {
        jdbcTemplate.update(SQL_UPDATE_HEROVILLAIN,
                hV.gethVName(),
                hV.gethVDescription(),
                hV.isIsVillain(),
                hV.getId());
        
        jdbcTemplate.update(SQL_DELETE_HVPOWERS_HV_ID, hV.getId());
        insertHVPowers(hV);
    }
    
    private static final String SQL_SELECT_HEROVILLAIN
            = "SELECT * FROM HeroesVillains WHERE hv_id = ?";
    @Override
    public HeroVillain getHeroVillainById(int id) {
        try {
            // get the properties from the HeroesVillains table
            HeroVillain hV = jdbcTemplate.queryForObject(SQL_SELECT_HEROVILLAIN, 
                new HeroVillainMapper(), id);           
            // get the Powers for this HeroVillain and set list on the HeroVillain
            hV.setPowers(findPowersForHeroVillain(hV));
            return hV;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    private static final String SQL_SELECT_HEROESVILLAINS_BY_POWER_ID
            = "SELECT hv.hv_id, hv.hv_name, hv.hv_description, hv.isVillain FROM HeroesVillains AS hv "
            + "INNER JOIN HVPowers AS hvp ON hv.hv_id = hvp.hv_id WHERE hvp.power_id = ?";
    @Override
    public List<HeroVillain> getHeroesVillainsByPowerId(int powerId) {
        // get heroes or villains with a specific power
        List<HeroVillain> hVList = jdbcTemplate.query(SQL_SELECT_HEROESVILLAINS_BY_POWER_ID, 
                new HeroVillainMapper(), powerId);
        // set the powers for each hero or villain
        return associatePowersWithHeroesVillains(hVList);
    }
    
    private static final String SQL_SELECT_ALL_HEROESVILLAINS
            = "SELECT * FROM HeroesVillains";
    @Override
    public List<HeroVillain> getAllHeroesVillains() {
        List<HeroVillain> hVList = jdbcTemplate.query(SQL_SELECT_ALL_HEROESVILLAINS, 
                new HeroVillainMapper());
        return associatePowersWithHeroesVillains(hVList);
    }
    
    //****************************HELPER METHODS FOR HeroesVillains*********************************
    private static final String SQL_INSERT_HVPOWERS
            = "INSERT INTO HVPowers (hv_id, power_id) VALUES (?, ?)";
    private void insertHVPowers(HeroVillain hV) {
        int hVId = hV.getId();
        List<Power> powers = hV.getPowers();
        
        // update the HVPowers bridge table with an entry for
        // each power of this HeroVillain
        for (Power currentPower : powers) {
            jdbcTemplate.update(SQL_INSERT_HVPOWERS, hVId, currentPower.getId());
        }
    }
    
    private static final String SQL_SELECT_POWERS_BY_HV_ID
            = "SELECT p.power_id, p.power_name FROM Powers AS p "
            + "INNER JOIN HVPowers AS hvp ON p.power_id = hvp.power_id "
            + "WHERE hvp.hv_id = ?";
    private List<Power> findPowersForHeroVillain(HeroVillain hV) {
        return jdbcTemplate.query(SQL_SELECT_POWERS_BY_HV_ID, new PowerMapper(),
                hV.getId());
    }
    
    private List<HeroVillain> associatePowersWithHeroesVillains(List<HeroVillain> hVList) {
        for (HeroVillain currentHV : hVList) {
            currentHV.setPowers(findPowersForHeroVillain(currentHV));
        }
        return hVList;
    }
    
    //**********************************************************************************************
    //**********************************************************************************************
    
    //*******************ORGANIZATION METHODS AND PREPARED STATEMENTS*******************************
    //**********************************************************************************************
    
    private static final String SQL_INSERT_ORGANIZATION
            = "INSERT INTO Organizations (org_name, org_description, phone, email, location_id) "
            + "VALUES (?, ?, ?, ?, ?)";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization org) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION, 
                org.getOrgName(),
                org.getOrgDescription(),
                org.getPhone(),
                org.getEmail(),
                org.getLocation().getId());
        
        int orgId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", 
                Integer.class);
        
        org.setId(orgId);
        
        insertHVOrganization(org);
    }
    
    private static final String SQL_DELETE_HVORGANIZATIONS_ORG_ID
            = "DELETE FROM HVOrganizations WHERE org_id = ?";
    private static final String SQL_DELETE_ORGANIZATION
            = "DELETE FROM Organizations WHERE org_id = ?";
    @Override
    public void deleteOrganization(int orgId) {
        jdbcTemplate.update(SQL_DELETE_HVORGANIZATIONS_ORG_ID, orgId);
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, orgId);
    }
   
    private static final String SQL_UPDATE_ORGANIZATION
            = "UPDATE Organizations SET org_name = ?, org_description = ?, "
            + "phone = ?, email = ?, location_id = ? WHERE org_id = ?";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateOrganization(Organization org) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION, 
                org.getOrgName(),
                org.getOrgDescription(),
                org.getPhone(),
                org.getEmail(),
                org.getLocation().getId(),
                org.getId());
        
        jdbcTemplate.update(SQL_DELETE_HVORGANIZATIONS_ORG_ID, org.getId());
        insertHVOrganization(org);
    }
    
    private static final String SQL_SELECT_ORGANIZATION
            = "SELECT * FROM Organizations WHERE org_id = ?";
    @Override
    public Organization getOrganizationById(int orgId) {
        try {
            Organization org = jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION, 
                new OrganizationMapper(), orgId);
            org.setLocation(findLocationForOrganization(org));
            org.setMetaHumans(findHeroesVillainsForOrganization(org));
            return org;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    private static final String SQL_SELECT_ORGANIZATIONS_BY_LOCATION_ID
            = "SELECT * FROM Organizations WHERE location_id = ?";
    @Override
    public List<Organization> getOrganizationsByLocationId(int locationId) {
        List<Organization> orgList = jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_BY_LOCATION_ID, 
                new OrganizationMapper(), locationId);
        
        return associateLocationAndHeroesVillainsWithOrganization(orgList);
    }
    
    private static final String SQL_SELECT_ORGANIZATIONS_BY_HV_ID
            = "SELECT o.org_id, o.org_name, o.org_description, o.phone, o.email "
            + "FROM Organizations AS o INNER JOIN HVOrganizations AS hvo "
            + "ON o.org_id = hvo.org_id WHERE hvo.hv_id = ?";
    @Override
    public List<Organization> getOrganizationsByHeroVillainId(int hvId) {
        List<Organization> orgList = jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_BY_HV_ID, 
                new OrganizationMapper(), hvId);
        
        return associateLocationAndHeroesVillainsWithOrganization(orgList);
    }
    
    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "SELECT * FROM Organizations";
    @Override
    public List<Organization> getAllOrganizations() {
        List<Organization> orgList = jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS, 
                new OrganizationMapper());
        
        return associateLocationAndHeroesVillainsWithOrganization(orgList);
    }
    
    //**********************HELPER METHODS FOR ORGANIZATION*****************************************
    
    private static final String SQL_INSERT_HVORGANIZATION
            = "INSERT INTO HVOrganizations (hv_id, org_id) VALUES (?, ?)";
    private void insertHVOrganization(Organization org) {
        int orgId = org.getId();
        List<HeroVillain> hvList = org.getMetaHumans();
        
        for (HeroVillain currentHV : hvList) {
            jdbcTemplate.update(SQL_INSERT_HVORGANIZATION, currentHV.getId(), 
                    orgId);
        }
    }
    
    private static final String SQL_SELECT_LOCATION_BY_ORG_ID
            = "SELECT l.location_id, l.location_name, l.location_description, "
            + "l.address, l.lat, l.lng FROM Locations AS l INNER JOIN Organizations AS o ON "
            + "l.location_id = o.location_id WHERE o.org_id = ?";
    private Location findLocationForOrganization(Organization org) {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_ORG_ID, 
                new LocationMapper(), org.getId());
    }
    
    private static final String SQL_SELECT_HEROESVILLAINS_BY_ORG_ID
            = "SELECT hv.hv_id, hv.hv_name, hv.hv_description, hv.isVillain "
            + "FROM HeroesVillains AS hv INNER JOIN HVOrganizations AS hvo "
            + "ON hv.hv_id = hvo.hv_id WHERE hvo.org_id = ?";
    private List<HeroVillain> findHeroesVillainsForOrganization(Organization org) {
        List<HeroVillain> hvList = jdbcTemplate.query(SQL_SELECT_HEROESVILLAINS_BY_ORG_ID, 
                new HeroVillainMapper(), org.getId());
        
        // borrowed the helper method, findPowerForHeroVillain(), from HeroesVillains 
        // section to get all powers associated with the HeroVillain
        for (HeroVillain currentHV : hvList) {
            currentHV.setPowers(findPowersForHeroVillain(currentHV));
        }
        
        return hvList;
    }
    
    private List<Organization> associateLocationAndHeroesVillainsWithOrganization(List<Organization> orgList) {
        for (Organization currentOrg : orgList) {
            currentOrg.setLocation(findLocationForOrganization(currentOrg));
            currentOrg.setMetaHumans(findHeroesVillainsForOrganization(currentOrg));
        }
        return orgList;
    }
    //**********************************************************************************************
    //**********************************************************************************************
}
