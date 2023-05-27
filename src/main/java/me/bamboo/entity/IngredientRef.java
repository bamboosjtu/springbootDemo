package me.bamboo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class IngredientRef {
	private final String ingredient;
}
