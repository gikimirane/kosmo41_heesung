package com.study.pattern02.stretegy3;

public class DatabaseUse {
	
	//접근점
	private Database db;
	
	//데이터베이스 교환 가능하게
	public void setDatbase(Database db) {
		this.db = db;
	}
	//기능 사용
	public void connect() {
		if (db == null) {
			System.out.println("데이터베이스를선택하세요.");
		} else {
			//Delegate 메서드 호출 : 구체적인 데이터 베이스의 종류는 몰라도 기능 사용 가능
			db.connectDatabase();
		}
	}
	

}
