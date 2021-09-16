package com.simulationhockey.columbus.scout_api.api.userinformation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;
    private String player_last_name;
    private String player_first_name;
    private String position;
    private String team;
    private String discord_username;
    private Integer tpe;
    private String joined;
    private String last_visit;
    private Boolean active;
    private Boolean contacted;

    // Getters
    public Integer getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPlayerLastName() {
        return this.player_last_name;
    }

    public String getPlayerFirstName() {
        return this.player_first_name;
    }

    public String getPosition() {
        return this.position;
    }

    public String getTeam() {
        return this.team;
    }

    public String getDiscordUsername() {
        return this.discord_username;
    }

    public Integer getTpe() {
        return this.tpe;
    }

    public String getJoined() {
        return this.joined;
    }

    public String getLastVisit() {
        return this.last_visit;
    }

    public Boolean getActive() {
        return this.active;
    }

    public Boolean getContacted() {
        return this.contacted;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPlayerLastName(String player_last_name) {
        this.player_last_name = player_last_name;
    }

    public void setPlayerFirstName(String player_first_name) {
        this.player_first_name = player_first_name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setDiscordUsername(String discord_username) {
        this.discord_username = discord_username;
    }

    public void setTpe(Integer tpe) {
        this.tpe = tpe;
    }

    public void setJoined(String joined) {
        this.joined = joined;
    }

    public void setLastVisit(String last_visit) {
        this.last_visit = last_visit;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setContacted(Boolean contacted) {
        this.contacted = contacted;
    }
}
