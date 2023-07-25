package org.jsp.food_order.controller;

import java.util.Scanner;

import javax.persistence.NoResultException;

import org.jsp.food_order.dao.FoodOrderDao;
import org.jsp.food_order.dao.UserDao;
import org.jsp.food_order.dto.FoodOrder;
import org.jsp.food_order.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FoodOrderController {
	 static FoodOrderDao orderDao;
	 static UserDao userDao;
	 
	static {
		ApplicationContext context = new AnnotationConfigApplicationContext("org.jsp.food_order");
		userDao = context.getBean(UserDao.class);
		orderDao = context.getBean(FoodOrderDao.class);
	}
	
	
	public static void main(String[] args) {
		FoodOrder order = new FoodOrder();
		User user = new User();
		Scanner sc = new Scanner(System.in);
		System.out.println("1.Save User");
		System.out.println("2.Find user by id");
		System.out.println("3.Place an Order");
		System.out.println("4.Fetch Order by User ID");
		System.out.println("5.Update food order");
		int choice = sc.nextInt();

		switch (choice) {
		case 1: {
			System.out.println("Enter your name, phone, address");
			String name = sc.next();
			long phone = sc.nextLong();
			String address = sc.next();

			user.setName(name);
			user.setPhone(phone);
			user.setAddress(address);

			System.out.println(userDao.saveUser(user));
			break;
		}
		case 2: {
			System.out.println("Enter User id to fetch the details");
			int id = sc.nextInt();

			user = userDao.findUserById(id);
			if (user != null) {
				System.out.println(user);
			} else {
				System.out.println("Invalid User ID");
			}
		}

		case 3: {
			System.out.println("Enter your user ID");
			int id = sc.nextInt();
			user = userDao.findUserById(id);
			user = userDao.findUserById(id);
			if (user != null) {
				System.out.println("Enter food name and price");
				String foodName = sc.next();
				int price = sc.nextInt();
				order.setFoodName(foodName);
				order.setPrice(price);
				order = orderDao.addFoodOrder(order, id);
			} else {
				System.out.println("Invalid User ID");
			}
		}
		
		case 4:{
			System.out.println("Enter User Id to fetch food order details");
			int id = sc.nextInt();
			try {
				orderDao.fetchOrderByUserId(id);
			}catch(NoResultException e) {
				System.out.println("invalid id ");
			}
			
		}
		
		case 5:{
			System.out.println("enter your userId to updated order details");
			int id = sc.nextInt();
			orderDao.updateOrder(id);
		}
		default:
			break;
		}
	}
}
