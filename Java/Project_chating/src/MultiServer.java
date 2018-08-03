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
	
	//��Ʈ��ũ ���� ����
	ServerSocket serverSocket = null;
	Socket socket = null;
	Map<String, PrintWriter> clientMap;
	private String ip = "192.168.0.111";
	private int port =  9999;

	
	//�׿� ������
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
					serverSocket = new ServerSocket(9999);// 9999��Ʈ�� �������� ��ü����
					
					while(true) 
					{
					System.out.println("������ ���۵Ǿ����ϴ�. ����� ���� �����\n");
					socket = serverSocket.accept();
		
					System.out.println(socket.getInetAddress()
							           + ":" + socket.getPort());
					Userinfo user = new Userinfo(socket);
					user.start(); //��ü�� ������ ����
					
						
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
	private void UserNetwork() //user �г��� �޴°�
	{
		try {
		is = user_socket.getInputStream();
		dis = new DataInputStream(is);
		
		os = user_socket.getOutputStream();
		dos = new DataOutputStream(os);
		
		Nickname = dis.readUTF(); //������� �г����� �޴´�.
		System.out.println(Nickname + ":����");
		user_vc.add(this);//����ڿ��� �˸��� �ڽ� Vector�� �߰�
		BroadCast("NewUser/"+Nickname); // ��������ڿ��� �˷���
		
		//�ڽſ��� ���� ����� �˸�
		for(int i=0;i<user_vc.size();i++) {
			Userinfo u = (Userinfo)user_vc.elementAt(i);
			send_message("OldUser/"+u.Nickname);
		}
		}catch(IOException e) {}

	}
	
	public void run() //�����忡�� ó���� ����
	{
		while(true)
		{
			try {
				String msg = dis.readUTF();//�޼��� ����
				System.out.println(Nickname+": ����ڷκ��� ���� �޼��� : "+msg+"\n");
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	} //run �޼ҵ� ��
	private void BroadCast(String str)//��ü ����ڿ��� �޼��� ������ �κ�
	{
		for(int i=0;i<user_vc.size();i++)
		{
			Userinfo u = (Userinfo)user_vc.elementAt(i);
			u.send_message("NewUser/"+Nickname);
		}
		System.out.println("���� ���ӵ� ����� �� : "+user_vc.size()+" ���Դϴ�");
	}
	private void send_message(String str)//���ڿ� �޾Ƽ� ����
	{
		try {
			dos.writeUTF(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
