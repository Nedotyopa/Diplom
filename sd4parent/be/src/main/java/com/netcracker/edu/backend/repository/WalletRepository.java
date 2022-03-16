package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Wallet;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long>, JpaSpecificationExecutor<Wallet> {

    @Modifying
    @Transactional
    @Query("update Wallet purse set purse.money = purse.money + :amount where purse.id = :id")
    void fillUp(@Param("amount") float amount, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update Wallet purse set purse.money = purse.money - :amount where purse.id = :id")
    void chargeMoney(@Param("amount") float amount, @Param("id") Long id);

}
