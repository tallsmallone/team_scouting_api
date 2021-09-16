package com.simulationhockey.columbus.scout_api.api.draft;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DraftRepository extends CrudRepository<Draft, Integer>{
    List<Draft> findByUserId(Integer userId);
}
