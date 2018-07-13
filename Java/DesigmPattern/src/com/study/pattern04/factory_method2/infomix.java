package com.study.pattern04.factory_method2;

public class infomix extends Database {
	
	public infomix() {
		name = "infomix";
		rows = 30;
		}

		@Override
		public void connectDatabase() {
			System.out.println(name+"에 접속했습니다.");
		}


}
