package com.study.pattern03.simple_factory;

public class Medic implements Unit {
	
	public Medic() {
	System.out.println("�޵� ����!!");
	}

	@Override
	public void move() {
		System.out.println("�޵� �̵�!!");
	}

}
