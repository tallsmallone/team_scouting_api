package com.simulationhockey.columbus.scout_api.api.userstatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_status")
public class UserStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer userId;
    private Boolean want;
    private Boolean avoid;
    private Boolean drafted;

    // Getters
    public Integer getId(){
        return this.id;
    }

    public Integer getUserId(){
        return this.userId;
    }

    public Boolean getWant(){
        return this.want;
    }

    public Boolean getAvoid(){
        return this.avoid;
    }

    public Boolean getDrafted(){
        return this.drafted;
    } 

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setWant(Boolean want) {
        this.want = want;
    }

    public void setAvoid(Boolean avoid) {
        this.avoid = avoid;
    }

    public void setDrafted(Boolean drafted) {
        this.drafted = drafted;
    }
}
