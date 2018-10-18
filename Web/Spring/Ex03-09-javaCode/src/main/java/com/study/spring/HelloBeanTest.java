package com.study.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloBeanTest {
   public static void main(String[] args)
{

   
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(Config.class);
      
      
      // 2.Hello Bean 가져오기
      Hello helloA = (Hello)context.getBean("hello");
      helloA.print();
      
      // 3.Hello Bean 가져오기
      Hello helloB = (Hello)context.getBean("hello1");
      helloB.print();
            
      //4.PrintB Bean 가져오기
      Printer printer = context.getBean("printerB",Printer.class);
      helloA.setPrinter(printer);
      helloA.print();
      
      //5.싱클톤인지 확인
      
      System.out.println(helloA == helloB);
      //context.close();
   }
}