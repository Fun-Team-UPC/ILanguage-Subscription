package com.edu.upc.ilanguagesubscription.command.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionInfra {
    @Id
    private String id;
    public int price;
    public int monthDuration;
    @NotBlank(message ="Name is mandatory")
    public String name;

}
