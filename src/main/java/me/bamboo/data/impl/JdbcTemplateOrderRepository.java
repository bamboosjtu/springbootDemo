package me.bamboo.data.impl;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import me.bamboo.data.OrderRepository;
import me.bamboo.entity.Ingredient;
import me.bamboo.entity.IngredientRef;
import me.bamboo.entity.Taco;
import me.bamboo.entity.TacoOrder;

@Repository
public class JdbcTemplateOrderRepository implements OrderRepository{
	private JdbcOperations jdbcOperations;
	
	@Autowired
	public JdbcTemplateOrderRepository(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}

	@Override
	public List<TacoOrder> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<TacoOrder> findById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	@Transactional
	public TacoOrder save(TacoOrder order) {
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
				"insert into Taco_Order "
				+ "(delivery_name, delivery_street, delivery_city, delivery_state, delivery_zip, "
				+ "cc_number, cc_expiration, cc_cvv, "
				+ "placed_at)"
				+ "values (?,?,?,?,?,?,?,?,?)",
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
				);
		pscf.setReturnGeneratedKeys(true);
		
		order.setPlacedAt(new Date());
		PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
				order.getDeliveryName(),
				order.getDeliveryStreet(),
				order.getDeliveryCity(),
				order.getDeliveryState(),
				order.getDeliveryZip(),
				order.getCcNumber(),
				order.getCcExpiration(),
				order.getCcCVV(),
				order.getPlacedAt()));
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcOperations.update(psc, keyHolder);
		long orderId = keyHolder.getKey().longValue();
		order.setId(orderId);
		
		List<Taco> tacos = order.getTacos();
		int i = 0;
		for(Taco taco: tacos) {
			saveTaco(orderId, i++, taco);
		}
		return order;
	}

	private Long saveTaco(long orderId, int orderKey, Taco taco) {
		taco.setCreatedAt(new Date());
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
				"insert into Taco "
				+ "(name, taco_order, taco_order_key, created_at) "
				+ "values (?, ?, ?, ?)",
				Types.VARCHAR, Type.LONG, Type.LONG, Types.TIMESTAMP);
		pscf.setReturnGeneratedKeys(true);
		
		PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
				taco.getName(),
				orderId,
				orderKey,
				taco.getCreatedAt()));
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcOperations.update(psc, keyHolder);
		long tacoId = keyHolder.getKey().longValue();
		taco.setId(tacoId);
		
		saveIngredientRefs(tacoId, taco.getIngredients());
		
		return tacoId;
	}

	private void saveIngredientRefs(long tacoId, List<IngredientRef> ingredients) {
		int key = 0;
		for(IngredientRef ingredientRef: ingredients) {
			jdbcOperations.update(
					"insert into Ingredient_Ref (ingredient, taco, taco_key) values(?,?,?)",
					ingredientRef.getIngredient(), tacoId, key++);
		}
	}	

}
