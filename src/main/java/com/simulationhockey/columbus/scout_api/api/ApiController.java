package com.simulationhockey.columbus.scout_api.api;

import java.util.List;
import java.util.Optional;

import com.simulationhockey.columbus.scout_api.api.commenters.Commenters;
import com.simulationhockey.columbus.scout_api.api.commenters.CommentersRepository;
import com.simulationhockey.columbus.scout_api.api.userinformation.UserInformation;
import com.simulationhockey.columbus.scout_api.api.userinformation.UserInformationRepository;
import com.simulationhockey.columbus.scout_api.api.userstatus.UserStatus;
import com.simulationhockey.columbus.scout_api.api.userstatus.UserStatusRepository;

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

    @Autowired
    private UserStatusRepository userStatusRepository;

    @Autowired CommentersRepository commentersRepository;

    // user information mappings
    @PostMapping(path="/user/add")
    public @ResponseBody String addNewUser(@RequestParam String username, 
        @RequestParam String playerLastName, @RequestParam String playerFirstName,
        @RequestParam String position, @RequestParam String team, @RequestParam String discordUsername,
        @RequestParam Integer tpe, @RequestParam String joined, @RequestParam String lastVisit,
        @RequestParam Boolean active, @RequestParam Boolean contacted) {
        
        List<UserInformation> existingUser = userInformationRepository.findByUsername(username);
        // check if the status already exists.  If it does, don't continue
        if (existingUser.size() > 0) {
            return "ERROR: user information already present";
        }

        UserInformation user = new UserInformation();
        user.setUsername(username);
        user.setPlayerLastName(playerLastName);
        user.setPlayerFirstName(playerFirstName);
        user.setPosition(position);
        user.setTeam(team);
        user.setDiscordUsername(discordUsername);
        user.setTpe(tpe);
        user.setJoined(joined);
        user.setLastVisit(lastVisit);
        user.setActive(active);
        user.setContacted(contacted);
        userInformationRepository.save(user);

        return "OK";
    }

    @PostMapping(path="/user/{id}/update")
    public @ResponseBody String updateNewUser(@PathVariable Integer id, @RequestParam String username, 
        @RequestParam String playerLastName, @RequestParam String playerFirstName,
        @RequestParam String position, @RequestParam String team, @RequestParam String discordUsername,
        @RequestParam Integer tpe, @RequestParam String joined, @RequestParam String lastVisit,
        @RequestParam Boolean active, @RequestParam Boolean contacted) {
        
        Optional<UserInformation> existingUser = userInformationRepository.findById(id);
        // check if the status already exists.  If it does, don't continue
        if (!existingUser.isPresent()) {
            return "ERROR: user information not present";
        }

        UserInformation user = existingUser.get();
        user.setPlayerLastName(playerLastName);
        user.setPlayerFirstName(playerFirstName);
        user.setPosition(position);
        user.setTeam(team);
        user.setDiscordUsername(discordUsername);
        user.setTpe(tpe);
        user.setJoined(joined);
        user.setLastVisit(lastVisit);
        user.setActive(active);
        user.setContacted(contacted);
        userInformationRepository.save(user);

        return "OK";
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
    public @ResponseBody List<UserInformation> getUserByUsername(@PathVariable String username) {
        return userInformationRepository.findByUsername(username);
    }

    // user status mappings
    @PostMapping(path="/user/{userId}/status/add")
    public @ResponseBody String addUserStatus(@PathVariable Integer userId,
        @RequestParam Boolean want, @RequestParam Boolean avoid, @RequestParam Boolean drafted) {
        
        List<UserStatus> existingStatus = userStatusRepository.findByUserId(userId);
        // check if the status already exists.  If it does, don't continue
        if (existingStatus.size() > 0) {
            return "ERROR: user status already present";
        }

        UserStatus status = new UserStatus();
        status.setUserId(userId);
        status.setWant(want);
        status.setAvoid(avoid);
        status.setDrafted(drafted);
        userStatusRepository.save(status);

        return "OK";
    }

    @PostMapping(path="/user/{userId}/status/update")
    public @ResponseBody String updateUserStatus(@PathVariable Integer userId,
        @RequestParam Boolean want, @RequestParam Boolean avoid, @RequestParam Boolean drafted) {
        
        List<UserStatus> existingStatus = userStatusRepository.findByUserId(userId);
        // check if the status already exists.  If it does not, don't continue
        if (existingStatus.size() < 1) {
            return "ERROR: user status not present";
        }

        UserStatus status = existingStatus.get(0);
        status.setWant(want);
        status.setAvoid(avoid);
        status.setDrafted(drafted);
        userStatusRepository.save(status);

        return "OK";
    }

    @GetMapping(path="/user/{userId}/status")
    public @ResponseBody List<UserStatus> getUserStatus(@PathVariable Integer userId) {
        return userStatusRepository.findByUserId(userId);
    }

    // commenter mappings
    @PostMapping(path="/commenters/add")
    public @ResponseBody String addCommenter(@RequestParam String username) {
        List<Commenters> commenters = commentersRepository.findByUsername(username);
        // check if the status already exists.  If it does, don't continue
        if (commenters.size() > 0) {
            return "ERROR: commenter already present";
        }
        
        Commenters commenter = new Commenters();
        commenter.setUsername(username);
        commentersRepository.save(commenter);

        return "OK";
    }

    @PostMapping(path="/commenters/{id}/update")
    public @ResponseBody String updateCommenter(@PathVariable Integer id, @RequestParam String username) {
        Optional<Commenters> commenters = commentersRepository.findById(id);
        // check if the status already exists.  If it does, don't continue
        if (!commenters.isPresent()) {
            return "ERROR: commenter does not exist";
        }
        
        Commenters commenter = commenters.get();
        commenter.setUsername(username);
        commentersRepository.save(commenter);

        return "OK";
    }
}
