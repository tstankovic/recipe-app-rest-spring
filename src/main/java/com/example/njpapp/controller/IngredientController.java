package com.example.njpapp.controller;

import com.example.njpapp.exception.ResourceNotFoundException;
import com.example.njpapp.model.Ingredient;
import com.example.njpapp.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingredients")
@CrossOrigin
public class IngredientController {
    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping()
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @PostMapping()
    public Ingredient createIngredients(@RequestBody Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @GetMapping("{/id}")
    public Optional<Ingredient> getIngredientById(@PathVariable(value = "id") Long ingredientId) {
        return ingredientRepository.findById(ingredientId);
    }

    @PutMapping("{/id}")
    public Ingredient updateIngredient(@PathVariable(value = "id") Long ingredientId, @RequestBody Ingredient details) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient", "id", ingredientId));
        ingredient.setName(details.getName());
        ingredient.setAmount(details.getAmount());
        Ingredient updatedIngredient = ingredientRepository.save(ingredient);
        return updatedIngredient;
    }
}
