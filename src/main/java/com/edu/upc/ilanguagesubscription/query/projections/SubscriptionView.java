package com.edu.upc.ilanguagesubscription.query.projections;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionView {
    @Id
    private String id;
    @Column()
    public int price;
    @Column()
    public int monthDuration;
    @Column(length=20)
    public String name;
    private Instant createdAt;
    @Column(nullable = true)
    private Instant updatedAt;

    public SubscriptionView(String id, int price, int monthDuration, String name, Instant createdAt) {
        this.id = id;
        this.price = price;
        this.monthDuration = monthDuration;
        this.name = name;
        this.createdAt = createdAt;
    }
}
