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
import java.math.BigDecimal;
import java.math.RoundingMode;
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

    //******************************FOR HEROESVILLAINS****************************************
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
        if ((request.getParameter("hvName") == null)
                || (request.getParameter("hvDescription") == null)
                || (request.getParameter("heroOrVillain") == null)) {

            return "createHero";
        }

        List<Power> powers = dao.getAllPowers();
        List<Power> powersForHero = new ArrayList<>();

        if (!(request.getParameter("hvName").equals(""))
                && !(request.getParameter("hvDescription").equals(""))
                && !(request.getParameter("heroOrVillain").equals(""))) {
            String name = request.getParameter("hvName");
            String description = request.getParameter("hvDescription");

            boolean villain;
            if (request.getParameter("heroOrVillain").equals("Hero")) {
                villain = false;
            } else {
                villain = true;
            }

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

            if (request.getParameter("hvName").equals("")) {
                model.addAttribute("hvName", "error");
            } else {
                model.addAttribute("hvName", request.getParameter("hvName"));
            }

            if (request.getParameter("hvDescription").equals("")) {
                model.addAttribute("hvDescription", "error");
            } else {
                model.addAttribute("hvDescription", request.getParameter("hvDescription"));
            }

            for (int i = 1; i <= powers.size(); i++) {
                if (request.getParameter("power" + i) != null) {
                    powersForHero.add(dao.getPowerById(Integer.parseInt(request.getParameter("power" + i))));
                }
            }
            model.addAttribute("hvPowers", powersForHero);

            List<Power> powersList = dao.getAllPowers();
            model.addAttribute("powersList", powersList);

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
    //******************************************************************************************************

    //********************************FOR LOCATIONS*********************************************************
    @RequestMapping(value = "/displayLocationsPage", method = RequestMethod.GET)
    public String displayLocationsPage(Model model) {
        List<Location> locationList = dao.getAllLocations();
        model.addAttribute("locationList", locationList);

        return "locations";
    }

    @RequestMapping(value = "/displayCreateLocationPage", method = RequestMethod.GET)
    public String displayCreateLocationPage() {
        return "createLocation";
    }

    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public String createLocation(HttpServletRequest request, Model model) {

        List<Location> locationList = dao.getAllLocations();
        model.addAttribute("locationList", locationList);

        if (request.getParameter("locationName") == null
                || request.getParameter("locationDescription") == null
                || request.getParameter("address") == null
                || request.getParameter("latitude") == null
                || request.getParameter("longitude") == null) {

            return "createLocation";
        }

        String name = request.getParameter("locationName");
        String descript = request.getParameter("locationDescription");
        String address = request.getParameter("address");
        String latStr = request.getParameter("latitude");
        String lngStr = request.getParameter("longitude");

        if (name.equals("")
                || descript.equals("")
                || address.equals("")
                || latStr.equals("")
                || lngStr.equals("")) {

            if (name.equals("")) {
                model.addAttribute("name", "error");
            } else {
                model.addAttribute("name", request.getParameter("locationName"));
            }

            if (descript.equals("")) {
                model.addAttribute("descript", "error");
            } else {
                model.addAttribute("descript", request.getParameter("locationDescription"));
            }

            if (address.equals("")) {
                model.addAttribute("address", "error");
            } else {
                model.addAttribute("address", request.getParameter("address"));
            }

            if (latStr.equals("")) {
                model.addAttribute("lat", "error");
            } else {
                model.addAttribute("lat", request.getParameter("latitude"));
            }

            if (lngStr.equals("")) {
                model.addAttribute("lng", "error");
            } else {
                model.addAttribute("lng", request.getParameter("longitude"));
            }

            return "createLocation";
        }

        BigDecimal lat;
        BigDecimal lng;

        try {
            lat = new BigDecimal(latStr);
        } catch (NumberFormatException e) {
            model.addAttribute("name", name);
            model.addAttribute("descript", descript);
            model.addAttribute("address", address);
            model.addAttribute("lat", "notNum");
            model.addAttribute("lng", lngStr);

            return "createLocation";
        }

        try {
            lng = new BigDecimal(lngStr);
        } catch (NumberFormatException e) {
            model.addAttribute("name", name);
            model.addAttribute("descript", descript);
            model.addAttribute("address", address);
            model.addAttribute("lat", latStr);
            model.addAttribute("lng", "notNum");

            return "createLocation";
        }

        lat = new BigDecimal(latStr);
        lng = new BigDecimal(lngStr);
        lat.setScale(6, RoundingMode.HALF_UP);
        lng.setScale(6, RoundingMode.HALF_UP);

        Location newLocation = new Location();
        newLocation.setLocationName(name);
        newLocation.setLocationDescription(descript);
        newLocation.setAddress(address);
        newLocation.setLat(lat);
        newLocation.setLng(lng);

        dao.addLocation(newLocation);

        return "locations";
    }

    @RequestMapping(value = "/displayEditLocationPage", method = RequestMethod.GET)
    public String displayEditLocationPage(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Location location = dao.getLocationById(id);

        model.addAttribute("id", id);
        model.addAttribute("name", location.getLocationName());
        model.addAttribute("descript", location.getLocationDescription());
        model.addAttribute("address", location.getAddress());
        model.addAttribute("lat", location.getLat().toString());
        model.addAttribute("lng", location.getLng().toString());

        return "editLocationPage";
    }

    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(HttpServletRequest request, Model model) {
        if ((request.getParameter("locationName") == null)
                || (request.getParameter("locationDescription") == null)
                || (request.getParameter("address") == null)
                || (request.getParameter("latitude") == null)
                || (request.getParameter("longitude") == null)) {

            // PROVIDE NULL ERROR PAGE LATER!
            return "redirect:displayLocationsPage";
        }

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("locationName");
        String descript = request.getParameter("locationDescription");
        String address = request.getParameter("address");
        String latStr = request.getParameter("latitude");
        String lngStr = request.getParameter("longitude");

        if (name.equals("")
                || descript.equals("")
                || address.equals("")
                || latStr.equals("")
                || lngStr.equals("")) {

            model.addAttribute("id", id);

            if (name.equals("")) {
                model.addAttribute("name", "error");
            } else {
                model.addAttribute("name", name);
            }

            if (descript.equals("")) {
                model.addAttribute("descript", "error");
            } else {
                model.addAttribute("descript", descript);
            }

            if (address.equals("")) {
                model.addAttribute("address", "error");
            } else {
                model.addAttribute("address", address);
            }

            if (latStr.equals("")) {
                model.addAttribute("lat", "error");
            } else {
                model.addAttribute("lat", latStr);
            }

            if (lngStr.equals("")) {
                model.addAttribute("lng", "error");
            } else {
                model.addAttribute("lng", lngStr);
            }

            return "editLocationPage";
        }

        BigDecimal lat;
        BigDecimal lng;

        try {
            lat = new BigDecimal(latStr);
        } catch (NumberFormatException e) {
            model.addAttribute("id", id);
            model.addAttribute("name", name);
            model.addAttribute("descript", descript);
            model.addAttribute("address", address);
            model.addAttribute("lat", "notNum");
            model.addAttribute("lng", lngStr);

            return "editLocationPage";
        }

        try {
            lng = new BigDecimal(lngStr);
        } catch (NumberFormatException e) {
            model.addAttribute("id", id);
            model.addAttribute("name", name);
            model.addAttribute("descript", descript);
            model.addAttribute("address", address);
            model.addAttribute("lat", latStr);
            model.addAttribute("lng", "notNum");

            return "editLocationPage";
        }

        lat = new BigDecimal(latStr);
        lng = new BigDecimal(lngStr);
        lat.setScale(6, RoundingMode.HALF_UP);
        lng.setScale(6, RoundingMode.HALF_UP);

        Location editLoc = dao.getLocationById(id);
        editLoc.setLocationName(name);
        editLoc.setLocationDescription(descript);
        editLoc.setAddress(address);
        editLoc.setLat(lat);
        editLoc.setLng(lng);

        dao.updateLocation(editLoc);

        return "redirect:displayLocationsPage";
    }
    
    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request) {
        
        int id = Integer.parseInt(request.getParameter("id"));
        dao.deleteLocation(id);
        
        return "redirect:displayLocationsPage";
    }
    //****************************************************************************************

    @RequestMapping(value = "/displayOrganizationsPage", method = RequestMethod.GET)
    public String displayOrganizationsPage(Model model) {
        List<Organization> orgList = dao.getAllOrganizations();
        model.addAttribute("orgList", orgList);

        return "organizations";
    }
    
    @RequestMapping(value = "/displayCreateOrg", method = RequestMethod.GET)
    public String displayCreateOrgPage(Model model) {
        List<HeroVillain> hvList = dao.getAllHeroesVillains();
        List<Location> locList = dao.getAllLocations();
        
        model.addAttribute("hvList", hvList);
        model.addAttribute("locList", locList);
        
        return "createOrg";
    }
    
    @RequestMapping(value = "/createOrg", method = RequestMethod.POST)
    public String createOrg(HttpServletRequest request, Model model) {
        List<HeroVillain> hvs = dao.getAllHeroesVillains();
        
        String name = request.getParameter("name");
        String descript = request.getParameter("description");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        int loc = Integer.parseInt(request.getParameter("location"));
        
//        for (int i = 1; i <= powers.size(); i++) {
//                if (request.getParameter("power" + i) != null) {
//                    powersForHero.add(dao.getPowerById(Integer.parseInt(request.getParameter("power" + i))));
//                }
//            }

        List<HeroVillain> hvsForOrg = new ArrayList<>();
        for (int i = 1; i <= hvs.size(); i++) {
            if (request.getParameter("hv" + i) != null) {
                hvsForOrg.add(dao.getHeroVillainById(Integer.parseInt(request.getParameter("hv" + i))));
            }
        }
        
        Location selectedLoc = dao.getLocationById(loc);
        
        Organization newOrg = new Organization();
        newOrg.setOrgName(name);
        newOrg.setOrgDescription(descript);
        newOrg.setPhone(phone);
        newOrg.setEmail(email);
        newOrg.setLocation(selectedLoc);
        newOrg.setMetaHumans(hvsForOrg);
        
        return "redirect:/displayOrganizationsPage";
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
