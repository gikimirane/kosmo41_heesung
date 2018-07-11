package com.study.pattern03.stretegy2;

public class WeaponManager {
	
	GameCharacter myWeapon;
	
	WeaponManager(){
		myWeapon = new GameCharacter();
		myWeapon.setWeapon(new Bullet());
	}
	
	public void ChangeBullet() {
		myWeapon.setWeapon(new Bullet());
	}
	public void ChangeArrow() {
		myWeapon.setWeapon(new Arrow());
	}
	
//	public void Fire() {
//		myWeapon.shoot();
//	}

}
