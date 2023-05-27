package me.bamboo.controller;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import me.bamboo.data.IngredientRepository;
import me.bamboo.entity.Ingredient;
import me.bamboo.entity.Ingredient.Type;
import me.bamboo.entity.Taco;
import me.bamboo.entity.TacoOrder;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class CreateTacoController {
	
	private final IngredientRepository ingredientRepository;
	
	@Autowired
	public CreateTacoController(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		Iterable<Ingredient> ingredients = ingredientRepository.findAll();
//		List<Ingredient> ingredients = Arrays.asList(
//				new Ingredient("FLTO", "小麦粉薄烙饼", Ingredient.Type.WRAP),
//				new Ingredient("COTO", "玉米粉薄烙饼", Ingredient.Type.WRAP),
//				new Ingredient("GRBF", "牛肉", Ingredient.Type.PROTEIN),
//				new Ingredient("CARN", "猪肉", Ingredient.Type.PROTEIN),
//				new Ingredient("TMTO", "番茄", Ingredient.Type.VEGGIES),
//				new Ingredient("LETC", "莴苣", Ingredient.Type.VEGGIES),
//				new Ingredient("CHED", "切达牌奶酪", Ingredient.Type.CHEESE),
//				new Ingredient("JACK", "杰克牌奶酪", Ingredient.Type.CHEESE),
//				new Ingredient("SLSA", "沙拉酱", Ingredient.Type.SAUCE),
//				new Ingredient("SRCR", "奶油酱", Ingredient.Type.SAUCE)
//				);
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
			
		}
	}
	
	@ModelAttribute(name = "tacoOrder")
	public TacoOrder order() {
		return new TacoOrder();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	@GetMapping
	public String showDesignForm() {
		return "design";
	}
	
	@PostMapping
	public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
		if(errors.hasErrors()) {
			return "design";
		}
		
		tacoOrder.addTaco(taco);
		log.info("正在添加塔可: {}", taco);
		return "redirect:/orders/current";
		
	}

	private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
		return StreamSupport.stream(ingredients.spliterator(), false).filter(x -> x.getType().equals(type)).collect(Collectors.toList());
	}
}