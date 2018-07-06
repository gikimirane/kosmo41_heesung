class Outer{
	private static int num=0;
	static class Nested1{
		void add(int n) {num+=n;}
	}
	
	static class Nested2{
		int get() {return num;}
	}
}
public class A1_StaticNested {

	public static void main(String[] args) {
		Outer.Nested1 a = new Outer.Nested1();
		a.add(5);
		Outer.Nested2 b = new Outer.Nested2();
		System.out.println(b.get());

	}

}
