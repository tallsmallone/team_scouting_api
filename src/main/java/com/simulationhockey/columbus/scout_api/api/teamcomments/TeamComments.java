package com.simulationhockey.columbus.scout_api.api.teamcomments;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="team_comments")
public class TeamComments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer user_id;
    private Integer commenter_id;
    private String comment;

    // Getters
    public Integer getId(){
        return this.id;
    }

    public Integer getUserId(){
        return this.user_id;
    }

    public Integer getCommenterId(){
        return this.commenter_id;
    }

    public String getComment(){
        return this.comment;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer user_id) {
        this.user_id = user_id;
    }

    public void setCommenterId(Integer commenter_id) {
        this.commenter_id = commenter_id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
