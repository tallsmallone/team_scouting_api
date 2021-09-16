package com.simulationhockey.columbus.scout_api.api.teamcomments;

import org.springframework.data.repository.CrudRepository;

public interface TeamCommentsRepository extends CrudRepository<TeamComments, Integer>{
    Iterable<TeamComments> findByUserId(Integer id);
    Iterable<TeamComments> findByCommenterId(Integer id);
}
