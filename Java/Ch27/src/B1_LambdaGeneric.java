@FunctionalInterface
interface Claculate2<T>{
	T cal(T a,T b);
}
public class B1_LambdaGeneric {

	public static void main(String[] args) {
		Claculate2<Integer> c1 = (a,b) -> a+b;
		System.out.println(c1.cal(4, 3));
		
		Claculate2<Double> c2 = (a,b)->a+b;
		System.out.println(c2.cal(4.32, 3.45));

	}

}
