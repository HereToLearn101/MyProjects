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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author tedis
 */
public class SuperHeroSightingsDaoTest {

    private SuperHeroSightingsDao dao;

    public SuperHeroSightingsDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("sightingsDao", SuperHeroSightingsDao.class);

        List<Sighting> sightingList = dao.getAllSightings();
        for (Sighting sighting : sightingList) {
            dao.deleteSighting(sighting.getId());
        }
        
        List<Organization> orgList = dao.getAllOrganizations();
        for (Organization org : orgList) {
            dao.deleteOrganization(org.getId());
        }

        List<HeroVillain> hvList = dao.getAllHeroesVillains();
        for (HeroVillain hV : hvList) {
            dao.deleteHeroVillain(hV.getId());
        }

        List<Location> locations = dao.getAllLocations();
        for (Location location : locations) {
            dao.deleteLocation(location.getId());
        }

        List<Power> powers = dao.getAllPowers();
        for (Power power : powers) {
            dao.deletePower(power.getId());
        }
    }

    @After
    public void tearDown() {
    }

    //************************LOCATION TESTS*****************************************
    /**
     * Test of addLocation method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testAddLocation() {
        Location location = new Location();
        location.setLocationName("Tedville");
        location.setLocationDescription("Home of Ted Talks");
        location.setAddress("10000 Falls Louisville, KY");
        location.setLat(new BigDecimal("1.150000"));
        location.setLng(new BigDecimal("1.150000"));

        dao.addLocation(location);

        Location locationFromDao = dao.getLocationById(location.getId());

        assertEquals(locationFromDao, location);
    }

    /**
     * Test of deleteLocation method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testDeleteLocation() {
        Location location = new Location();
        location.setLocationName("Tedville");
        location.setLocationDescription("Home of Ted Talks");
        location.setAddress("10000 Falls Louisville, KY");
        location.setLat(new BigDecimal("1.150000"));
        location.setLng(new BigDecimal("1.150000"));

        dao.addLocation(location);

        Location locationFromDao = dao.getLocationById(location.getId());
        assertEquals(locationFromDao, location);
        dao.deleteLocation(location.getId());
        assertNull(dao.getLocationById(location.getId()));
    }

    /**
     * Test of updateLocation method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testUpdateLocation() {
        Location location = new Location();
        location.setLocationName("Tedville");
        location.setLocationDescription("Home of Ted Talks");
        location.setAddress("10000 Falls Louisville, KY");
        location.setLat(new BigDecimal("1.150000"));
        location.setLng(new BigDecimal("1.150000"));

        dao.addLocation(location);

        location.setLocationName("Heroesville");
        dao.updateLocation(location);

        Location locationFromDaoAfterUpdate = dao.getLocationById(location.getId());
        assertEquals(locationFromDaoAfterUpdate, location);
    }

    /**
     * Test of getLocationById method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testGetLocationById() {
        // Already tested with addLocation method above
    }

    /**
     * Test of getAllLocations method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testGetAllLocations() {
        Location location = new Location();
        location.setLocationName("Tedville");
        location.setLocationDescription("Home of Ted Talks");
        location.setAddress("10000 Falls Louisville, KY");
        location.setLat(new BigDecimal("1.150000"));
        location.setLng(new BigDecimal("1.150000"));

        Location location2 = new Location();
        location2.setLocationName("Villainville");
        location2.setLocationDescription("Home of villains");
        location2.setAddress("10000 Evil Risen Louisville, KY");
        location2.setLat(new BigDecimal("1.110000"));
        location2.setLng(new BigDecimal("1.110000"));

        dao.addLocation(location);
        dao.addLocation(location2);

        List<Location> locations = dao.getAllLocations();
        assertEquals(2, locations.size());
    }

    //*******************************POWER TESTS***************************************
    @Test
    public void testAddPower() {
        Power power = new Power();
        power.setPowerName("Super Strength");

        dao.addPower(power);

        Power powerFromDao = dao.getPowerById(power.getId());

        assertEquals(powerFromDao, power);
    }

    @Test
    public void testDeletePower() {
        Power power = new Power();
        power.setPowerName("Super Strength");

        dao.addPower(power);

        Power powerFromDao = dao.getPowerById(power.getId());
        assertEquals(powerFromDao, power);
        dao.deletePower(power.getId());
        assertNull(dao.getPowerById(power.getId()));
    }

    @Test
    public void testUpdatePower() {
        Power power = new Power();
        power.setPowerName("Super Strength");

        dao.addPower(power);

        power.setPowerName("Speed Force");
        dao.updatePower(power);

        Power powerFromDaoAfterUpdate = dao.getPowerById(power.getId());
        assertEquals(powerFromDaoAfterUpdate, power);
    }

    @Test
    public void testGetPowerById() {
        // Already tested with addPower method
    }

    @Test
    public void testGetAllPowers() {
        Power power = new Power();
        power.setPowerName("Super Strength");

        Power power2 = new Power();
        power2.setPowerName("Speed Force");

        dao.addPower(power);
        dao.addPower(power2);

        List<Power> powers = dao.getAllPowers();
        assertEquals(2, powers.size());
    }

    //***************************HEROVILLAIN TESTS***********************************
    @Test
    public void testAddHeroVillain() {
        Power power = new Power();
        power.setPowerName("Super Strength");

        Power power2 = new Power();
        power2.setPowerName("Flight");

        dao.addPower(power);
        dao.addPower(power2);

        List<Power> powers = new ArrayList();
        powers.add(power);
        powers.add(power2);

        HeroVillain hV = new HeroVillain();
        hV.sethVName("Mr. Awesome");
        hV.sethVDescription("An awesome hero!");
        hV.setIsVillain(false);
        hV.setPowers(powers);

        dao.addHeroVillain(hV);

        HeroVillain hVFromDao = dao.getHeroVillainById(hV.getId());

        assertEquals(hVFromDao, hV);
    }

    @Test
    public void testDeleteHeroVillain() {
        Power power = new Power();
        power.setPowerName("Super Strength");

        Power power2 = new Power();
        power2.setPowerName("Flight");

        dao.addPower(power);
        dao.addPower(power2);

        List<Power> powers = new ArrayList();
        powers.add(power);
        powers.add(power2);

        HeroVillain hV = new HeroVillain();
        hV.sethVName("Mr. Awesome");
        hV.sethVDescription("An awesome hero!");
        hV.setIsVillain(false);
        hV.setPowers(powers);

        dao.addHeroVillain(hV);

        HeroVillain hVFromDao = dao.getHeroVillainById(hV.getId());
        assertEquals(hVFromDao, hV);
        dao.deleteHeroVillain(hV.getId());
        assertNull(dao.getHeroVillainById(hV.getId()));
    }

    @Test
    public void testUpdateHeroVillain() {
        Power power = new Power();
        power.setPowerName("Super Strength");

        Power power2 = new Power();
        power2.setPowerName("Flight");

        dao.addPower(power);
        dao.addPower(power2);

        List<Power> powers = new ArrayList();
        powers.add(power);
        powers.add(power2);

        HeroVillain hV = new HeroVillain();
        hV.sethVName("Mr. Awesome");
        hV.sethVDescription("An awesome hero!");
        hV.setIsVillain(false);
        hV.setPowers(powers);

        dao.addHeroVillain(hV);

        hV.setIsVillain(true);
        dao.updateHeroVillain(hV);

        HeroVillain hVFromDao = dao.getHeroVillainById(hV.getId());
        assertEquals(hVFromDao, hV);
    }

    @Test
    public void testGetHeroVillain() {
        // already tested with methods above
    }

    @Test
    public void testGetHeroesByPowerId() {
        Power power = new Power();
        power.setPowerName("Super Strength");

        Power power2 = new Power();
        power2.setPowerName("Flight");

        Power power3 = new Power();
        power3.setPowerName("Psychic");

        dao.addPower(power);
        dao.addPower(power2);
        dao.addPower(power3);

        List<Power> powers = new ArrayList();
        powers.add(power);
        powers.add(power2);

        List<Power> powers2 = new ArrayList();
        powers2.add(power2);
        powers2.add(power3);

        List<Power> powers3 = new ArrayList();
        powers3.add(power);
        powers3.add(power3);

        HeroVillain hV = new HeroVillain();
        hV.sethVName("Mr. Awesome");
        hV.sethVDescription("An awesome hero!");
        hV.setIsVillain(false);
        hV.setPowers(powers);

        HeroVillain hV2 = new HeroVillain();
        hV2.sethVName("Mr. Hood");
        hV2.sethVDescription("Mysterious hero...");
        hV2.setIsVillain(false);
        hV2.setPowers(powers2);

        HeroVillain hV3 = new HeroVillain();
        hV3.sethVName("Thing");
        hV3.sethVDescription("something");
        hV3.setIsVillain(true);
        hV3.setPowers(powers3);

        dao.addHeroVillain(hV);
        dao.addHeroVillain(hV2);
        dao.addHeroVillain(hV3);

        List<HeroVillain> heroesWithFlight = dao.getHeroesVillainsByPowerId(power2.getId());
        assertEquals(2, heroesWithFlight.size());
    }

    @Test
    public void testGetAllHeroesVillains() {
        Power power = new Power();
        power.setPowerName("Super Strength");

        Power power2 = new Power();
        power2.setPowerName("Flight");

        Power power3 = new Power();
        power3.setPowerName("Psychic");

        dao.addPower(power);
        dao.addPower(power2);
        dao.addPower(power3);

        List<Power> powers = new ArrayList();
        powers.add(power);
        powers.add(power2);

        List<Power> powers2 = new ArrayList();
        powers2.add(power2);
        powers2.add(power3);

        List<Power> powers3 = new ArrayList();
        powers3.add(power);
        powers3.add(power3);

        HeroVillain hV = new HeroVillain();
        hV.sethVName("Mr. Awesome");
        hV.sethVDescription("An awesome hero!");
        hV.setIsVillain(false);
        hV.setPowers(powers);

        HeroVillain hV2 = new HeroVillain();
        hV2.sethVName("Mr. Hood");
        hV2.sethVDescription("Mysterious hero...");
        hV2.setIsVillain(false);
        hV2.setPowers(powers2);

        HeroVillain hV3 = new HeroVillain();
        hV3.sethVName("Thing");
        hV3.sethVDescription("something");
        hV3.setIsVillain(true);
        hV3.setPowers(powers3);

        dao.addHeroVillain(hV);
        dao.addHeroVillain(hV2);
        dao.addHeroVillain(hV3);

        List<HeroVillain> hVList = dao.getAllHeroesVillains();
        assertEquals(3, hVList.size());
    }

    //********************************SIGHTING TESTS**********************************
    @Test
    public void testAddSighting() {
        Power power = new Power();
        power.setPowerName("Super Strength");

        Power power2 = new Power();
        power2.setPowerName("Flight");

        dao.addPower(power);
        dao.addPower(power2);

        List<Power> powers = new ArrayList();
        powers.add(power);
        powers.add(power2);

        HeroVillain hV = new HeroVillain();
        hV.sethVName("Mr. Awesome");
        hV.sethVDescription("An awesome hero!");
        hV.setIsVillain(false);
        hV.setPowers(powers);

        dao.addHeroVillain(hV);

        List<HeroVillain> hvList = new ArrayList();
        hvList.add(hV);

        Location location = new Location();
        location.setLocationName("Zootopia");
        location.setLocationDescription("Nice place!");
        location.setAddress("1000 Feel That, Louisville KY");
        location.setLat(new BigDecimal("1.150000"));
        location.setLng(new BigDecimal("1.150000"));

        dao.addLocation(location);

        LocalDate date = LocalDate.of(2017, 01, 12);

        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setLocation(location);
        sighting.sethVList(hvList);

        dao.addSighting(sighting);

        Sighting sightingFromDao = dao.getSightingById(sighting.getId());

        assertEquals(sightingFromDao, sighting);
    }

    @Test
    public void testDeleteSighting() {
        Power power = new Power();
        power.setPowerName("Super Strength");

        Power power2 = new Power();
        power2.setPowerName("Flight");

        dao.addPower(power);
        dao.addPower(power2);

        List<Power> powers = new ArrayList();
        powers.add(power);
        powers.add(power2);

        HeroVillain hV = new HeroVillain();
        hV.sethVName("Mr. Awesome");
        hV.sethVDescription("An awesome hero!");
        hV.setIsVillain(false);
        hV.setPowers(powers);

        dao.addHeroVillain(hV);

        List<HeroVillain> hvList = new ArrayList();
        hvList.add(hV);

        Location location = new Location();
        location.setLocationName("Zootopia");
        location.setLocationDescription("Nice place!");
        location.setAddress("1000 Feel That, Louisville KY");
        location.setLat(new BigDecimal("1.150000"));
        location.setLng(new BigDecimal("1.150000"));

        dao.addLocation(location);

        LocalDate date = LocalDate.of(2017, 01, 12);

        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setLocation(location);
        sighting.sethVList(hvList);

        dao.addSighting(sighting);

        Sighting sightingFromDao = dao.getSightingById(sighting.getId());
        assertEquals(sightingFromDao, sighting);
        dao.deleteSighting(sighting.getId());
        assertNull(dao.getSightingById(sighting.getId()));
    }

    @Test
    public void testUpdateSighting() {
        Power power = new Power();
        power.setPowerName("Super Strength");

        Power power2 = new Power();
        power2.setPowerName("Flight");

        dao.addPower(power);
        dao.addPower(power2);

        List<Power> powers = new ArrayList();
        powers.add(power);
        powers.add(power2);

        HeroVillain hV = new HeroVillain();
        hV.sethVName("Mr. Awesome");
        hV.sethVDescription("An awesome hero!");
        hV.setIsVillain(false);
        hV.setPowers(powers);

        dao.addHeroVillain(hV);

        List<HeroVillain> hvList = new ArrayList();
        hvList.add(hV);

        Location location = new Location();
        location.setLocationName("Zootopia");
        location.setLocationDescription("Nice place!");
        location.setAddress("1000 Feel That, Louisville KY");
        location.setLat(new BigDecimal("1.150000"));
        location.setLng(new BigDecimal("1.150000"));

        dao.addLocation(location);

        LocalDate date = LocalDate.of(2017, 01, 12);

        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setLocation(location);
        sighting.sethVList(hvList);

        dao.addSighting(sighting);

        Sighting sightingFromDao = dao.getSightingById(sighting.getId());
        assertEquals(sightingFromDao, sighting);

        LocalDate date2 = LocalDate.of(2017, 02, 03);
        sighting.setDate(date2);

        dao.updateSighting(sighting);
        Sighting sightingFromDaoAfterUpdate = dao.getSightingById(sighting.getId());
        assertEquals(sightingFromDaoAfterUpdate, sighting);
    }

    @Test
    public void testGetSightingById() {
        // already tested with methods above
    }

    @Test
    public void testGetSightingByLocationId() {
        Power power = new Power();
        power.setPowerName("Super Strength");

        Power power2 = new Power();
        power2.setPowerName("Flight");

        dao.addPower(power);
        dao.addPower(power2);

        List<Power> powers = new ArrayList();
        powers.add(power);
        powers.add(power2);

        HeroVillain hV = new HeroVillain();
        hV.sethVName("Mr. Awesome");
        hV.sethVDescription("An awesome hero!");
        hV.setIsVillain(false);
        hV.setPowers(powers);

        dao.addHeroVillain(hV);

        List<HeroVillain> hvList = new ArrayList();
        hvList.add(hV);

        Location location = new Location();
        location.setLocationName("Zootopia");
        location.setLocationDescription("Nice place!");
        location.setAddress("1000 Feel That, Louisville KY");
        location.setLat(new BigDecimal("1.150000"));
        location.setLng(new BigDecimal("1.150000"));

        dao.addLocation(location);

        LocalDate date = LocalDate.of(2017, 01, 12);
        LocalDate date2 = LocalDate.of(2017, 02, 03);

        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setLocation(location);
        sighting.sethVList(hvList);

        Sighting sighting2 = new Sighting();
        sighting2.setDate(date2);
        sighting2.setLocation(location);
        sighting2.sethVList(hvList);

        dao.addSighting(sighting);
        dao.addSighting(sighting2);

        List<Sighting> sightingList = dao.getSightingsByLocationId(location.getId());
        assertEquals(2, sightingList.size());
    }

    @Test
    public void testGetSightingsByHeroVillainId() {
        Power power = new Power();
        power.setPowerName("Super Strength");

        Power power2 = new Power();
        power2.setPowerName("Flight");

        dao.addPower(power);
        dao.addPower(power2);

        List<Power> powers = new ArrayList();
        powers.add(power);
        powers.add(power2);

        HeroVillain hV = new HeroVillain();
        hV.sethVName("Mr. Awesome");
        hV.sethVDescription("An awesome hero!");
        hV.setIsVillain(false);
        hV.setPowers(powers);

        dao.addHeroVillain(hV);

        List<HeroVillain> hvList = new ArrayList();
        hvList.add(hV);

        Location location = new Location();
        location.setLocationName("Zootopia");
        location.setLocationDescription("Nice place!");
        location.setAddress("1000 Feel That, Louisville KY");
        location.setLat(new BigDecimal("1.150000"));
        location.setLng(new BigDecimal("1.150000"));

        dao.addLocation(location);

        LocalDate date = LocalDate.of(2017, 01, 12);
        LocalDate date2 = LocalDate.of(2017, 02, 03);

        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setLocation(location);
        sighting.sethVList(hvList);

        Sighting sighting2 = new Sighting();
        sighting2.setDate(date2);
        sighting2.setLocation(location);
        sighting2.sethVList(hvList);

        dao.addSighting(sighting);
        dao.addSighting(sighting2);

        List<Sighting> sightingList = dao.getSightingsByHeroVillainId(hV.getId());
        assertEquals(2, sightingList.size());
    }

    @Test
    public void testGetAllSightings() {
        Power power = new Power();
        power.setPowerName("Super Strength");

        Power power2 = new Power();
        power2.setPowerName("Flight");

        dao.addPower(power);
        dao.addPower(power2);

        List<Power> powers = new ArrayList();
        powers.add(power);
        powers.add(power2);

        HeroVillain hV = new HeroVillain();
        hV.sethVName("Mr. Awesome");
        hV.sethVDescription("An awesome hero!");
        hV.setIsVillain(false);
        hV.setPowers(powers);

        dao.addHeroVillain(hV);

        List<HeroVillain> hvList = new ArrayList();
        hvList.add(hV);

        Location location = new Location();
        location.setLocationName("Zootopia");
        location.setLocationDescription("Nice place!");
        location.setAddress("1000 Feel That, Louisville KY");
        location.setLat(new BigDecimal("1.150000"));
        location.setLng(new BigDecimal("1.150000"));

        dao.addLocation(location);

        LocalDate date = LocalDate.of(2017, 01, 12);
        LocalDate date2 = LocalDate.of(2017, 02, 03);

        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setLocation(location);
        sighting.sethVList(hvList);

        Sighting sighting2 = new Sighting();
        sighting2.setDate(date2);
        sighting2.setLocation(location);
        sighting2.sethVList(hvList);

        dao.addSighting(sighting);
        dao.addSighting(sighting2);

        List<Sighting> sightingList = dao.getAllSightings();
        assertEquals(2, sightingList.size());
    }
    
    //************************ORGANIZATION TESTS***************************************
    @Test
    public void testAddOrganization() {
        Power power = new Power();
        power.setPowerName("Super Strength");

        Power power2 = new Power();
        power2.setPowerName("Flight");

        dao.addPower(power);
        dao.addPower(power2);

        List<Power> powers = new ArrayList();
        powers.add(power);
        powers.add(power2);

        HeroVillain hV = new HeroVillain();
        hV.sethVName("Mr. Awesome");
        hV.sethVDescription("An awesome hero!");
        hV.setIsVillain(false);
        hV.setPowers(powers);

        dao.addHeroVillain(hV);

        List<HeroVillain> hvList = new ArrayList();
        hvList.add(hV);

        Location location = new Location();
        location.setLocationName("Zootopia");
        location.setLocationDescription("Nice place!");
        location.setAddress("1000 Feel That, Louisville KY");
        location.setLat(new BigDecimal("1.150000"));
        location.setLng(new BigDecimal("1.150000"));

        dao.addLocation(location);
        
        Organization org = new Organization();
        org.setOrgName("Justice League");
        org.setOrgDescription("Organization of Heroes");
        org.setPhone("555-555");
        org.setEmail("justice@jl.com");
        org.setLocation(location);
        org.setMetaHumans(hvList);
        
        dao.addOrganization(org);
        
        Organization orgFromDao = dao.getOrganizationById(org.getId());
        assertEquals(orgFromDao, org);
    }
    
    @Test
    public void testDeleteOrganization() {
        Power power = new Power();
        power.setPowerName("Super Strength");

        Power power2 = new Power();
        power2.setPowerName("Flight");

        dao.addPower(power);
        dao.addPower(power2);

        List<Power> powers = new ArrayList();
        powers.add(power);
        powers.add(power2);

        HeroVillain hV = new HeroVillain();
        hV.sethVName("Mr. Awesome");
        hV.sethVDescription("An awesome hero!");
        hV.setIsVillain(false);
        hV.setPowers(powers);

        dao.addHeroVillain(hV);

        List<HeroVillain> hvList = new ArrayList();
        hvList.add(hV);

        Location location = new Location();
        location.setLocationName("Zootopia");
        location.setLocationDescription("Nice place!");
        location.setAddress("1000 Feel That, Louisville KY");
        location.setLat(new BigDecimal("1.150000"));
        location.setLng(new BigDecimal("1.150000"));

        dao.addLocation(location);
        
        Organization org = new Organization();
        org.setOrgName("Justice League");
        org.setOrgDescription("Organization of Heroes");
        org.setPhone("555-555");
        org.setEmail("justice@jl.com");
        org.setLocation(location);
        org.setMetaHumans(hvList);
        
        dao.addOrganization(org);
        
        Organization orgFromDao = dao.getOrganizationById(org.getId());
        assertEquals(orgFromDao, org);
        dao.deleteOrganization(org.getId());
        assertNull(dao.getOrganizationById(org.getId()));
    }
    
    @Test
    public void testUpdateOrganization() {
        Power power = new Power();
        power.setPowerName("Super Strength");

        Power power2 = new Power();
        power2.setPowerName("Flight");

        dao.addPower(power);
        dao.addPower(power2);

        List<Power> powers = new ArrayList();
        powers.add(power);
        powers.add(power2);

        HeroVillain hV = new HeroVillain();
        hV.sethVName("Mr. Awesome");
        hV.sethVDescription("An awesome hero!");
        hV.setIsVillain(false);
        hV.setPowers(powers);

        dao.addHeroVillain(hV);

        List<HeroVillain> hvList = new ArrayList();
        hvList.add(hV);

        Location location = new Location();
        location.setLocationName("Zootopia");
        location.setLocationDescription("Nice place!");
        location.setAddress("1000 Feel That, Louisville KY");
        location.setLat(new BigDecimal("1.150000"));
        location.setLng(new BigDecimal("1.150000"));

        dao.addLocation(location);
        
        Organization org = new Organization();
        org.setOrgName("Justice League");
        org.setOrgDescription("Organization of Heroes");
        org.setPhone("555-555");
        org.setEmail("justice@jl.com");
        org.setLocation(location);
        org.setMetaHumans(hvList);
        
        dao.addOrganization(org);
        
        org.setOrgName("Avengers");
        dao.updateOrganization(org);
        Organization orgFromDaoAfterUpdate = dao.getOrganizationById(org.getId());
        assertEquals(orgFromDaoAfterUpdate, org);
    }
    
    @Test
    public void testGetOrganizationById() {
        // Already tested with other methods above
    }
    
    @Test
    public void testGetOrganizationsByLocationId() {
        Power power = new Power();
        power.setPowerName("Super Strength");

        Power power2 = new Power();
        power2.setPowerName("Flight");

        dao.addPower(power);
        dao.addPower(power2);

        List<Power> powers = new ArrayList();
        powers.add(power);
        powers.add(power2);

        HeroVillain hv = new HeroVillain();
        hv.sethVName("Mr. Awesome");
        hv.sethVDescription("An awesome hero!");
        hv.setIsVillain(false);
        hv.setPowers(powers);
        
        HeroVillain hv2 = new HeroVillain();
        hv2.sethVName("Mr. AwesomeOpposit");
        hv2.sethVDescription("An evil super-powered being!");
        hv2.setIsVillain(true);
        hv2.setPowers(powers);

        dao.addHeroVillain(hv);
        dao.addHeroVillain(hv2);

        List<HeroVillain> hvList = new ArrayList();
        hvList.add(hv);
        
        List<HeroVillain> evilHVList = new ArrayList();
        evilHVList.add(hv2);

        Location location = new Location();
        location.setLocationName("Zootopia");
        location.setLocationDescription("Nice place!");
        location.setAddress("1000 Feel That, Louisville KY");
        location.setLat(new BigDecimal("1.150000"));
        location.setLng(new BigDecimal("1.150000"));

        dao.addLocation(location);
        
        Organization org = new Organization();
        org.setOrgName("Justice League");
        org.setOrgDescription("Organization of Heroes");
        org.setPhone("555-555");
        org.setEmail("justice@jl.com");
        org.setLocation(location);
        org.setMetaHumans(hvList);
        
        Organization org2 = new Organization();
        org2.setOrgName("Villain League");
        org2.setOrgDescription("Organization of Villains");
        org2.setPhone("111-1111");
        org2.setEmail("villain@vl.com");
        org2.setLocation(location);
        org2.setMetaHumans(hvList);
        
        dao.addOrganization(org);
        dao.addOrganization(org2);
        
        List<Organization> orgList = dao.getOrganizationsByLocationId(location.getId());
        assertEquals(2, orgList.size());
    }
    
    @Test
    public void testGetOrganizationsByHeroVillainId() {
        Power power = new Power();
        power.setPowerName("Super Strength");

        Power power2 = new Power();
        power2.setPowerName("Flight");

        dao.addPower(power);
        dao.addPower(power2);

        List<Power> powers = new ArrayList();
        powers.add(power);
        powers.add(power2);

        HeroVillain hv = new HeroVillain();
        hv.sethVName("Mr. Awesome");
        hv.sethVDescription("An awesome hero!");
        hv.setIsVillain(false);
        hv.setPowers(powers);
        
        HeroVillain hv2 = new HeroVillain();
        hv2.sethVName("Mr. AwesomeOpposite");
        hv2.sethVDescription("An evil super-powered being!");
        hv2.setIsVillain(true);
        hv2.setPowers(powers);

        dao.addHeroVillain(hv);
        dao.addHeroVillain(hv2);

        List<HeroVillain> hvList = new ArrayList();
        hvList.add(hv);
        
        List<HeroVillain> evilHVList = new ArrayList();
        evilHVList.add(hv2);

        Location location = new Location();
        location.setLocationName("Zootopia");
        location.setLocationDescription("Nice place!");
        location.setAddress("1000 Feel That, Louisville KY");
        location.setLat(new BigDecimal("1.150000"));
        location.setLng(new BigDecimal("1.150000"));

        dao.addLocation(location);
        
        Organization org = new Organization();
        org.setOrgName("Justice League");
        org.setOrgDescription("Organization of Heroes");
        org.setPhone("555-555");
        org.setEmail("justice@jl.com");
        org.setLocation(location);
        org.setMetaHumans(hvList);
        
        Organization org2 = new Organization();
        org2.setOrgName("Villain League");
        org2.setOrgDescription("Organization of Villains");
        org2.setPhone("111-1111");
        org2.setEmail("villain@vl.com");
        org2.setLocation(location);
        org2.setMetaHumans(evilHVList);
        
        dao.addOrganization(org);
        dao.addOrganization(org2);
        
        List<Organization> orgList = dao.getOrganizationsByHeroVillainId(hv.getId());
        assertEquals(1, orgList.size());
    }
    
    @Test
    public void testGetAllOrganizations() {
        Power power = new Power();
        power.setPowerName("Super Strength");

        Power power2 = new Power();
        power2.setPowerName("Flight");

        dao.addPower(power);
        dao.addPower(power2);

        List<Power> powers = new ArrayList();
        powers.add(power);
        powers.add(power2);

        HeroVillain hv = new HeroVillain();
        hv.sethVName("Mr. Awesome");
        hv.sethVDescription("An awesome hero!");
        hv.setIsVillain(false);
        hv.setPowers(powers);
        
        HeroVillain hv2 = new HeroVillain();
        hv2.sethVName("Mr. AwesomeOpposite");
        hv2.sethVDescription("An evil super-powered being!");
        hv2.setIsVillain(true);
        hv2.setPowers(powers);

        dao.addHeroVillain(hv);
        dao.addHeroVillain(hv2);

        List<HeroVillain> hvList = new ArrayList();
        hvList.add(hv);
        
        List<HeroVillain> evilHVList = new ArrayList();
        evilHVList.add(hv2);

        Location location = new Location();
        location.setLocationName("Zootopia");
        location.setLocationDescription("Nice place!");
        location.setAddress("1000 Feel That, Louisville KY");
        location.setLat(new BigDecimal("1.150000"));
        location.setLng(new BigDecimal("1.150000"));

        dao.addLocation(location);
        
        Organization org = new Organization();
        org.setOrgName("Justice League");
        org.setOrgDescription("Organization of Heroes");
        org.setPhone("555-555");
        org.setEmail("justice@jl.com");
        org.setLocation(location);
        org.setMetaHumans(hvList);
        
        Organization org2 = new Organization();
        org2.setOrgName("Villain League");
        org2.setOrgDescription("Organization of Villains");
        org2.setPhone("111-1111");
        org2.setEmail("villain@vl.com");
        org2.setLocation(location);
        org2.setMetaHumans(evilHVList);
        
        dao.addOrganization(org);
        dao.addOrganization(org2);
        
        List<Organization> orgList = dao.getAllOrganizations();
        assertEquals(2, orgList.size());
    }
}
