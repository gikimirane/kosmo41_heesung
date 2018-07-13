package com.study.pattern04.factory_method2;

public class Main {

	public static void main(String[] args) {
		DatabaseFactory factory = new DatabaseFactoryUse();
		
		//어떤 데이터 베이스가 사용될지 여기서는 모른다
		//팩토리메서드에서 제공되는 데이터베이스를 그냥 사용한다.
		Database db = factory.setDatabase();
		
		//데이터베이스 접속
		db.connectDatabase();
		
		//접속된 데이터베이스를 이용해 업무처리를 한다
		db.insertData();
		db.selectData();
		
		
		

	}

}
