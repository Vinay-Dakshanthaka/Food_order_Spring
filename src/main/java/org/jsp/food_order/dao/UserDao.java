package org.jsp.food_order.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.jsp.food_order.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	EntityManager manager;
	
	public User saveUser(User user) {
		EntityTransaction transaction = manager.getTransaction();
		manager.persist(user);
		transaction.begin();
		transaction.commit();
		return user;
	}
	
	public User findUserById(int id) {
		User user = manager.find(User.class, id);
		if(user!=null) {
			return user;
		}else {
			return null;
		}
	}
	
	
}
