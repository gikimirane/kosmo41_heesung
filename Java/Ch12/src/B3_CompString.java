
public class B3_CompString {

	public static void main(String[] args) {
		String st1 = "Apple";
		String st2 = "apple";
		int cmp;

		if (st1.equals(st2))
			System.out.println("두 문자열은 같습니다");
		else
			System.out.println("두 문자열은 다릅니다");
		
		cmp = st1.compareTo(st2);
		
		if(cmp == 0)
			System.out.println("두 문자열은 일치합니다.");
		else if (cmp < 0)
		System.out.println("사전의 앞에 위치하는 문자 : "+st1);
		else 
			System.out.println("사전의 앞에 위치하는 문자: "+st2);
		
		if(st1.compareToIgnoreCase(st2)==0)
			System.out.println("같다");
		else
			System.out.println("다르다");

	}

}
