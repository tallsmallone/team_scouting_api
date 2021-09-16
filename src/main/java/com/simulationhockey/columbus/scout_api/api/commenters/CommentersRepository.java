package com.simulationhockey.columbus.scout_api.api.commenters;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CommentersRepository extends CrudRepository<Commenters, Integer>{
    List<Commenters> findByUsername(String username);
}
