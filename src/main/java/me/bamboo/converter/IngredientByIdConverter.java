package me.bamboo.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import me.bamboo.data.IngredientRepository;
import me.bamboo.entity.Ingredient;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient>{
	
	private IngredientRepository ingredientRepository;
	
	@Autowired
	public IngredientByIdConverter(IngredientRepository ingredientRepository) {
			this.ingredientRepository = ingredientRepository;
		}

	@Override
	public Ingredient convert(String id) {
		Optional<Ingredient> ingredient = ingredientRepository.findById(id);
		return ingredient.orElse(null);
		
	}	

}
