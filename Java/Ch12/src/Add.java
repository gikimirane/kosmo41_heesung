import java.util.StringTokenizer;

public class Add {

	public static void main(String[] args) {
		StringTokenizer a=new StringTokenizer("a,b,c",",");
		StringTokenizer b=new StringTokenizer("1,2,3",",");
		
		String s = a.nextToken();
		int num = Integer.parseInt(b.nextToken());
		
		while(a.hasMoreTokens()) {
			s=s+"+"+(a.nextToken());
			num = num+Integer.parseInt(b.nextToken());
		}
		System.out.println(s+"="+num);
	}

}
