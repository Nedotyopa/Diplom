package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Post;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Long>, JpaSpecificationExecutor<Post>, PagingAndSortingRepository<Post, Long> {

}
