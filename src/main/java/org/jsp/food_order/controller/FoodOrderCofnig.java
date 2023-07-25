package org.jsp.food_order.controller;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = "org.jsp.food_order")
public class FoodOrderCofnig {
	@Bean
	public EntityManager getEntityManger() {
		return Persistence.createEntityManagerFactory("dev").createEntityManager();
	}
}
