package com.hardrockdevops.services.whatwillweeat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.time.ZonedDateTime;

@Entity
@Getter @Setter @NoArgsConstructor
public class Recipes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length=80, nullable=false, unique=false)
    private String name;

    @Lob
    private String description;

    @Basic
    private long creator;

    @Column(nullable=false)
    private ZonedDateTime created;

    private int feedPeople;

    @Basic
    private Duration prepTime;

    @Enumerated(EnumType.STRING)
    private Kitchens kitchen;
}
