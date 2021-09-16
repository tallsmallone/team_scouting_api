package com.simulationhockey.columbus.scout_api.api.teamcomments;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TeamCommentsRepository extends CrudRepository<TeamComments, Integer>{
    List<TeamComments> findByUserId(Integer id);
    List<TeamComments> findByCommenterId(Integer id);
}
