package com.study.pattern04.factory_method1.unit;

public class Firebet extends Unit {
	public Firebet() {
		type = UnitType.Firebet;
		name = "Firebet";
		hp = 80;
		exp = 60;
		
		System.out.println(this.name + "생성 !!");
	}
	@Override
	public void attack() {
		System.out.println(this.name + "공격!!");
		
	}

}
