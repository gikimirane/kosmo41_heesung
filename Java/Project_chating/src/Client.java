import java.awt.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JList;

public class Client {
	
	private List User_list = new List();//전체접속자 list
	private List Room_list = new List();//전체 방 목록
	Scanner s = new Scanner(System.in);
	private Socket socket ;
	private String ip = "192.168.0.111";
	private int port = 9999;
	private String id = "";
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;
	String str = null;
	Vector user_list = new Vector();
	Vector room_list = new Vector();
	StringTokenizer st;
	

	
	private void send_massage(String str) {//서버에게 메세지 보내는 부분
		
		try {
			dos.writeUTF(str);
		} catch (IOException e) { // 메세지 처리부분.
			e.printStackTrace();
		}
		
	}
	private void network() {
		try {
			socket = new Socket(ip,port);
			
			if (socket != null) {
				Connection(); 
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void Connection() {//실제적인 메소드 연결 부분
		try {
		is = socket.getInputStream();
		dis = new DataInputStream(is);
		
		os = socket.getOutputStream();
		dos = new DataOutputStream(os);
		}catch(IOException e) {		
		}// Stream 설정 끝.
		
		//처음 접속시에 ID 전송
		System.out.println("아이디를 입력하세요");
		id = s.nextLine();
		send_massage(id);
		
		//사용자 추가
		user_list.add(id);
		User_list.add(id);
		
		
			
		
		Thread the = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
				
					String msg = s.nextLine();
					send_massage(msg); 
				}
			}
			
		});the.start();
		
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				
				while(true) {
					try {
						String msg = dis.readUTF();
						
					//	System.out.println("서버로부터 수신된 메세지 :"+msg);//메세지 수신
						
						inmassage(msg);
					} catch (IOException e) {
					} 
				}
			}
			
		}); th.start();
	
	}
	private void inmassage(String str)//서버로부터 들어오는 모든 메세지
	{
		st = new StringTokenizer(str, "/");
		String protocol = st.nextToken();
		String Massage = st.nextToken();
		
		if(protocol.equals("NewUser"))//새로운 접속자
		{
			user_list.add(Massage);

		}else if(protocol.equals("OldUser"))
		{
			user_list.add(Massage);
			
		}
	
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException, NullPointerException{
		Client a = new Client();
		a.network();
		
		

	}

}
