package me.bamboo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force=true)
@Builder
@Table
public class Ingredient implements Persistable<String>{
	@Id
	private String id;
	private String name;
	private Type type;
	
	public enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return true;
	}
	

}
