interface Greet{
	void greet();
	
}

interface Bye extends Greet{
	void bye();
}

class Greeting implements Bye{
	public void greet() {
		System.out.println("¾È³ç!");
	}
	public void bye() {
		System.out.println("ÀßÀÖ¾î!");
	}
}
public class Mee2 {

	public static void main(String[] args) {
		Greeting a = new Greeting();
		a.greet();
		a.bye();

	}

}
