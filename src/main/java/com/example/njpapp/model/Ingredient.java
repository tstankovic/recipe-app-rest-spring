package com.example.njpapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "ingredients")
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredientId;
    @NotBlank
    private String name;
    @NotBlank
    private String amount;

    public Ingredient() {

    }

    public Ingredient(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    public Long getId() {
        return ingredientId;
    }

    public void setId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
