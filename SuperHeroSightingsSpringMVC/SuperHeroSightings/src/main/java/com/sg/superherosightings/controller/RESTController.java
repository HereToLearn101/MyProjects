/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SuperHeroSightingsDao;
import com.sg.superherosightings.model.HeroVillain;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author tedis
 */
@CrossOrigin
@Controller
public class RESTController {
    private SuperHeroSightingsDao dao;
    
    @Inject
    public RESTController(SuperHeroSightingsDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/HeroVillain/{id}", method = RequestMethod.GET)
    @ResponseBody
    public HeroVillain getHeroVillainById(@PathVariable("id") int id) {
        return dao.getHeroVillainById(id);
    }
    
    @RequestMapping(value = "/Location/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Location getLocationById(@PathVariable("id") int id) {
        return dao.getLocationById(id);
    }
    
    @RequestMapping(value = "/Organization/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Organization getOrgById(@PathVariable("id") int id) {
        return dao.getOrganizationById(id);
    }
    
    @RequestMapping(value = "/Sighting/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Sighting getSightingById(@PathVariable("id") int id) {
        return dao.getSightingById(id);
    }
}
