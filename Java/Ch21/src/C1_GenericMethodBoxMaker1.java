class BoxC<T>{
	private T ob;
	
	public void set(T o) {
		ob = o;
	}
	public T get() {
		return ob;
	}
}

class BoxFactoryC{
	public static <T> BoxC<T> makeBox(T o){
		BoxC<T> a = new BoxC<T>();
		a.set(o);
		return a;
	}
}
public class C1_GenericMethodBoxMaker1 {

	public static void main(String[] args) {
		BoxC<String> s = BoxFactoryC.makeBox("Sweet");
		System.out.println(s.get());
		
		BoxC<Double> d = BoxFactoryC.makeBox(7.25);
		System.out.println(d.get());
	}

}
