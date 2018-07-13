import java.util.StringTokenizer;

public class sadsad {

	public static void main(String[] args) {
		String a = "/r °³ Á¿°°´Ù";
		String b = a.substring(0, 2);
		String c = a.substring(3, 4);
		String d = a.substring(5);
		if(b.equalsIgnoreCase("/r")) {
			System.out.println(b);
			System.out.println(c);
			System.out.println(d);
		}
		int e = a.indexOf(" ");
		String ef = a.substring(e+1,e+2);
		System.out.println(ef);
		

		}
	}


