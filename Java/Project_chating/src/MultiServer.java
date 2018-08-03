import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class MultiServer extends Thread { 
	
	//네트워크 위한 변수
	ServerSocket serverSocket = null;
	Socket socket = null;
	Map<String, PrintWriter> clientMap;
	private String ip = "192.168.0.111";
	private int port =  9999;

	
	//그외 변수들
	Vector user_list = new Vector();
	Vector room_list = new Vector();

	private MultiServer() {
		clientMap = new HashMap<String, PrintWriter>();
		Collections.synchronizedMap(clientMap);
	}
	


	void init() {
		
			
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
					serverSocket = new ServerSocket(9999);// 9999포트로 서버소켓 객체생성
					
					while(true) 
					{
					System.out.println("서버가 시작되었습니다. 사용자 접속 대기중\n");
					socket = serverSocket.accept();
		
					System.out.println(socket.getInetAddress()
							           + ":" + socket.getPort());
					Userinfo user = new Userinfo(socket);
					user.start(); //객체의 스레드 실행
					
						
					}
				} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	});
			th.start();
	}
	private void Network() {
		try {
			socket = new Socket(ip,port);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void main(String[] args) {
		MultiServer a = new MultiServer();
		a.init();
		
	}

}

class Userinfo extends Thread
{
	private Vector user_vc = new Vector();
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	private Socket user_socket;
	private String Nickname = "";
	
	Userinfo(Socket soc)
	{
		this.user_socket = soc;
		
		UserNetwork();
		
			
	}
	private void UserNetwork() //user 닉네임 받는것
	{
		try {
		is = user_socket.getInputStream();
		dis = new DataInputStream(is);
		
		os = user_socket.getOutputStream();
		dos = new DataOutputStream(os);
		
		Nickname = dis.readUTF(); //사용자의 닉네임을 받는다.
		System.out.println(Nickname + ":접속");
		user_vc.add(this);//사용자에게 알린후 자신 Vector에 추가
		BroadCast("NewUser/"+Nickname); // 기존사용자에게 알려줌
		
		//자신에게 기존 사용자 알림
		for(int i=0;i<user_vc.size();i++) {
			Userinfo u = (Userinfo)user_vc.elementAt(i);
			send_message("OldUser/"+u.Nickname);
		}
		}catch(IOException e) {}

	}
	
	public void run() //쓰레드에서 처리할 내용
	{
		while(true)
		{
			try {
				String msg = dis.readUTF();//메세지 수신
				System.out.println(Nickname+": 사용자로부터 들어온 메세지 : "+msg+"\n");
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	} //run 메소드 끝
	private void BroadCast(String str)//전체 사용자에게 메세지 보내는 부분
	{
		for(int i=0;i<user_vc.size();i++)
		{
			Userinfo u = (Userinfo)user_vc.elementAt(i);
			u.send_message("NewUser/"+Nickname);
		}
		System.out.println("현재 접속된 사용자 수 : "+user_vc.size()+" 명입니다");
	}
	private void send_message(String str)//문자열 받아서 전송
	{
		try {
			dos.writeUTF(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
