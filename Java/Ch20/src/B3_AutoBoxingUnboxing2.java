
public class B3_AutoBoxingUnboxing2 {

	public static void main(String[] args) {
		Integer a = 10;
		
		a++;	//new Integer(a.intValue()+1); 오토박싱과 오토언박싱 동시에진행
		System.out.println(a);
		
		a+=3;	//new Integer(a.intValue()+3);
		System.out.println(a);
		
		int r = a+5;		//오토 언박싱 진행
		Integer r1 = a-5;	//오토 언박싱 진행
		
		System.out.println(r);
		System.out.println(r1);

	}

}
