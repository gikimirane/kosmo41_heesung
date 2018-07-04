class BoxA<T extends Number>{
	private T ob;
	
	public void set (T o) {
		ob = o;
	}
	
	public T get() {
		return ob;
	}
}
public class B4_BoundedBox {

	public static void main(String[] args) {
		BoxA<Integer> a = new BoxA<>();
		a.set(24);
		
		
		BoxA<Double> b = new BoxA<>();
		b.set(5.79);
		
		System.out.println(a.get());
		System.out.println(b.get());
		

	}

}
