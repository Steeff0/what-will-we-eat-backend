package com.hardrockdevops.services.whatwillweeat.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

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
    private Roll roll = Roll.USER;

    @OneToMany(mappedBy="creator")
    private List<Recipe> recipes;
}
