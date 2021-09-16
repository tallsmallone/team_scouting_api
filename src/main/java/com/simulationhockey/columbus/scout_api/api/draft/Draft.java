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

    private Integer user_id;
    private String draft_team;
    private Integer draft_pick;

    // Getters
    public Integer getId(){
        return this.id;
    }

    public Integer getUserId(){
        return this.user_id;
    }

    public String getDraftTeam(){
        return this.draft_team;
    }

    public Integer getDraftPick(){
        return this.draft_pick;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer user_id) {
        this.user_id = user_id;
    }

    public void setDraftTeam(String draft_team) {
        this.draft_team = draft_team;
    }

    public void setDraftPick(Integer draft_pick) {
        this.draft_pick = draft_pick;
    }    
}
