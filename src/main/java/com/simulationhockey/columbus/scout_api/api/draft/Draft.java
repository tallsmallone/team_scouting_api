package com.simulationhockey.columbus.scout_api.api.draft;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="draft")
public class Draft {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer userId;
    private String draftTeam;
    private Integer draftPick;

    // Getters
    public Integer getId(){
        return this.id;
    }

    public Integer getUserId(){
        return this.userId;
    }

    public String getDraftTeam(){
        return this.draftTeam;
    }

    public Integer getDraftPick(){
        return this.draftPick;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setDraftTeam(String draftTeam) {
        this.draftTeam = draftTeam;
    }

    public void setDraftPick(Integer draftPick) {
        this.draftPick = draftPick;
    }    
}
