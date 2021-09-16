package com.simulationhockey.columbus.scout_api.api.userinformation;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserInformationRepository extends CrudRepository<UserInformation, Integer> {
    List<UserInformation> findByUsername(String username);
}
