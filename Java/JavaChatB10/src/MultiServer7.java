import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer7 {
	static ServerSocket serverSocket = null;
	static Socket socket = null;
	
	//생성자
	public MultiServer7() {
		
		
	}
	
	public void init() {
		try {
			serverSocket = new ServerSocket(9999);//9999포트로 서버소켓 객체생성
			System.out.println("서버가 시작되었습니다.");
			
			while (true) {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress()+":"+socket.getPort());
				
				Thread msr = new MultiServerT(socket); //쓰레드 생성
				msr.start(); //쓰레드 시동
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				serverSocket.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		MultiServer7 ms = new MultiServer7();
		ms.init();
	}
	
	class MultiServerT extends Thread{
		 Socket socket = null;
		 PrintWriter out = null;
		 BufferedReader in = null;
		 
		 public MultiServerT(Socket socket) {
			 this.socket = socket;
			 try {
				out = new PrintWriter(this.socket.getOutputStream(),true);
				in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			} catch (Exception e) {
				System.out.println("예외"+e);
			}
		 }
		 
		 @Override
		 public void run() {
			 String s ="";
			 try {
				while(in !=null) {
					s=in.readLine();
					if(s==null)
						break;
					if(s.equals("q")||s.equals("Q"))
						break;
					
					System.out.println(s);
					sendAllMsg(s,out);
				}
				System.out.println("Bye...~");
			} catch (Exception e) {
				System.out.println("예외:"+e);
			}finally {
				try {
					in.close();
					out.close();
					
					socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		 }
	}
	
	//접속된 모든 클라이언트들에게 메시지 전달.
	public void sendAllMsg(String msg, PrintWriter out) {
		try {
			out.println(msg);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("예외:"+e);
		}
	}

	}
