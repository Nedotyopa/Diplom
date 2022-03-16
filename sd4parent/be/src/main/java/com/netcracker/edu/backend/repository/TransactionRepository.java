package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Transaction;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {
}
