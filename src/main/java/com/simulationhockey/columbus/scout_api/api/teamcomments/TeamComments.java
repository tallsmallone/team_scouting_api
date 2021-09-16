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

    private Integer userId;
    private Integer commenterId;
    private String comment;

    // Getters
    public Integer getId(){
        return this.id;
    }

    public Integer getUserId(){
        return this.userId;
    }

    public Integer getCommenterId(){
        return this.commenterId;
    }

    public String getComment(){
        return this.comment;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setCommenterId(Integer commenterId) {
        this.commenterId = commenterId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
