package me.bamboo.data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import me.bamboo.entity.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, UUID>{

};
