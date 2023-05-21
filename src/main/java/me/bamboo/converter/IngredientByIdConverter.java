package me.bamboo.converter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import me.bamboo.data.IngredientRepository;
import me.bamboo.entity.Ingredient;
import me.bamboo.entity.IngredientRef;

@Component
public class IngredientByIdConverter implements Converter<String, IngredientRef>{
	
	private IngredientRepository ingredientRepository;
	
	@Autowired
	public IngredientByIdConverter(IngredientRepository ingredientRepository) {
			this.ingredientRepository = ingredientRepository;
		}

	@Override
	public IngredientRef convert(String id) {
		Optional<Ingredient> ingredient = ingredientRepository.findById(id);
		if(ingredient.isEmpty()) {
			return null;
		}

		return new IngredientRef(ingredient.get().getId());
		
	}	

//	private Map<String, Ingredient> ingredientMap = new HashMap<>();
//	
//	public IngredientByIdConverter(Map<String, Ingredient> ingredientMap) {
//		this.ingredientMap.put("FLTO",new Ingredient("FLTO", "小麦粉薄烙饼", Ingredient.Type.WRAP));
//		this.ingredientMap.put("COTO",new Ingredient("COTO", "玉米粉薄烙饼", Ingredient.Type.WRAP));
//		this.ingredientMap.put("GRBF",new Ingredient("GRBF", "牛肉", Ingredient.Type.PROTEIN));
//		this.ingredientMap.put("CARN",new Ingredient("CARN", "猪肉", Ingredient.Type.PROTEIN));
//		this.ingredientMap.put("TMTO",new Ingredient("TMTO", "番茄", Ingredient.Type.VEGGIES));
//		this.ingredientMap.put("LETC",new Ingredient("LETC", "莴苣", Ingredient.Type.VEGGIES));
//		this.ingredientMap.put("CHED",new Ingredient("CHED", "切达牌奶酪", Ingredient.Type.CHEESE));
//		this.ingredientMap.put("JACK",new Ingredient("JACK", "杰克牌奶酪", Ingredient.Type.CHEESE));
//		this.ingredientMap.put("SLSA",new Ingredient("SLSA", "沙拉酱", Ingredient.Type.SAUCE));
//		this.ingredientMap.put("SRCR",new Ingredient("SRCR", "奶油酱", Ingredient.Type.SAUCE));
//	}

//	@Override
//	public Ingredient convert(String id) {
//		return ingredientMap.get(id);
//	}
	

}
