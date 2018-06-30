package com.las.WebData.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.boot.archive.scan.spi.ScanEnvironment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.las.WebData.entity.Users;
import com.las.WebData.service.UserService;

public class Test {

	@org.junit.Test
	public static void saveUser(){
		Scanner sc = new Scanner(System.in);
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring-dao.xml");
		UserService service = (UserService) context.getBean("userService");
		
		Users user = new Users();
////		user.setId(4);
//		user.setUsername("hahah");
//		service.saveUser(user);
		
//		List<Users> list = new ArrayList<Users>();
//		System.out.print("请输入第几页 :");
//		int page = sc.nextInt();
//		System.out.print("请输每页多少数据 :");
//		int size = sc.nextInt();
//		list = service.getPageUser(page , size);
//		for (Users user2 : list) {
//			System.out.println(user2.getUsername());
//		}
//		
//		user = service.getUser(2);
//		System.out.println(user.getUsername());
	}
	
	public static void main(String[] args) {
		saveUser();
	}
}
