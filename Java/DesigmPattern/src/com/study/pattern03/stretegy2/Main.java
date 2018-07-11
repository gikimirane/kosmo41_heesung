package com.study.pattern03.stretegy2;

public class Main {

	public static void main(String[] args) {
		
		GameCharacter character = new GameCharacter();
		character.Fire();
		
		character.setWeapon(new Arrow());
		character.Fire();

		character.setWeapon(new Bullet());
		character.Fire();
		
		character.setWeapon(new Missile());
		character.Fire();
	}

}
