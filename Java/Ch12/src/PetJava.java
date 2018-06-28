class Animal{
	String name;
	int age;
	
	void printPet() {
		System.out.println("이름 : "+name);
		System.out.println("나이 : "+age);
	}
}

class Cat extends Animal{
	String variety;
	
	void printPet() {
		super.printPet();
		System.out.println("종류 : "+variety);	
	}
}
public class PetJava {

	public static void main(String[] args) {
		Cat a = new Cat();
		a.name="양순이";
		a.age=5;
		a.variety="페르시안";
		a.printPet();
	}

}
