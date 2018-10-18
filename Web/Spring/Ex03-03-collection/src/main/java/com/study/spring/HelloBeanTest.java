package com.study.spring;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloBeanTest {

	public static void main(String[] args) {
		
		String configLocation = "classpath:beans.xml";
		ApplicationContext context = new GenericXmlApplicationContext(configLocation);
		
		Hello hello = (Hello)context.getBean("hello");
		
		List<String> myList = hello.getNames();
		for(String value : myList) {
			System.out.println("List : "+value);
		}
		
		Set<Integer> mySet1 = hello.getNums();
		for(Integer value : mySet1) {
			System.out.println("Set : "+ value);
		}
		
		Map<String, Integer> map = hello.getAges();
		
		//방법 1
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println("Map1 : "+entry.getKey() + " - "+ entry.getValue());
		}
		
		//방법2
		for(String key : map.keySet()) {
			Integer value = map.get(key);
			System.out.println("Map2 : "+ key + " - "+value);
		}
		
		//방법3
		Iterator<String> iterator = map.keySet().iterator();
		
		while(iterator.hasNext()) {
			String key = iterator.next();
			Integer value = map.get(key);
			System.out.println("Map3 : "+key+" - "+value);
		}

	}

}
