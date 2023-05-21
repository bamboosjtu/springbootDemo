package me.bamboo.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Taco {
	private Long id;
	
	private Date createdAt = new Date();
	
	@NotNull
	@Size(min = 5, message = "塔克名称不应少于5个字符。")
	private String name;
	
	@NotNull
	@Size(min = 1, message = "您至少需要添加1味食材。")
	private List<IngredientRef> ingredients;
}
