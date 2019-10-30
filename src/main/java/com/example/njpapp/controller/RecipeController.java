package com.example.njpapp.controller;

import com.example.njpapp.exception.ResourceNotFoundException;
import com.example.njpapp.model.Recipe;
import com.example.njpapp.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
@CrossOrigin
public class RecipeController {
    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping()
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @PostMapping()
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable(value = "id") Long recipeId) {
        return recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));
    }

    @PutMapping("{/id}")
    public Recipe updateRecipe(@PathVariable(value = "id") Long recipeId, @RequestBody Recipe recipeDetails) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));
        recipe.setName(recipeDetails.getName());
        recipe.setDescription(recipeDetails.getDescription());
        recipe.setImagePath(recipeDetails.getImagePath());

        Recipe updatedRecipe = recipeRepository.save(recipe);
        return updatedRecipe;
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable(value = "id") Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));

        recipeRepository.delete(recipe);
        return ResponseEntity.ok().build();
    }
}
