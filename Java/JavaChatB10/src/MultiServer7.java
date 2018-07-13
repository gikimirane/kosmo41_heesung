import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer7 {
	static ServerSocket serverSocket = null;
	static Socket socket = null;
	
	//������
	public MultiServer7() {
		
		
	}
	
	public void init() {
		try {
			serverSocket = new ServerSocket(9999);//9999��Ʈ�� �������� ��ü����
			System.out.println("������ ���۵Ǿ����ϴ�.");
			
			while (true) {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress()+":"+socket.getPort());
				
				Thread msr = new MultiServerT(socket); //������ ����
				msr.start(); //������ �õ�
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
				System.out.println("����"+e);
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
				System.out.println("����:"+e);
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
	
	//���ӵ� ��� Ŭ���̾�Ʈ�鿡�� �޽��� ����.
	public void sendAllMsg(String msg, PrintWriter out) {
		try {
			out.println(msg);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("����:"+e);
		}
	}

	}
