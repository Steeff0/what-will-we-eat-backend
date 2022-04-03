package com.hardrockdevops.services.whatwillweeat.repositories;

import com.hardrockdevops.services.whatwillweeat.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}