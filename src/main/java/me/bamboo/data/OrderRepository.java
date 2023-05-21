package me.bamboo.data;

import java.util.List;
import java.util.Optional;

import me.bamboo.entity.Ingredient;
import me.bamboo.entity.TacoOrder;

public interface OrderRepository {
	List<TacoOrder> findAll();
	Optional<TacoOrder> findById(String id);
	public TacoOrder save(TacoOrder tacoOrder);
}
