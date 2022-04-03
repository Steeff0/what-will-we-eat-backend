package com.hardrockdevops.services.whatwillweeat.services;

import com.hardrockdevops.services.whatwillweeat.exceptions.ServiceException;
import com.hardrockdevops.services.whatwillweeat.models.Recipe;
import com.hardrockdevops.services.whatwillweeat.repositories.RecipeRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Recipe findByRecipeId(long id) throws ServiceException {
        Optional<Recipe> opt = recipeRepository.findById(id);
        if (opt.isEmpty()) {
            throw new ServiceException("No Recipe found with id " + id);
        }
        return opt.get();
    }

    public void createRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public void updateRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public void deleteRecipe(long id) throws ServiceException {
        Recipe recipe = findByRecipeId(id);
        recipeRepository.delete(recipe);
    }
}
