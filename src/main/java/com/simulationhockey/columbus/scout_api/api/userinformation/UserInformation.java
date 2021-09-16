package com.simulationhockey.columbus.scout_api.api.userinformation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_information")
public class UserInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;
    private String playerLastName;
    private String playerFirstName;
    private String position;
    private String team;
    private String discordUsername;
    private Integer tpe;
    private String joined;
    private String lastVisit;
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
        return this.playerLastName;
    }

    public String getPlayerFirstName() {
        return this.playerFirstName;
    }

    public String getPosition() {
        return this.position;
    }

    public String getTeam() {
        return this.team;
    }

    public String getDiscordUsername() {
        return this.discordUsername;
    }

    public Integer getTpe() {
        return this.tpe;
    }

    public String getJoined() {
        return this.joined;
    }

    public String getLastVisit() {
        return this.lastVisit;
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

    public void setPlayerLastName(String playerLastName) {
        this.playerLastName = playerLastName;
    }

    public void setPlayerFirstName(String playerFirstName) {
        this.playerFirstName = playerFirstName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setDiscordUsername(String discordUsername) {
        this.discordUsername = discordUsername;
    }

    public void setTpe(Integer tpe) {
        this.tpe = tpe;
    }

    public void setJoined(String joined) {
        this.joined = joined;
    }

    public void setLastVisit(String lastVisit) {
        this.lastVisit = lastVisit;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setContacted(Boolean contacted) {
        this.contacted = contacted;
    }
}
