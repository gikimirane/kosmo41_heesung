package com.study.pattern02.stretegy2;

public class GameCharacter {
	//접근점
	private Weapon weapon;
	
	//무기교환이 가능하게~
	public void setWeapon(Weapon weapon) {
	this.weapon = weapon;	
	
	}
	
	// 기능 사용
	public void Fire() {
		if(weapon == null) {
			System.out.println("무기를 먼저 착용하세요.");
		}else {
			weapon.shoot();
		}
	}
	
}
