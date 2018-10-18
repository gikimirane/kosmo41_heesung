package com.study.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloBeanTest {

	public static void main(String[] args) {
		
		String configLocation = "classpath:beans.xml";
		ApplicationContext context = new GenericXmlApplicationContext(configLocation);
		//2. 클래스 패스가 아닌 파일시스템에서 설정파일읽기
		//String configLocation = "file:src/main/resources/config/beans.xml";
		
		//3. 특정 경로에 있는 모든 xml 파일을 설정파일로 사용하고 싶은경우
		//String configLocation = "classpath:config/beans*.xml;
		
		//B.Hello Bean 가져오기
		Hello hello = (Hello) context.getBean("hello");
		hello.print();
		
//		Printer printer = context.getBean("printerB", Printer.class);
//		hello.setPrinter(printer);
//		hello.print();

	}

}
