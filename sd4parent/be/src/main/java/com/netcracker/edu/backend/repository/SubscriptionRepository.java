package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Subscription;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long>, JpaSpecificationExecutor<Subscription> {

    @Modifying
    @Transactional
    @Query("update Subscription sub set sub.duration = sub.duration - 1 where sub.id = :id")
    void decreaseDuration(@Param("id") Long id);

//    @Modifying
//    @Transactional
//    @Query("update Subscription sub set sub.status = case when sub.status = 1 then 0 else 1 end where sub.id = :id")
//    void changeStatus(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update Subscription sub set sub.status = 0 where sub.id = :id")
    void falseStatus(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update Subscription sub set sub.status = 1 where sub.id = :id")
    void trueStatus(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update Subscription sub set sub.duration = sub.duration + :duration where sub.id = :id")
    void extendSubscription(@Param("id") Long id, @Param("duration") int duration);

    @Modifying
    @Transactional
    @Query("update Subscription sub set sub.cost = :cost where sub.id = :id")
    void changeCost(@Param("id") Long id, @Param("cost") float cost);

}
