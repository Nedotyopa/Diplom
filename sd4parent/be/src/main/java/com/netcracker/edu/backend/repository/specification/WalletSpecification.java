package com.netcracker.edu.backend.repository.specification;

import com.netcracker.edu.backend.entity.Wallet;

import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class WalletSpecification {

    public static Specification<Wallet> walletFindByLogin(String login) {
        return new Specification<Wallet>() {
            @Override
            public Predicate toPredicate(Root<Wallet> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("owner").get("login"), login);
            }
        };
    }
}
