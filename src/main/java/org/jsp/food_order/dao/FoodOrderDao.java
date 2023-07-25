package org.jsp.food_order.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.food_order.dto.FoodOrder;
import org.jsp.food_order.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.ScalarOrBuilder;

@Repository
public class FoodOrderDao {
	@Autowired
	EntityManager manager;
	Scanner sc = new Scanner(System.in);
	public FoodOrder addFoodOrder(FoodOrder order, int userId) {
		EntityTransaction transaction = manager.getTransaction();
		String qry = "select u from User u where u.id=?1";
		Query q = manager.createQuery(qry);
		q.setParameter(1, userId);
		try {
			User user = (User) q.getSingleResult();
			user.getOrder().add(order);
			order.setUser(user);
			manager.persist(order);
			transaction.begin();
			transaction.commit();
			return order;
		} catch (NoResultException e) {
			return null;
		}
	}

	public void fetchOrderByUserId(int userId) {
		String qry = "select order from FoodOrder order where order.user.id=?1";
		Query q = manager.createQuery(qry);
		q.setParameter(1, userId);
		List<FoodOrder> order = q.getResultList();
		for(FoodOrder o :order) {
			System.out.println(o);
		}
	}
	
	public void updateOrder(int userId) {
		EntityTransaction transaction = manager.getTransaction();
		String qry = "select order from FoodOrder order where order.user.id=?1";
		Query q = manager.createQuery(qry);
		User user  = manager.find(User.class, userId);
		
		if(user!=null) {
			
			q.setParameter(1, userId);
			List<FoodOrder> order = q.getResultList();
			for(FoodOrder o :order) {
				System.out.println(o);
			}
			
			System.out.println("enter food id to update");
			int foodId = sc.nextInt();
			System.out.println("enter food name and price");
			String foodName = sc.next();
			double price = sc.nextDouble();
			
			FoodOrder order2 = new FoodOrder();
			order2.setId(foodId);
			order2.setFoodName(foodName);
			order2.setPrice(price);
			order2.setUser(user);
			manager.merge(order2);
			transaction.begin();
			transaction.commit();
			System.out.println("Updated successfully");
		}else {
			
		}
		
		
		
		
		
	}
}
