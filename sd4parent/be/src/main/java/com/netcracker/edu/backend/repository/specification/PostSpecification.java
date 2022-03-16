package com.netcracker.edu.backend.repository.specification;

import com.netcracker.edu.backend.entity.Post;

import com.netcracker.edu.backend.entity.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Collection;

public class PostSpecification {

    public static Specification<Post> postsFindByLogin(String login) {
        return (root, query, cb) -> {
            query.distinct(true);
            Root<Post> post = root;
            Root<User> user = query.from(User.class);
            Expression<Collection<User>> postUsers = post.get("users");
            return cb.and(cb.equal(user.get("login"), login), cb.isMember(user, postUsers));
        };
    }

}
