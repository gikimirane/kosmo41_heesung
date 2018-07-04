class Apple4 {
	public String toString() {
		return "I am an apple.";
	}
}

class Orange4 {
	public String toString() {
		return "I am an orange.";
	}
}

class Box4 {
	private Object ob;

	public void set(Object a) {
		ob = a;
	}

	public Object get() {
		return ob;
	}
}
public class A4_FruitAndBoxFault2 {

	public static void main(String[] args) {
		Box4 b = new Box4();
		Box4 c = new Box4();
		
		b.set("Apple");
		c.set("Orange");
		
		
		System.out.println(b.get());
		System.out.println(c.get());

	}

}
