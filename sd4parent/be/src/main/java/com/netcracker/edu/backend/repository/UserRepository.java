package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.MoneyOperation;
import com.netcracker.edu.backend.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Modifying
    @Transactional
    @Query("update User account set account.ban = 1 - account.ban where account.id = :id")
    void banUser(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update User account set account.lastDateLogin = :date where account.login = :login")
    void checkUser(@Param("login") String login, @Param("date") String date);

    Optional<User> findByEmail(String email);
    Optional<User> findByResetToken(String resetToken);

    @Modifying
    @Transactional
    @Query("update User account set account.resetToken = :resetToken where account.login = :login")
    void updateToken(@Param("login") String login, @Param("resetToken") String resetToken);

    @Modifying
    @Transactional
    @Query("update User account set account.password = :password where account.login = :login")
    void updatePassword(@Param("login") String login, @Param("password") String password);

    @Modifying
    @Transactional
    @Query("update User account set account.debt = :debt where account.id = :id")
    void updateDebt(@Param("debt") float debt, @Param("id") Long id);
}
