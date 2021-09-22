package com.edu.upc.ilanguagesubscription.query.projections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionHistoryView {
    @Id
    @GeneratedValue
    private int subscriptionHistoryId;
    @Column()
    private int subscriptionId;
    @Column()
    public int price;
    @Column()
    public int monthDuration;
    @Column(length=20)
    public String name;
    private Instant createdAt;
    @Column(nullable = true)
    private Instant updatedAt;

    public SubscriptionHistoryView(int id, int price, int monthDuration, String name, Instant createdAt, Instant updatedAt) {
        this.subscriptionId = id;
        this.price = price;
        this.monthDuration = monthDuration;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public SubscriptionHistoryView(SubscriptionHistoryView subscriptionHistoryView){
        this.subscriptionId = subscriptionHistoryView.getSubscriptionId();
        this.name = subscriptionHistoryView.getName();
        this.createdAt = subscriptionHistoryView.getCreatedAt();
        this.updatedAt = subscriptionHistoryView.getUpdatedAt();
        this.monthDuration = subscriptionHistoryView.getMonthDuration();
        this.price = subscriptionHistoryView.getPrice();
    }
}
