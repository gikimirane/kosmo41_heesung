package com.study.pattern.stretegy1;

public class BObj {
	AInterface ainter;
	public BObj() {
	ainter = new AImplement();
	
	}
	public void SomeFunc() {
		
		// 다음 기능ㅇ ㅣ필요합니다. A님 만들어줘여.
		//기능 구현을 위힘 - delegate 했다
	//	System.out.println("AAA");
	//	System.out.println("AAA");
	//	System.out.println("AAA");
		
		
		//Delegate 사용
		ainter.funcA();
		ainter.funcA();
		ainter.funcA();
	}
}
