class Apple5 {
	public String toString() {
		return "I am an apple.";
	}
}

class Orange5 {
	public String toString() {
		return "I am an orange.";
	}
}

class Box5<T> {
	private T ob;

	public void set(T o) {
		ob = o;
	}

	public T get() {
		return ob;
	}
}

public class A5_FruitAndBox2_Generic {

	public static void main(String[] args) {
		Box5<Apple5> a = new Box5<Apple5>();
		Box5<Orange5> b = new Box5<Orange5>();

		// a.set("Apple");
		// b.set("Orange");
		a.set(new Apple5());
		b.set(new Orange5());

		Apple5 ap = a.get();
		Orange5 or = b.get();

		System.out.println(ap);
		System.out.println(or);
	}

}
