package com.simulationhockey.columbus.scout_api.api.commenters;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="draft")
public class Commenters {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;

    // Getters
    public Integer getId(){
        return this.id;
    }

    public String getUsername(){
        return this.username;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
}
