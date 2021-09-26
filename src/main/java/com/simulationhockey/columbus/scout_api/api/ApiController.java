package com.simulationhockey.columbus.scout_api.api;

import java.util.List;

import com.simulationhockey.columbus.scout_api.api.commenters.Commenters;
import com.simulationhockey.columbus.scout_api.api.commenters.CommentersRepository;
import com.simulationhockey.columbus.scout_api.api.teamcomments.TeamComments;
import com.simulationhockey.columbus.scout_api.api.teamcomments.TeamCommentsRepository;
import com.simulationhockey.columbus.scout_api.api.userinformation.UserInformation;
import com.simulationhockey.columbus.scout_api.api.userinformation.UserInformationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    // Test functions
    @PostMapping(path="/test")
    public @ResponseBody ResponseEntity<String> test(@RequestBody String username) {
        return ResponseEntity.status(HttpStatus.OK).body(username);
    }

    @PostMapping(path="/test2")
    public @ResponseBody String test2(@RequestBody String username) {
        return "username";
    }

    // user information mappings
    @PostMapping(path="/user/add")
    public @ResponseBody ResponseEntity<String> addNewUser(@RequestBody UserInformation user) {

        List<UserInformation> existingUser = userInformationRepository.findByUsername(user.getUsername());
        // check if the status already exists.  If it does, don't continue
        if (existingUser.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User already exists!");
        }


        System.out.print("test");

        userInformationRepository.addUserInformation(
            user.getUsername(),
            user.getPlayerLastName(),
            user.getPlayerFirstName(),
            user.getPosition(),
            user.getTeam(),
            user.getDiscordUsername(),
            user.getTpe(),
            user.getJoined(),
            user.getLastVisit(),
            user.getActive(),
            user.getContacted(),
            user.getWant(),
            user.getAvoid(),
            user.getDrafted(),
            user.getDraftPick()
        );

        System.out.print("tesrtyt");
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @PostMapping(path="/user/{id}/update")
    public @ResponseBody ResponseEntity<String> updateNewUser(@PathVariable Integer id, @RequestBody UserInformation user) {

        // check if the status already exists.  If it does, don't continue
        if (!this.isUserPresent(id)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User doesn't exist!");
        }

        userInformationRepository.updateUserInformation(
            id,
            user.getUsername(),
            user.getPlayerLastName(),
            user.getPlayerFirstName(),
            user.getPosition(),
            user.getTeam(),
            user.getDiscordUsername(),
            user.getTpe(),
            user.getJoined(),
            user.getLastVisit(),
            user.getActive(),
            user.getContacted(),
            user.getWant(),
            user.getAvoid(),
            user.getDrafted(),
            user.getDraftPick()
        );

        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @PostMapping(path="/user/{id}/delete")
    public @ResponseBody ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        // check if the status already exists.  If it does, don't continue
        if (!this.isUserPresent(id)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User doesn't exist!");
        }

        userInformationRepository.deleteUserInformation(id);

        return ResponseEntity.status(HttpStatus.OK).body("OK");
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
    public @ResponseBody ResponseEntity<String> addCommenter(@RequestBody Commenters commenter) {
        List<Commenters> commenters = commentersRepository.findByUsername(commenter.getUsername());
        // check if the status already exists.  If it does, don't continue
        if (commenters.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Commenter already exists!");
        }

        commentersRepository.addCommenter(commenter.getUsername());

        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @PostMapping(path="/commenters/{id}/update")
    public @ResponseBody ResponseEntity<String> updateCommenter(@PathVariable Integer id, @RequestBody Commenters commenter) {
        // check if the status already exists.  If it does, don't continue
        if (!this.isCommenterPresent(id)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Commenter doesn't exist!");
        }

        commentersRepository.updateCommenter(id, commenter.getUsername());

        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @PostMapping(path="/commenters/{id}/delete")
    public @ResponseBody ResponseEntity<String> deleteCommenter(@PathVariable Integer id) {
        // check if the status already exists.  If it does, don't continue
        if (!this.isCommenterPresent(id)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Commenter doesn't exist!");
        }

        commentersRepository.deleteCommenter(id);

        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @GetMapping(path="/commenters/all")
    public @ResponseBody Iterable<Commenters> getAllCommenters() {
        return commentersRepository.getAllCommenters();
    }

    // comments mappings
    @PostMapping(path="/user/{userId}/comment/add")
    public @ResponseBody ResponseEntity<String> addNewComment(@PathVariable Integer userId,
        @RequestBody TeamComments teamComments) {

        if (!isUserPresent(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User doesn't exist!");
        }

        if (!this.isCommenterPresent(teamComments.getCommenterId())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Commenter doesn't exist!");
        }

        teamCommentsRepository.addComment(userId, teamComments.getCommenterId(), teamComments.getComment());

        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @PostMapping(path="/user/{userId}/comment/{commentId}/update")
    public @ResponseBody ResponseEntity<String> updateComment(@PathVariable Integer userId, @PathVariable Integer commentId,
        @RequestBody TeamComments teamComments) {

        if (!isUserPresent(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User doesn't exist!");
        }

        if (!this.isCommentPresent(commentId)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Commenter doesn't exist!");
        }

        teamCommentsRepository.updateComment(commentId, userId, teamComments.getCommenterId(), teamComments.getComment());

        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @PostMapping(path="/user/{userId}/comment/{commentId}/delete")
    public @ResponseBody ResponseEntity<String> deleteComment(@PathVariable Integer userId, @PathVariable Integer commentId) {

        if (!isUserPresent(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User doesn't exist!");
        }

        if (!this.isCommentPresent(commentId)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Commenter doesn't exist!");
        }

        teamCommentsRepository.deleteComment(commentId);

        return ResponseEntity.status(HttpStatus.OK).body("OK");
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
