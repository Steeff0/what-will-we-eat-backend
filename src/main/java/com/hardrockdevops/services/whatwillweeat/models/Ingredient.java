package com.hardrockdevops.services.whatwillweeat.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id", nullable=false)
    private Recipe recipe;

    @Column(length=80, nullable=false)
    private String ingredient;

    @Column(length=20, nullable=false)
    private String amount;
}
