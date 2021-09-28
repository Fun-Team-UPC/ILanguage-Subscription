package com.edu.upc.ilanguagesubscription.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;


public interface SubscriptionInfraRepository extends JpaRepository<SubscriptionInfra, Integer> {

    //!Query refers to the class (MODEL)
    @Query("SELECT s FROM SubscriptionInfra s WHERE s.name = ?1")
    public Optional<SubscriptionInfra> findByName(String name);

    @Query("SELECT s FROM SubscriptionInfra s WHERE s.price = ?1")
    public Optional<SubscriptionInfra> findByPrice(int price);

    @Query("SELECT s FROM SubscriptionInfra s WHERE s.monthDuration = ?1")
    public Optional<SubscriptionInfra>findByMonthDuration(int monthDuration);






}
