package com.codeup.blog.blog.controllers;


import com.codeup.blog.blog.models.*;
import com.codeup.blog.blog.repositories.*;
import com.sun.mail.auth.MD4;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BrowserController {

    private HobbyRepository hobbyDao;
    private TraitRepository traitDao;
    private ProfileRepository profileDao;
    private LocationRepository locationDao;
    private MeetUpRepository meetUpDao;

    public BrowserController(HobbyRepository hobbyDao, TraitRepository traitDao, ProfileRepository profileDao, LocationRepository locationDao, MeetUpRepository meetUpDao){
        this.hobbyDao = hobbyDao;
        this.profileDao = profileDao;
        this.traitDao = traitDao;
        this.locationDao = locationDao;
        this.meetUpDao = meetUpDao;
    }

    @GetMapping("/browse/hobbies-index")
    public String showHobbiesIndex(Model vModel){
        vModel.addAttribute("hobbies", hobbyDao.findAll());
        return "browse/hobbies-index";
    }

    @GetMapping("/browse/hobbies/{id}")
    public String showIndividualHobby(@PathVariable long id, Model vModel){
        Hobby hobby = hobbyDao.getOne(id);
        List<Profile> profileList = new ArrayList<>();

        for (Profile profile : profileDao.findAll()) {
            for (Hobby userHobby: profile.getHobbies()){
                if (userHobby == hobby){
                    profileList.add(profile);
                }
            }
        }
        vModel.addAttribute("hobby", hobby);
        vModel.addAttribute("profiles", profileList);
        return "browse/hobby";
    }

    @GetMapping("/browse/traits-index")
    public String showTraitIndex(Model vModel){
        vModel.addAttribute("traits", traitDao.findAll());
        return "browse/traits-index";
    }

    @GetMapping("/browse/traits/{id}")
    public String showIndividualTrait(@PathVariable long id, Model vModel){
        Trait trait = traitDao.getOne(id);
        List<Profile> profileList = new ArrayList<>();

        for (Profile profile : profileDao.findAll()) {
            for (Trait userTrait: profile.getTraits()){
                if (userTrait == trait){
                    profileList.add(profile);
                }
            }
        }
        vModel.addAttribute("trait", trait);
        vModel.addAttribute("profiles", profileList);
        return "browse/trait";
    }

    @GetMapping("/browse/locations-index")
    public String showLocationIndex(Model vModel){
        vModel.addAttribute("locations", locationDao.findAll());
        return "browse/locations-index";
    }


    @GetMapping("/browse/location/{id}")
    public String showIndividualLocation(@PathVariable long id, Model vModel){
        Location location = locationDao.getOne(id);
        List<Profile> profileList = new ArrayList<>();

        for (Profile profile : profileDao.findAll()) {
            for (Location userLocation: profile.getLocation()){
                if (userLocation == location){
                    profileList.add(profile);
                }
            }
        }
        vModel.addAttribute("location", location);
        vModel.addAttribute("profiles", profileList);
        return "browse/location";
    }


    @GetMapping("/browse/meetups-locations-index/")
    public String showMeetUpLocationsIndex(Model vModel){
        vModel.addAttribute("locations", locationDao.findAll());
        return "browse/meetups-locations-index";
    }

    @GetMapping("/browse/meetups-locations/{id}")
    public String showMeetUpLocation(Model vModel, @PathVariable long id){
        Location location = locationDao.getOne(id);
        List<MeetUp> meetUpList = new ArrayList<>();

        for (MeetUp meetUp : meetUpDao.findAll()) {
            if (meetUp.getLocation() == location){
                meetUpList.add(meetUp);
            }
        }
        vModel.addAttribute("location", location);
        vModel.addAttribute("meetups", meetUpList);

        return "/browse/meetups-location";
    }


    @GetMapping("/browse/")
    public String showBrowseIndex(){
        return "browse/index";
    }

}
