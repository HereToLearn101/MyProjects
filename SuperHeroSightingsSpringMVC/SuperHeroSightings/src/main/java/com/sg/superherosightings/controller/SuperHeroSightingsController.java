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
import com.sg.superherosightings.model.Power;
import com.sg.superherosightings.model.Sighting;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author tedis
 */
@Controller
public class SuperHeroSightingsController {

    private SuperHeroSightingsDao dao;

    @Inject
    public SuperHeroSightingsController(SuperHeroSightingsDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHomePage(Model model) {
        List<Sighting> listOfSightings = dao.getAllSightings();
        model.addAttribute("listOfSightings", listOfSightings);
        
        return "home";
    }

    @RequestMapping(value = "/displayHeroesVillainsPage", method = RequestMethod.GET)
    public String displayHeroesVillainsPage(Model model) {
        List<HeroVillain> hvList = dao.getAllHeroesVillains();
        model.addAttribute("hvList", hvList);

        return "heroesVillains";
    }

    @RequestMapping(value = "/displayCreateHeroVillainPage", method = RequestMethod.GET)
    public String displayCreateHeroVillainPage(Model model) {
        List<Power> powersList = dao.getAllPowers();
        model.addAttribute("powersList", powersList);

        return "createHero";
    }

    @RequestMapping(value = "/createHeroVillain", method = RequestMethod.POST)
    public String createHeroVillain(HttpServletRequest request, Model model) {

        if (!(request.getParameter("hvName").equals("")) || !(request.getParameter("hvDescription").equals(""))) {
            String name = request.getParameter("hvName");
            String description = request.getParameter("hvDescription");

            boolean villain;
            if (request.getParameter("heroOrVillain").equals("Hero")) {
                villain = false;
            } else {
                villain = true;
            }

            List<Power> powers = dao.getAllPowers();
            List<Power> powersForHero = new ArrayList<>();
            for (int i = 1; i <= powers.size(); i++) {
                if (request.getParameter("power" + i) != null) {
                    powersForHero.add(dao.getPowerById(Integer.parseInt(request.getParameter("power" + i))));
                }
            }

            HeroVillain newHV = new HeroVillain();
            newHV.sethVName(name);
            newHV.sethVDescription(description);
            newHV.setIsVillain(villain);
            newHV.setPowers(powersForHero);

            dao.addHeroVillain(newHV);
        } else {
            // Only exectured
            List<Power> powersList = dao.getAllPowers();
            model.addAttribute("powersList", powersList);
            model.addAttribute("error", "Please don't leave name and description blank!");
            
            return "createHero";
        }

        return "redirect:/displayHeroesVillainsPage";
    }

    @RequestMapping(value = "/deleteHV", method = RequestMethod.GET)
    public String deleteHeroVillain(HttpServletRequest request) {
        int hvId = Integer.parseInt(request.getParameter("hvId"));
        dao.deleteHeroVillain(hvId);

        return "redirect:/displayHeroesVillainsPage";
    }

    @RequestMapping(value = "/displayEditHVPage", method = RequestMethod.GET)
    public String displayEditHVPage(HttpServletRequest request, Model model) {
        int hvId = Integer.parseInt(request.getParameter("hvId"));
        HeroVillain hv = dao.getHeroVillainById(hvId);
        List<Power> powersList = dao.getAllPowers();

        model.addAttribute("hvPowers", hv.getPowers());
        model.addAttribute("hv", hv);
        model.addAttribute("powersList", powersList);

        return "editHVPage";
    }

    @RequestMapping(value = "/editHeroVillain", method = RequestMethod.POST)
    public String editHeroVillain(HttpServletRequest request) {
        int hvId = Integer.parseInt(request.getParameter("hvId"));
        String name = request.getParameter("hvName");
        String description = request.getParameter("hvDescription");

        boolean villain;
        if (request.getParameter("heroOrVillain").equals("Hero")) {
            villain = false;
        } else {
            villain = true;
        }

        List<Power> powers = dao.getAllPowers();
        List<Power> powersForHero = new ArrayList<>();
        for (int i = 1; i <= powers.size(); i++) {
            if (request.getParameter("power" + i) != null) {
                powersForHero.add(dao.getPowerById(Integer.parseInt(request.getParameter("power" + i))));
            }
        }

        HeroVillain hvToEdit = dao.getHeroVillainById(hvId);
        hvToEdit.sethVName(name);
        hvToEdit.sethVDescription(description);
        hvToEdit.setIsVillain(villain);
        hvToEdit.setPowers(powersForHero);

        dao.updateHeroVillain(hvToEdit);

        return "redirect:/displayHeroesVillainsPage";
    }

    @RequestMapping(value = "/displayLocationsPage", method = RequestMethod.GET)
    public String displayLocationsPage(Model model) {
        List<Location> locationList = dao.getAllLocations();
        model.addAttribute("locationList", locationList);

        return "locations";
    }

    @RequestMapping(value = "/displayOrganizationsPage", method = RequestMethod.GET)
    public String displayOrganizationsPage(Model model) {
        List<Organization> orgList = dao.getAllOrganizations();
        model.addAttribute("orgList", orgList);

        return "organizations";
    }

    @RequestMapping(value = "/displaySightingsPage", method = RequestMethod.GET)
    public String displaySightingsPage(Model model) {
        List<Sighting> sightingList = dao.getAllSightings();
        model.addAttribute("sightingList", sightingList);

        return "sightings";
    }
    
    @RequestMapping(value = "/displaySightingDetails", method = RequestMethod.GET)
    public String displaySightingDetails(HttpServletRequest request, Model model) {
        
        return "sightingDetails";
    }

    //******************************SIGHTING COMPARATOR******************************************
    // Planning to use later to sort date
    private static final class OrgCompare implements Comparator<Sighting> {

        @Override
        public int compare(Sighting o1, Sighting o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    }
}
