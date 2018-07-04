class Box1<T>{
	protected T ob;
	public void set(T o) {ob=o;}
	public T get() {return ob;}
}
class SteelBox<T> extends Box1<T>{
	public SteelBox(T o) {
		ob = o;
	}
}
public class A1_GenericInheritance {

	public static void main(String[] args) {
		Box1<Integer> a = new SteelBox<>(7559);
		Box1<String> s = new SteelBox<>("Simple");
		
		System.out.println(a.get());
		System.out.println(s.get());

	}

}
