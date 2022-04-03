package com.hardrockdevops.services.whatwillweeat.services;

import com.hardrockdevops.services.whatwillweeat.models.Recipe;
import com.hardrockdevops.services.whatwillweeat.repositories.RecipeRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.util.List;

@CommonsLog
@Service
public class RecipeServices {

    private final RecipeRepository recipeRepository;

    public RecipeServices(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> list() {
        return recipeRepository.findAll();
    }
}
