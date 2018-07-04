class Apple3 {
	public String toString() {
		return "I am an apple.";
	}
}

class Orange3 {
	public String toString() {
		return "I am an orange.";
	}
}

class Box3 {
	private Object ob;

	public void set(Object a) {
		ob = a;
	}

	public Object get() {
		return ob;
	}
}
public class A3_FruitAndBoxFault {

	public static void main(String[] args) {
		
		Box3 b = new Box3();
		Box3 c = new Box3();
		
		b.set("Apple");
		c.set("Orange");
		
		Apple3 ap = (Apple3)b.get();
		Orange3 or = (Orange3)c.get();
		
		System.out.println(ap);
		System.out.println(or);

	}

}
