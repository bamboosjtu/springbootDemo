package me.bamboo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import me.bamboo.data.IngredientRepository;
import me.bamboo.entity.Ingredient;
import me.bamboo.entity.Ingredient.Type;

@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repo) {
		return args -> {
			repo.save(new Ingredient("FLTO", "小麦粉薄烙饼", Type.WRAP));
			repo.save(new Ingredient("COTO", "玉米粉薄烙饼", Type.WRAP));
			repo.save(new Ingredient("GRBF", "牛肉", Type.PROTEIN));
			repo.save(new Ingredient("CARN", "猪肉", Type.PROTEIN));
			repo.save(new Ingredient("TMTO", "番茄", Type.VEGGIES));
			repo.save(new Ingredient("LETC", "莴苣", Type.VEGGIES));
			repo.save(new Ingredient("CHED", "切达牌奶酪", Type.CHEESE));
			repo.save(new Ingredient("JACK", "杰克牌奶酪", Type.CHEESE));
			repo.save(new Ingredient("SLSA", "沙拉酱", Type.SAUCE));
			repo.save(new Ingredient("SRCR", "奶油酱", Type.SAUCE));
		};
	}
	

}
