class Animal{
	String name;
	int age;
	
	void printPet() {
		System.out.println("�̸� : "+name);
		System.out.println("���� : "+age);
	}
}

class Cat extends Animal{
	String variety;
	
	void printPet() {
		super.printPet();
		System.out.println("���� : "+variety);	
	}
}
public class PetJava {

	public static void main(String[] args) {
		Cat a = new Cat();
		a.name="�����";
		a.age=5;
		a.variety="�丣�þ�";
		a.printPet();
	}

}
