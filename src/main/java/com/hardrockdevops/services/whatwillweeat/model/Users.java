package com.hardrockdevops.services.whatwillweeat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(length=100, nullable=false)
    private String name;

    @Column(length=256, nullable=false)
    private String email;

    @Column(nullable=false)
    private boolean blocked = false;

    @Column(nullable=false)
    private Rolls roll = Rolls.USER;

    @OneToMany(mappedBy="creator")
    private List<Recipes> recipes;
}
