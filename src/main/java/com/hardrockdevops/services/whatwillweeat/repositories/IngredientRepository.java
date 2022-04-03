package com.hardrockdevops.services.whatwillweeat.repositories;

import com.hardrockdevops.services.whatwillweeat.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Recipe, Long> {
}