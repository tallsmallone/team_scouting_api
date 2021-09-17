package com.simulationhockey.columbus.scout_api.api.teamcomments;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TeamCommentsRepository extends CrudRepository<TeamComments, Integer>{
    @Query(nativeQuery = true, value="CALL sp_GetUserComment(:id)")
    List<TeamComments> getUserComment(@Param("id") Integer id);

    @Query(nativeQuery = true, value="CALL sp_GetAllUserComments(:id)")
    List<TeamComments> getAllUserComments(@Param("id") Integer id);

    @Query(nativeQuery = true, value="CALL sp_GetUserCommentsByCommenterId(:id)")
    List<TeamComments> getCommentsByCommenterId(@Param("id") Integer id);

    @Query(nativeQuery = true, value="CALL sp_AddUserComment(:userId, :commenterId, :comment)")
    void addComment(@Param("userId") Integer userId, @Param("commenterId") Integer commenterId, @Param("comment") String comment);

    @Query(nativeQuery = true, value="CALL sp_UpdateUserComment(:id, :userId, :commenterId, :comment)")
    void updateComment(@Param("id") Integer commentId, @Param("userId") Integer userId, @Param("commenterId") Integer commenterId, @Param("comment") String comment);

    @Query(nativeQuery = true, value="CALL sp_DeleteUserComment(:id)")
    void deleteComment(@Param("id") Integer id);
}
