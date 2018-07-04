import java.util.StringTokenizer;

public class G_TokenizeString {

	public static void main(String[] args) {
		StringTokenizer a = new StringTokenizer("PM:08:45",":");
		
		while(a.hasMoreTokens())
			System.out.print(a.nextToken()+' ');
		System.out.println();
		
		StringTokenizer b = new StringTokenizer("12+36+-8/2=44","+-/=");
		
		while(b.hasMoreTokens())
			System.out.print(b.nextToken()+' ');
		System.out.println();
	}

}
