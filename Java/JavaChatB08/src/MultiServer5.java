import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer5 {
	
	static ServerSocket serverSocket = null;
	static Socket socket = null;
	static PrintWriter out = null;
	static BufferedReader in = null;
	static String s = "";
	
	public MultiServer5() {
		
	}
	
	public static void init() {
		try {
			serverSocket = new ServerSocket(9999);
			System.out.println("������ ���۵Ǿ����ϴ�.");
			
			socket = serverSocket.accept();
			System.out.println(socket.getInetAddress()+":"+socket.getPort());
			
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			while (in != null) {
				s =in.readLine();
				if (s ==null)
					break;
				if ( s.equals("q")||s.equals("Q"))
					break;
				
				System.out.println(s);
				//out.println(s);
				sendAllMsg(s);
			}
			System.out.println("Bye..~");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
				out.close();
				
				socket.close();
				serverSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	
	public static void sendAllMsg(String msg) {
		try {
			out.println(msg);
		} catch (Exception e) {
			System.out.println("���� : "+e);
		}
	}
	public static void main(String[] args) {
		init();

	}

}
