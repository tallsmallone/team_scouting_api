package com.simulationhockey.columbus.scout_api.api.commenters;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CommentersRepository extends CrudRepository<Commenters, Integer>{
    @Query(nativeQuery = true, value="CALL sp_GetCommenterByUsername(:name)")
    List<Commenters> findByUsername(@Param("name") String username);

    @Query(nativeQuery = true, value="CALL sp_GetCommenter(:id)")
    List<Commenters> getCommenter(@Param("id") Integer id);

    @Query(nativeQuery = true, value="CALL sp_GetAllCommenters()")
    List<Commenters> getAllCommenters();

    @Query(nativeQuery = true, value="CALL sp_AddCommenter(:username)")
    void addCommenter(@Param("username") String username);

    @Query(nativeQuery = true, value="CALL sp_UpdateCommenter(:id, :username)")
    void updateCommenter(@Param("id") Integer id, @Param("username") String username);

    @Query(nativeQuery = true, value="CALL sp_DeleteCommenter(:id)")
    void deleteCommenter(@Param("id") Integer id);
}
