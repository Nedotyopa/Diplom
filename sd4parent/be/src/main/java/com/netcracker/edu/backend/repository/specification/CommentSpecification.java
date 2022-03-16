package com.netcracker.edu.backend.repository.specification;

import com.netcracker.edu.backend.entity.Comment;

import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CommentSpecification {

    public static Specification<Comment> commentsFindByPostId(Long id) {
        return new Specification<Comment>() {
            @Override
            public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("post").get("id"), id);
            }
        };
    }

}
