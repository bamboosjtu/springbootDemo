package me.bamboo.data;

import org.springframework.data.repository.CrudRepository;

import me.bamboo.entity.Ingredient;


public interface IngredientRepository extends CrudRepository<Ingredient, String>{
//	Iterable<Ingredient> findAll();
//	Optional<Ingredient> findById(String id);
//	Ingredient save(Ingredient ingredient);
}
