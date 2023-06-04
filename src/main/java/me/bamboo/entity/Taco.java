package me.bamboo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.oss.driver.api.core.uuid.Uuids;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("tacos")
public class Taco {
	@PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
	private UUID id = Uuids.timeBased();
	
	@PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
	private Date createdAt = new Date();
	
	@NotNull
	@Size(min = 5, message = "塔克名称不应少于5个字符。")
	private String name;
	
	@Size(min = 1, message = "您至少需要添加1味食材。")
	@Column("ingredients")
	private List<IngredientUDT> ingredients = new ArrayList<>();
	
	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(TacoUDRUtils.toIngredientUDT(ingredient));
	}
}
