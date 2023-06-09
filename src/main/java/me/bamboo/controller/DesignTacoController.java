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
import me.bamboo.entity.TacoUDRUtils;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
	
	private final IngredientRepository ingredientRepository;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		Iterable<Ingredient> ingredients = ingredientRepository.findAll();
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
		
		tacoOrder.addTaco(TacoUDRUtils.toTacoUDT(taco));
		log.info("正在添加塔可: {}", taco);
		return "redirect:/orders/current";
		
	}

	private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
		return StreamSupport.stream(ingredients.spliterator(), false).filter(x -> x.getType().equals(type)).collect(Collectors.toList());
	}
}