package com.simulationhockey.columbus.scout_api.api.draft;

import org.springframework.data.repository.CrudRepository;

public interface DraftRepository extends CrudRepository<Draft, Integer>{
    Iterable<Draft> findByUserId(Integer user_id);
}
