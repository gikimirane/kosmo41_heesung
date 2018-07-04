interface Animal2{
	void cry();
	
}

class Bet2 implements Animal2{
	
	public void cry() {
		System.out.println("¾ß¿Ë~");
	}
}
class Dog2 implements Animal2{
	public void cry() {
		System.out.println("¿Ð¿Ð!");
	}
}
public class CheckCat2{

	public static void main(String[] args) {
		Bet2 cat = new Bet2();
		Dog2 dog = new Dog2();
		
		
		checkAnimal(cat);
		checkAnimal(dog);
	}
	public static void checkAnimal(Animal2 animal) {
		if(animal instanceof Bet2) {
			System.out.println("°í¾çÀÌ");
			animal.cry();
		}
		if (animal instanceof Dog2) {
			System.out.println("°­¾ÆÁö");
			animal.cry();
		}
	}

}
