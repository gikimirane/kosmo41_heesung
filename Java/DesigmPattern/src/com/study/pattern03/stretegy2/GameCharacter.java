package com.study.pattern03.stretegy2;

public class GameCharacter {
	//������
	private Weapon weapon;
	
	//���ⱳȯ�� �����ϰ�~
	public void setWeapon(Weapon weapon) {
	this.weapon = weapon;	
	
	}
	
	// ��� ���
	public void Fire() {
		if(weapon == null) {
			System.out.println("���⸦ ���� �����ϼ���.");
		}else {
			weapon.shoot();
		}
	}
	
}
