package com.edu.upc.ilanguagesubscription.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionHistoryViewRepository extends JpaRepository<SubscriptionHistoryView, Integer> {
    @Query(value = "SELECT * FROM subscription_history_view WHERE subscription_id = (SELECT MAX(subscription_history_id) FROM subscription_history_view WHERE subscription_id = :subscriptionId)", nativeQuery = true)
    Optional<SubscriptionHistoryView> getLastBySubscriptionId(int subscriptionId);

}
