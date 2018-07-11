import java.net.URL;

public class NetworkEx2 {

	public static void main(String[] args)throws Exception {
		URL ur1 =new URL("http://www.enjoypuzzle.com:80/board/bbsview/nemonotice/260/1#1");
		
		System.out.println("ur1.getAuthority():"+ur1.getAuthority());
		System.out.println("ur1.getContent(): "+ur1.getContent());
		System.out.println("ur1.getDefaultport():"+ur1.getDefaultPort());
		System.out.println("ur1.getPort():"+ur1.getPort());
		System.out.println("ur1.getFile():"+ur1.getFile());
		System.out.println("ur1.getHost():"+ur1.getHost());
		System.out.println("ur1.getProtocol():"+ur1.getProtocol());
		System.out.println("ur1.getQuery():"+ur1.getQuery());
		System.out.println("ur1.getRef():"+ur1.getRef());
		System.out.println("ur1.getUserInfo():"+ur1.getUserInfo());
		System.out.println("ur1.toExternalForm():"+ur1.toExternalForm());
		System.out.println("ur1.toURI():"+ur1.toURI());

	}

}
