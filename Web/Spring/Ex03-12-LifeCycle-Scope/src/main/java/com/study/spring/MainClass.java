package com.study.spring;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	
	public static void main(String[] args) {
		
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		
		context.load("classpath:beans.xml");
		
		context.refresh();
		
		Student student = context.getBean("student",Student.class);
		System.out.println("이름 : "+ student.getName());
		System.out.println("나이 : "+ student.getAge());
		
		System.out.println("==================================");
		
		Student student1 = context.getBean("student",Student.class);
		student1.setName("전우치");
		student1.setAge(25);
		
		
		System.out.println("이름 : "+ student.getName());
		System.out.println("나이 : "+ student.getAge());
		
		System.out.println("====================================");
		
		if(student.equals(student1)) {
			System.out.println("==");
		}else {
			System.out.println("!=");
		}
		
		System.out.println(student == student1);
		
		
	}

}
