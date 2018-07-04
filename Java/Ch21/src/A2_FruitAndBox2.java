class Apple2 {
	public String toString() {
		return "I am an apple.";
	}
}

class Orange2 {
	public String toString() {
		return "I am an orange.";
	}
}

class Box2 {
	private Object ob;

	public void set(Object a) {
		ob = a;
	}

	public Object get() {
		return ob;
	}
}

public class A2_FruitAndBox2 {

	public static void main(String[] args) {
		Box2 b = new Box2();
		Box2 c = new Box2();
		
		b.set(new Apple2());
		c.set(new Orange2());
		
		Apple2 ap = (Apple2)b.get();
		Orange2 or = (Orange2)c.get();
		
		System.out.println(ap);
		System.out.println(or);

	}

}
