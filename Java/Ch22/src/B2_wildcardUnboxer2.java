class Box4<T>{
	protected T ob;
	public void set(T o) {ob=o;}
	public T get() {return ob;}
	
	@Override
	public String toString() {
		return ob.toString();
	}
}
class Unboxer4{
	public static <T> T openBox(Box4<T> box) {
		return box.get();
	}
	
	public static void peekBox(Box4<?> box) {
		System.out.println(box);
	}
}
public class B2_wildcardUnboxer2 {

	public static void main(String[] args) {
		Box4<String> a = new Box4<>();
		a.set("So Simple String");
		Unboxer4.peekBox(a);
		Box4<Integer> b = new Box4<>();
		b.set(123);
		Unboxer4.peekBox(b);

	}

}
//와일드카드 <?> 는 어떤것이든 받을수 있는 타입
