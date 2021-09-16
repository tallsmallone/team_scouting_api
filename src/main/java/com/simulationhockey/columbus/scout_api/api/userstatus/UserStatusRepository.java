package com.simulationhockey.columbus.scout_api.api.userstatus;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserStatusRepository extends CrudRepository<UserStatus, Integer>{
    List<UserStatus> findByUserId(Integer userId);
}
