package com.simulationhockey.columbus.scout_api.api;

import java.util.Optional;

import com.simulationhockey.columbus.scout_api.api.userinformation.UserInformation;
import com.simulationhockey.columbus.scout_api.api.userinformation.UserInformationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/api/v1")
public class ApiController {
    @Autowired
    private UserInformationRepository userInformationRepository;

    @PostMapping(path="/user/add")
    public @ResponseBody String addNewUser(@RequestParam String username, 
        @RequestParam String player_last_name, @RequestParam String player_first_name,
        @RequestParam String position, @RequestParam String team, @RequestParam String discord_username,
        @RequestParam Integer tpe, @RequestParam String joined, @RequestParam String last_visit,
        @RequestParam Boolean active, @RequestParam Boolean contacted) {
            UserInformation user = new UserInformation();
            user.setUsername(username);
            user.setPlayerLastName(player_last_name);
            user.setPlayerFirstName(player_first_name);
            user.setPosition(position);
            user.setTeam(team);
            user.setDiscordUsername(discord_username);
            user.setTpe(tpe);
            user.setJoined(joined);
            user.setLastVisit(last_visit);
            user.setActive(active);
            user.setContacted(contacted);
            userInformationRepository.save(user);

            return "Saved";
        }

    @GetMapping(path="/user/all")
    public @ResponseBody Iterable<UserInformation> getAllUsers() {
        return userInformationRepository.findAll();
    }

    @GetMapping(path="/user/{id}")
    public @ResponseBody Optional<UserInformation> getSingleUser(@PathVariable Integer id) {
        return userInformationRepository.findById(id);
    }

    @GetMapping(path="/user/username/{username}")
    public @ResponseBody Iterable<UserInformation> getUserByUsername(@PathVariable String username) {
        return userInformationRepository.findByUsername(username);
    }
}
