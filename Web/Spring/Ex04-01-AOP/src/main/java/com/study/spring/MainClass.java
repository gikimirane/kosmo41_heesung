package com.study.spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

   public static void main(String[] args) {

      AbstractApplicationContext context = 
            new GenericXmlApplicationContext("classpath:beans.xml");
      
      Student student = context.getBean("student",Student.class);
      student.getStudentInfo();
      
      
      Worket worker = context.getBean("worker",Worket.class);
      worker.getWorkerInfo();
      
      context.close();
      
      
   }

}