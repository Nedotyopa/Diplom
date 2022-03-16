package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Comment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {

//    @Modifying
//    @Transactional
//    @Query("update Comment comment set comment.user = 2 where comment.user = :id")
//    void changeAuthor(@Param("id") Long id);

}
