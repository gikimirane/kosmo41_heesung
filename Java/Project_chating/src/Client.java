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
	
	private List User_list = new List();//��ü������ list
	private List Room_list = new List();//��ü �� ���
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
	

	
	private void send_massage(String str) {//�������� �޼��� ������ �κ�
		
		try {
			dos.writeUTF(str);
		} catch (IOException e) { // �޼��� ó���κ�.
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
	private void Connection() {//�������� �޼ҵ� ���� �κ�
		try {
		is = socket.getInputStream();
		dis = new DataInputStream(is);
		
		os = socket.getOutputStream();
		dos = new DataOutputStream(os);
		}catch(IOException e) {		
		}// Stream ���� ��.
		
		//ó�� ���ӽÿ� ID ����
		System.out.println("���̵� �Է��ϼ���");
		id = s.nextLine();
		send_massage(id);
		
		//����� �߰�
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
						
					//	System.out.println("�����κ��� ���ŵ� �޼��� :"+msg);//�޼��� ����
						
						inmassage(msg);
					} catch (IOException e) {
					} 
				}
			}
			
		}); th.start();
	
	}
	private void inmassage(String str)//�����κ��� ������ ��� �޼���
	{
		st = new StringTokenizer(str, "/");
		String protocol = st.nextToken();
		String Massage = st.nextToken();
		
		if(protocol.equals("NewUser"))//���ο� ������
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
