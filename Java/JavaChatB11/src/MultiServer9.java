import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultiServer9 {
	int a[] ;
	
	ServerSocket serverSocket = null;
	Socket socket = null;
	Map<String, PrintWriter> clientMap;
	
	public MultiServer9() {
		//Į���̾�Ʈ�� ��½�Ʈ���� ������ �ؽ��� ����. //������
		clientMap = new HashMap<String, PrintWriter>();
		//�ؽ��� ����ȭ ����
		Collections.synchronizedMap(clientMap);
	}
	
	public void init() {
		
		try {
			serverSocket = new ServerSocket(9999);//9999��Ʈ�� �������� ��ü����
			System.out.println("������ ���۵Ǿ����ϴ�.");
			
			while (true) {
				socket = serverSocket.accept();
			System.out.println(socket.getInetAddress()+":"+socket.getPort());
			//	System.out.println("�����:"+socket.getPort());
				Thread mst = new MultiServerT(socket); //������ ����
				mst.start(); //������ �õ�
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			try {
				serverSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//���ӵ� ��� Ŭ���̾�Ʈ�鿡�� �޼��� ����
	public void sendAllMsg(String msg) {
		//��½�Ʈ���� ���������� ���ͼ� �ش�޼��� ���.
		Iterator it = clientMap.keySet().iterator();
		while(it.hasNext()) {
			try {
				PrintWriter it_out = (PrintWriter) clientMap.get(it.next());
				it_out.println(msg);
				
				
			} catch (Exception e) {
				System.out.println("����"+e);
			}
		}
		
	}

	public static void main(String[] args) {
		MultiServer9 ms = new MultiServer9();
		ms.init();

	}
	//////////////////////////////����Ŭ����///////////////////////////////
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
			 String name =""; //Ŭ���̾�Ʈ�� ���� ���� �̸��� ������ ����
			 try {
				name = in.readLine(); // Ŭ���̾�Ʈ���� ó������ ������ �޼�����
									// Ŭ���̾�Ʈ�� ����� �̸��̴�
				sendAllMsg(name+"�Ϲ� �����Ͽ����ϴ�.");//�������� ������ �ٸ� Ŭ���̾�Ʈ����Ͽ� �˸�.
				clientMap.put(name, out); //�ؽ��ʿ� Ű�� �̸���½�Ʈ����ü�� ����
				System.out.println("���� ������ ����"+clientMap.size()+"�� �Դϴ�.");
				
				
				String s = "";
				while(in !=null) {
					s=in.readLine();
					System.out.println(s);
					if(s.equals("q")||s.equals("Q"))
						break;
					sendAllMsg(s);
				}
			} catch (Exception e) {
				System.out.println("����"+e);
			}finally {
				//���ܰ� �߻��Ҷ� ����, �ؽ��ʿ��� �ش� ������ ����.
				//���� �����ϰų� ������ java.net.SocketException. ���ܹ߻�
				clientMap.remove(name);
				sendAllMsg(name+"�Ϲ� �����ϼ̽��ϴ�.");
				System.out.println("���� ������ ����"+clientMap.size()+"�� �Դϴ�");
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

}
