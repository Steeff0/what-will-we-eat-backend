package com.hardrockdevops.whatwillweeatservices.controller;

import com.hardrockdevops.whatwillweeatservices.model.Recipe;
import com.hardrockdevops.whatwillweeatservices.services.RecipeServices;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recipe")
@CommonsLog
public class RecipeController {

    final
    RecipeServices recipeServices;

    public RecipeController(RecipeServices recipeServices) {
        this.recipeServices = recipeServices;
    }

    @GetMapping(path = "/")
    public List<Recipe> getRecipes(){
        log.debug("Entering getRecipes");
        return recipeServices.getAllRecipes();
    }

    @GetMapping(path = "/{id}")
    public Recipe getRecipe(@PathVariable("id") final long id){
        log.debug("Entering getRecipe with id:" + id);
        return recipeServices.findByRecipeId(id);
    }

    @PostMapping(path = "/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addRecipe(@RequestBody Recipe recipe){
        log.debug("Entering addRecipe");
        recipeServices.create(recipe);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateRecipe(@RequestBody Recipe recipe, @PathVariable("id") final long id){
        log.debug("Entering updateRecipe");
        recipeServices.update(recipe);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteRecipe(@PathVariable("id") final long id){
        log.debug("Entering deleteRecipe");
        recipeServices.delete(id);
    }
}
