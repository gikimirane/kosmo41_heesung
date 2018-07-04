interface Cry{
	void cry();
	
}

class Bet implements Cry{
	
	public void cry() {
		System.out.println("¾ß¿Ë~");
	}
}
class Dog{
	public void cry() {
		System.out.println("¿Ð¿Ð!");
	}
}
public class CheckCry {

	public static void main(String[] args) {
		Bet cat = new Bet();
		Dog dog = new Dog();
		if (cat instanceof Cry) {
			cat.cry();
		}
		if (dog instanceof Cry) {
			dog.cry();
		}

	}

}
