class Box<T>{
	private T ob;
	
	public void set(T o) {
	ob = o;
	}
	
	public T get() {
		return ob;
	}
}
public class PrimitiverAndGeneric {

	public static void main(String[] args) {
		Box<Integer> a = new Box<Integer>();
		a.set(125);//오토박싱진행
		int b = a.get(); //오토 언박싱 진행
		System.out.println(b);
		
		//타입인자로 기본자료형올수없다. int x Integer o
		//뤠퍼클래스가 필요한 이유

	}

}
