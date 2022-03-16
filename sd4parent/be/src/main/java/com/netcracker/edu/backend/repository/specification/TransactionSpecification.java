package com.netcracker.edu.backend.repository.specification;

import com.netcracker.edu.backend.entity.Transaction;

import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TransactionSpecification {

    public static Specification<Transaction> transactionsFindByLogin(String login) {
        return new Specification<Transaction>() {
            @Override
            public Predicate toPredicate(Root<Transaction> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("wallet").get("owner").get("login"), login);
            }
        };
    }
}
