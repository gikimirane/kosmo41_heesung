
public class B1_BoxingUnboxing {

	public static void main(String[] args) {
		//인스턴스에 값을 감싸는 박싱
		Integer a = new Integer(10);
		Double b = new Double(3.14);
		
		System.out.println(a);
		System.out.println(b);
		System.out.println();
		
		//메소드 호출을 통한 언박싱
		int i = a.intValue();
		double j = b.doubleValue();
		
		System.out.println(a);
		System.out.println(b);
		System.out.println();
		
		//래풔 인스턴스 값의 증가방법 불편행
		a=new Integer(a.intValue()+10);
		b=new Double(b.doubleValue()+1.2);
		
		
		System.out.println(a);
		System.out.println(b);

	}

}
