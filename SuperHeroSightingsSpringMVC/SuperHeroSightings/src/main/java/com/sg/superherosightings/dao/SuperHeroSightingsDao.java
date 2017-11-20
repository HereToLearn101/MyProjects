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
import java.util.List;

/**
 *
 * @author tedis
 */
public interface SuperHeroSightingsDao {
    
    // LOCATIONS
    public void addLocation(Location location);
    
    public void deleteLocation(int locationId);
    
    public void updateLocation(Location location);
    
    public Location getLocationById(int locationId);
    
    public List<Location> getAllLocations();
    
    // POWERS
    public void addPower(Power power);
    
    public void deletePower(int powerId);
    
    public void updatePower(Power power);
    
    public Power getPowerById(int powerId);
    
    public List<Power> getAllPowers();
    
    // HEROESVILLAINS
    public void addHeroVillain(HeroVillain hV);
    
    public void deleteHeroVillain(int id);
    
    public void updateHeroVillain(HeroVillain hV);
    
    public HeroVillain getHeroVillainById(int id);
    
    public List<HeroVillain> getHeroesVillainsByPowerId(int powerId);
    
    public List<HeroVillain> getAllHeroesVillains();
    
    // SIGHTINGS
    public void addSighting(Sighting sighting);
    
    public void deleteSighting(int sightingId);
    
    public void updateSighting(Sighting sighting);
    
    public Sighting getSightingById(int sightingId);
    
    public List<Sighting> getSightingsByLocationId(int locationId);
    
    public List<Sighting> getSightingsByHeroVillainId(int hvId);
    
    public List<Sighting> getAllSightings();
    
    // ORGANIZATIONS
    public void addOrganization(Organization org);
    
    public void deleteOrganization(int orgId);
    
    public void updateOrganization(Organization org);
    
    public Organization getOrganizationById(int orgId);
    
    public List<Organization> getOrganizationsByLocationId(int locationId);
    
    public List<Organization> getOrganizationsByHeroVillainId(int hvId);
    
    public List<Organization> getAllOrganizations();
}
