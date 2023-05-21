package me.bamboo.data;

import java.util.List;
import java.util.Optional;

import me.bamboo.entity.Ingredient;

public interface IngredientRepository {
	List<Ingredient> findAll();
	Optional<Ingredient> findById(String id);
	Ingredient save(Ingredient ingredient);
}
