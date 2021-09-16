package com.simulationhockey.columbus.scout_api.api;

import java.util.List;
import java.util.Optional;

import com.simulationhockey.columbus.scout_api.api.commenters.Commenters;
import com.simulationhockey.columbus.scout_api.api.commenters.CommentersRepository;
import com.simulationhockey.columbus.scout_api.api.draft.Draft;
import com.simulationhockey.columbus.scout_api.api.draft.DraftRepository;
import com.simulationhockey.columbus.scout_api.api.teamcomments.TeamComments;
import com.simulationhockey.columbus.scout_api.api.teamcomments.TeamCommentsRepository;
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

    @Autowired 
    private CommentersRepository commentersRepository;

    @Autowired
    private TeamCommentsRepository teamCommentsRepository;

    @Autowired
    private DraftRepository draftRepository;

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

    @PostMapping(path="/user/{id}/delete")
    public @ResponseBody String deleteUser(@PathVariable Integer id, @RequestParam String username, 
        @RequestParam String playerLastName, @RequestParam String playerFirstName,
        @RequestParam String position, @RequestParam String team, @RequestParam String discordUsername,
        @RequestParam Integer tpe, @RequestParam String joined, @RequestParam String lastVisit,
        @RequestParam Boolean active, @RequestParam Boolean contacted) {
        
        Optional<UserInformation> existingUser = userInformationRepository.findById(id);
        // check if the status already exists.  If it does, don't continue
        if (!existingUser.isPresent()) {
            return "ERROR: user information not present";
        }

        userInformationRepository.delete(existingUser.get());

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
        
        if (!isUserPresent(userId)) {
            return "ERROR: User does not exist";
        }

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

    @PostMapping(path="/user/{userId}/status/delete")
    public @ResponseBody String deleteUserStatus(@PathVariable Integer userId,
        @RequestParam Boolean want, @RequestParam Boolean avoid, @RequestParam Boolean drafted) {
        
        List<UserStatus> existingStatus = userStatusRepository.findByUserId(userId);
        // check if the status already exists.  If it does not, don't continue
        if (existingStatus.size() < 1) {
            return "ERROR: user status not present";
        }

        userStatusRepository.delete(existingStatus.get(0));

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

    @PostMapping(path="/commenters/{id}/delete")
    public @ResponseBody String deleteCommenter(@PathVariable Integer id, @RequestParam String username) {
        Optional<Commenters> commenters = commentersRepository.findById(id);
        // check if the status already exists.  If it does, don't continue
        if (!commenters.isPresent()) {
            return "ERROR: commenter does not exist";
        }
        
        commentersRepository.delete(commenters.get());

        return "OK";
    }

    @GetMapping(path="/commenters/all")
    public @ResponseBody Iterable<Commenters> getAllCommenters() {
        return commentersRepository.findAll();
    }

    // comments mappings
    @PostMapping(path="/user/{userId}/comment/add")
    public @ResponseBody String addNewComment(@PathVariable Integer userId,
        @RequestParam Integer commenterId, @RequestParam String comment) {
        
        if (!isUserPresent(userId)) {
            return "ERROR: User does not exist";
        }

        Optional<Commenters> commenter = commentersRepository.findById(commenterId);
        if (!commenter.isPresent()) {
            return "ERROR: Commenter does not exist";
        }
        TeamComments teamComment = new TeamComments();
        teamComment.setUserId(userId);
        teamComment.setCommenterId(commenterId);
        teamComment.setComment(comment);

        teamCommentsRepository.save(teamComment);
        return "OK";
    }

    @PostMapping(path="/user/{userId}/comment/{commentId}/update")
    public @ResponseBody String updateComment(@PathVariable Integer userId, @PathVariable Integer commentId,
        @RequestParam Integer commenterId, @RequestParam String comment) {
        
        if (!isUserPresent(userId)) {
            return "ERROR: User does not exist";
        }

        Optional<TeamComments> teamComments = teamCommentsRepository.findById(commentId);
        
        if (!teamComments.isPresent()) {
            return "ERROR: Comment doesn't exist";
        }

        TeamComments teamComment = teamComments.get();
        teamComment.setComment(comment);

        teamCommentsRepository.save(teamComment);
        return "OK";
    }

    @PostMapping(path="/user/{userId}/comment/{commentId}/delete")
    public @ResponseBody String deleteComment(@PathVariable Integer userId, @PathVariable Integer commentId,
        @RequestParam Integer commenterId, @RequestParam String comment) {
        
        if (!isUserPresent(userId)) {
            return "ERROR: User does not exist";
        }

        Optional<TeamComments> teamComments = teamCommentsRepository.findById(commentId);
        
        if (!teamComments.isPresent()) {
            return "ERROR: Comment doesn't exist";
        }

        teamCommentsRepository.delete(teamComments.get());
        return "OK";
    }

    @GetMapping(path="/user/{userId}/comment/all")
    public @ResponseBody Optional<TeamComments> getUserComments(@PathVariable Integer userId) {
        return teamCommentsRepository.findById(userId);
    }

    // draft end points
    @PostMapping(path="/user/{userId}/draft/add")
    public @ResponseBody String addDraftInformation(@PathVariable Integer userId,
        @RequestParam String draftTeam, @RequestParam Integer draftPick) {

            if (!isUserPresent(userId)) {
                return "ERROR: User does not exist";
            }
    
            List<Draft> draftInfo = draftRepository.findByUserId(userId);
            if (draftInfo.size() > 0) {
                return "ERROR: Draft info already exists";
            }

            Draft draft = new Draft();
            draft.setUserId(userId);
            draft.setDraftTeam(draftTeam);
            draft.setDraftPick(draftPick);

            draftRepository.save(draft);

            return "OK";
        }

    @PostMapping(path="/user/{userId}/draft/update")
    public @ResponseBody String updateDraft(@PathVariable Integer userId,
        @RequestParam String draftTeam, @RequestParam Integer draftPick) {
        
        if (!isUserPresent(userId)) {
            return "ERROR: User does not exist";
        }

        List<Draft> draftInfo = draftRepository.findByUserId(userId);
        
        if (draftInfo.size() < 1) {
            return "ERROR: Draft info does not exist";
        }

        Draft draft = draftInfo.get(1);
        draft.setDraftTeam(draftTeam);
        draft.setDraftPick(draftPick);

        draftRepository.save(draft);
        return "OK";
    }

    @PostMapping(path="/user/{userId}/draft/delete")
    public @ResponseBody String deleteDraft(@PathVariable Integer userId) {
        
        if (!isUserPresent(userId)) {
            return "ERROR: User does not exist";
        }

        List<Draft> draftInfo = draftRepository.findByUserId(userId);
        
        if (draftInfo.size() < 1) {
            return "ERROR: Draft info does not exist";
        }

        draftRepository.delete(draftInfo.get(1));
        return "OK";
    }

    // misc tools
    private Boolean isUserPresent(Integer id) {
        Optional<UserInformation> user = userInformationRepository.findById(id);
        return user.isPresent();
    }
}
