class Apple1 {
	public String toString() {
		return "I am an apple.";
	}
}

class Orange1 {
	public String toString() {
		return "I am an orange.";
	}
}

class AppleBox1 {
	private Apple1 ap;

	public void set(Apple1 a) {
		ap = a;
	}

	public Apple1 get() {
		return ap;
	}
}

class OrnageBox1 {
	private Orange1 or;

	public void set(Orange1 o) {
		or = o;
	}

	public Orange1 get() {
		return or;
	}
}

public class A1_FruitAndBox1 {

	public static void main(String[] args) {
		AppleBox1 a = new AppleBox1();
		OrnageBox1 b = new OrnageBox1();
		
		a.set(new Apple1());
		b.set(new Orange1());
		
		Apple1 ap = a.get();
		Orange1 or = b.get();
		
		System.out.println(ap);
		System.out.println(or);
		

	}

}
