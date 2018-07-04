class BoxD<T>{
	private T ob;
	
	public void set(T o) {
		ob = o;
	}
	public T get() {
		return ob;
	}
}

class UnboxerD{
	public static <T> T openBox(BoxD<T> box) {
		return box.get();
	}
}
public class C2_GenericMethodBoxMaker2 {

	public static void main(String[] args) {
		BoxD<String> a = new BoxD<>();
		a.set("My Generic Method");
		
		String s = UnboxerD.<String>openBox(a);
		System.out.println(s);
	}

}
