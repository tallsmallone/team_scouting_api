package com.simulationhockey.columbus.scout_api.api.userinformation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserInformationRepository extends JpaRepository<UserInformation, Integer> {
    @Query(nativeQuery = true, value = "CALL sp_GetUserInformationByUsername(:username)")
    List<UserInformation> findByUsername(@Param("username") String username);

    @Query(nativeQuery = true, value = "CALL sp_GetAllUserInformation")
    List<UserInformation> getAllUserInformation();

    @Query(nativeQuery = true, value = "CALL sp_GetUserInformation(:userId)")
    List<UserInformation> getUserInformation(@Param("userId") Integer userId);

    @Query(nativeQuery = true, value = "CALL sp_AddUserInformation(:name, :playerLastName, " +
        ":playerFirstName, :pos, :draftTeam, :discord, :earnedTpe, :joinedDate, :lastVisitDate, " +
        ":isActive, :isContacted, :isWant, :isAvoid, :isDrafted, :draftPick)")
    void addUserInformation(
        @Param("name") String name, 
        @Param("playerLastName") String playerLastName,
        @Param("playerFirstName") String playerFirstName,
        @Param("pos") String pos,
        @Param("draftTeam") String draftTeam,
        @Param("discord") String discord,
        @Param("earnedTpe") Integer earnedTpe,
        @Param("joinedDate") String joinedDate,
        @Param("lastVisitDate") String lastVisitDate,
        @Param("isActive") Boolean isActive,
        @Param("isContacted") Boolean isContacted,
        @Param("isWant") Boolean isWant,
        @Param("isAvoid") Boolean isAvoid,
        @Param("isDrafted") Boolean isDrafted,
        @Param("draftPick") Integer draftPick
        );
    
    @Query(nativeQuery = true, value = "CALL sp_UpdateUserInformation(:id, :name, :playerLastName, " +
        ":playerFirstName, :pos, :draftTeam, :discord, :earnedTpe, :joinedDate, :lastVisitDate, " +
        ":isActive, :isContacted, :isWant, :isAvoid, :isDrafted, :draftPick)")
    void updateUserInformation(
        @Param("id") Integer id,
        @Param("name") String name, 
        @Param("playerLastName") String playerLastName,
        @Param("playerFirstName") String playerFirstName,
        @Param("pos") String pos,
        @Param("draftTeam") String draftTeam,
        @Param("discord") String discord,
        @Param("earnedTpe") Integer earnedTpe,
        @Param("joinedDate") String joinedDate,
        @Param("lastVisitDate") String lastVisitDate,
        @Param("isActive") Boolean isActive,
        @Param("isContacted") Boolean isContacted,
        @Param("isWant") Boolean isWant,
        @Param("isAvoid") Boolean isAvoid,
        @Param("isDrafted") Boolean isDrafted,
        @Param("draftPick") Integer draftPick
        );

    @Query(nativeQuery = true, value = "CALL sp_DeleteUserInformation(:id)")
    void deleteUserInformation(@Param("id") Integer id);
}
