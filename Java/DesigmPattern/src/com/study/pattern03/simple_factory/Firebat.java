package com.study.pattern03.simple_factory;

public class Firebat implements Unit {
	public Firebat() {
	System.out.println("���̾�� ����!!");
	}

	@Override
	public void move() {
		System.out.println("���̾�� �̵�!!");
	}

}
