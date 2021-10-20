package com.edu.upc.ilanguagesubscription.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionViewRepository extends JpaRepository <SubscriptionView, String>{

    @Query("SELECT s from SubscriptionView s WHERE s.name=?1")
    public SubscriptionView findByName(String subscriptionName);

    @Query("SELECT s from SubscriptionView s WHERE s.id=?1")
    public SubscriptionView getById(String subscriptionId);

}
