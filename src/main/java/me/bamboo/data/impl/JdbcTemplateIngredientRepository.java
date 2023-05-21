package me.bamboo.data.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import me.bamboo.data.IngredientRepository;
import me.bamboo.entity.Ingredient;

@Repository
public class JdbcTemplateIngredientRepository implements IngredientRepository{
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcTemplateIngredientRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Ingredient> findAll() {
		return jdbcTemplate.query("select id, name, type from Ingredient", this::mapRowToIngredient);
	}

	@Override
	public Optional<Ingredient> findById(String id) {
		List<Ingredient> results = jdbcTemplate.query("select id, name, type from Ingredient where id=?", this::mapRowToIngredient, id);
		return results.size() == 0? Optional.empty() : Optional.of(results.get(0));
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		jdbcTemplate.update("insert into Ingredient (id, name, type) values(?, ?, ?)", ingredient.getId(), ingredient.getName(), ingredient.getType());
		return ingredient;
	}
	
	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
		return new Ingredient(
				rs.getString("id"),
				rs.getString("name"),
				Ingredient.Type.valueOf(rs.getString("type")));
	}
	

}
