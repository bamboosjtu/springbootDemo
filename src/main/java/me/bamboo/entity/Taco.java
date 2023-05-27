package me.bamboo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Taco {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date createdAt = new Date();
	
	@NotNull
	@Size(min = 5, message = "塔克名称不应少于5个字符。")
	private String name;
	
	@ManyToMany
	@Size(min = 1, message = "您至少需要添加1味食材。")
	private List<Ingredient> ingredients;
	
	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}
}
