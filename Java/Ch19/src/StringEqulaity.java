
public class StringEqulaity {

	public static void main(String[] args) {
		String a = new String("So Simple");
		String b = new String("So Simple");
		
		if (a==b)
			System.out.println("참조 동일");
		else
			System.out.println("참조 다름");
		
		if (a.equals(b))System.out.println("동일");
		else System.out.println("다름");

	}

}
