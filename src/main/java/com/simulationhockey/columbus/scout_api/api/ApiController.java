package com.simulationhockey.columbus.scout_api.api;

import java.util.List;
import java.util.Optional;

import com.simulationhockey.columbus.scout_api.api.commenters.Commenters;
import com.simulationhockey.columbus.scout_api.api.commenters.CommentersRepository;
import com.simulationhockey.columbus.scout_api.api.teamcomments.TeamComments;
import com.simulationhockey.columbus.scout_api.api.teamcomments.TeamCommentsRepository;
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

    @Autowired 
    private CommentersRepository commentersRepository;

    @Autowired
    private TeamCommentsRepository teamCommentsRepository;

    // user information mappings
    @PostMapping(path="/user/add")
    public @ResponseBody String addNewUser(@RequestParam String username, 
        @RequestParam String playerLastName, @RequestParam String playerFirstName,
        @RequestParam String position, @RequestParam String team, @RequestParam String discordUsername,
        @RequestParam Integer tpe, @RequestParam String joined, @RequestParam String lastVisit,
        @RequestParam Boolean active, @RequestParam Boolean contacted, @RequestParam Boolean want,
        @RequestParam Boolean avoid, @RequestParam Boolean drafted, @RequestParam Integer draftPick) {
        
        List<UserInformation> existingUser = userInformationRepository.findByUsername(username);
        // check if the status already exists.  If it does, don't continue
        if (existingUser.size() > 0) {
            return "ERROR: user already exists";
        }

        userInformationRepository.addUserInformation(
            username,
            playerLastName,
            playerFirstName,
            position,
            team,
            discordUsername,
            tpe,
            joined,
            lastVisit,
            active,
            contacted,
            want,
            avoid,
            drafted,
            draftPick
        );

        return "OK";
    }

    @PostMapping(path="/user/{id}/update")
    public @ResponseBody String updateNewUser(@PathVariable Integer id, @RequestParam String username, 
        @RequestParam String playerLastName, @RequestParam String playerFirstName,
        @RequestParam String position, @RequestParam String team, @RequestParam String discordUsername,
        @RequestParam Integer tpe, @RequestParam String joined, @RequestParam String lastVisit,
        @RequestParam Boolean active, @RequestParam Boolean contacted, @RequestParam Boolean want,
        @RequestParam Boolean avoid, @RequestParam Boolean drafted, @RequestParam Integer draftPick) {
        
        // check if the status already exists.  If it does, don't continue
        if (!this.isUserPresent(id)) {
            return "ERROR: user information not present";
        }

        userInformationRepository.updateUserInformation(
            id,
            username,
            playerLastName,
            playerFirstName,
            position,
            team,
            discordUsername,
            tpe,
            joined,
            lastVisit,
            active,
            contacted,
            want,
            avoid,
            drafted,
            draftPick
        );

        return "OK";
    }

    @PostMapping(path="/user/{id}/delete")
    public @ResponseBody String deleteUser(@PathVariable Integer id) {
        // check if the status already exists.  If it does, don't continue
        if (!this.isUserPresent(id)) {
            return "ERROR: user information not present";
        }

        userInformationRepository.deleteUserInformation(id);

        return "OK";
    }

    @GetMapping(path="/user/all")
    public @ResponseBody List<UserInformation> getAllUsers() {
        return userInformationRepository.getAllUserInformation();
    }

    @GetMapping(path="/user/{id}")
    public @ResponseBody List<UserInformation> getSingleUser(@PathVariable Integer id) {
        return userInformationRepository.getUserInformation(id);
    }

    @GetMapping(path="/user/username/{username}")
    public @ResponseBody List<UserInformation> getUserByUsername(@PathVariable String username) {
        return userInformationRepository.findByUsername(username);
    }
    
    // commenter mappings
    @PostMapping(path="/commenters/add")
    public @ResponseBody String addCommenter(@RequestParam String username) {
        List<Commenters> commenters = commentersRepository.findByUsername(username);
        // check if the status already exists.  If it does, don't continue
        if (commenters.size() > 0) {
            return "ERROR: commenter already present";
        }
        
        commentersRepository.addCommenter(username);

        return "OK";
    }

    @PostMapping(path="/commenters/{id}/update")
    public @ResponseBody String updateCommenter(@PathVariable Integer id, @RequestParam String username) {
        // check if the status already exists.  If it does, don't continue
        if (!this.isCommenterPresent(id)) {
            return "ERROR: commenter does not exist";
        }
        
        commentersRepository.updateCommenter(id, username);

        return "OK";
    }

    @PostMapping(path="/commenters/{id}/delete")
    public @ResponseBody String deleteCommenter(@PathVariable Integer id, @RequestParam String username) {
        // check if the status already exists.  If it does, don't continue
        if (!this.isCommenterPresent(id)) {
            return "ERROR: commenter does not exist";
        }
        
        commentersRepository.deleteCommenter(id);

        return "OK";
    }

    @GetMapping(path="/commenters/all")
    public @ResponseBody Iterable<Commenters> getAllCommenters() {
        return commentersRepository.getAllCommenters();
    }

    // comments mappings
    @PostMapping(path="/user/{userId}/comment/add")
    public @ResponseBody String addNewComment(@PathVariable Integer userId,
        @RequestParam Integer commenterId, @RequestParam String comment) {
        
        if (!isUserPresent(userId)) {
            return "ERROR: User does not exist";
        }

        if (!this.isCommenterPresent(commenterId)) {
            return "ERROR: Commenter does not exist";
        }

        teamCommentsRepository.addComment(userId, commenterId, comment);

        return "OK";
    }

    @PostMapping(path="/user/{userId}/comment/{commentId}/update")
    public @ResponseBody String updateComment(@PathVariable Integer userId, @PathVariable Integer commentId,
        @RequestParam Integer commenterId, @RequestParam String comment) {
        
        if (!isUserPresent(userId)) {
            return "ERROR: User does not exist";
        }
        
        if (!this.isCommentPresent(commentId)) {
            return "ERROR: Comment doesn't exist";
        }

        teamCommentsRepository.updateComment(commentId, userId, commenterId, comment);

        return "OK";
    }

    @PostMapping(path="/user/{userId}/comment/{commentId}/delete")
    public @ResponseBody String deleteComment(@PathVariable Integer userId, @PathVariable Integer commentId,
        @RequestParam Integer commenterId, @RequestParam String comment) {
        
        if (!isUserPresent(userId)) {
            return "ERROR: User does not exist";
        }
        
        if (!this.isCommentPresent(commentId)) {
            return "ERROR: Comment doesn't exist";
        }

        teamCommentsRepository.deleteComment(commentId);

        return "OK";
    }

    @GetMapping(path="/user/{userId}/comment/all")
    public @ResponseBody List<TeamComments> getUserComments(@PathVariable Integer userId) {
        return teamCommentsRepository.getAllUserComments(userId);
    }

    // misc tools
    private Boolean isUserPresent(Integer id) {
        List<UserInformation> existingUser = userInformationRepository.getUserInformation(id);
        // check if the status already exists.  If it does, don't continue
        if (existingUser.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean isCommenterPresent(Integer id) {
        List<Commenters> existingUser = commentersRepository.getCommenter(id);
        // check if the status already exists.  If it does, don't continue
        if (existingUser.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean isCommentPresent(Integer id) {
        List<TeamComments> existingComment = teamCommentsRepository.getUserComment(id);
        // check if the status already exists.  If it does, don't continue
        if (existingComment.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
