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
		//칼라이언트의 출력스트림을 저장할 해쉬맵 생성. //생성자
		clientMap = new HashMap<String, PrintWriter>();
		//해쉬맵 동기화 설정
		Collections.synchronizedMap(clientMap);
	}
	
	public void init() {
		
		try {
			serverSocket = new ServerSocket(9999);//9999포트로 서버소켓 객체생성
			System.out.println("서버가 시작되었습니다.");
			
			while (true) {
				socket = serverSocket.accept();
			System.out.println(socket.getInetAddress()+":"+socket.getPort());
			//	System.out.println("사용자:"+socket.getPort());
				Thread mst = new MultiServerT(socket); //쓰레드 생성
				mst.start(); //쓰레드 시동
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
	//접속된 모든 클라이언트들에게 메세지 전달
	public void sendAllMsg(String msg) {
		//출력스트림을 순차적으로 얻어와서 해당메세지 출력.
		Iterator it = clientMap.keySet().iterator();
		while(it.hasNext()) {
			try {
				PrintWriter it_out = (PrintWriter) clientMap.get(it.next());
				it_out.println(msg);
				
				
			} catch (Exception e) {
				System.out.println("예외"+e);
			}
		}
		
	}

	public static void main(String[] args) {
		MultiServer9 ms = new MultiServer9();
		ms.init();

	}
	//////////////////////////////내부클래스///////////////////////////////
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
			 String name =""; //클라이언트로 부터 받은 이름을 저장할 변수
			 try {
				name = in.readLine(); // 클라이언트에서 처음으로 보내는 메세지는
									// 클라이언트가 사용할 이름이다
				sendAllMsg(name+"니미 입장하였습니다.");//내소켓을 제외한 다른 클라이언트들소켓에 알림.
				clientMap.put(name, out); //해쉬맵에 키를 이름출력스트림객체를 저장
				System.out.println("현재 접속자 수는"+clientMap.size()+"명 입니다.");
				
				
				String s = "";
				while(in !=null) {
					s=in.readLine();
					System.out.println(s);
					if(s.equals("q")||s.equals("Q"))
						break;
					sendAllMsg(s);
				}
			} catch (Exception e) {
				System.out.println("예외"+e);
			}finally {
				//예외가 발생할때 퇴장, 해쉬맵에서 해당 데이터 제거.
				//보통 종료하거나 나가면 java.net.SocketException. 예외발생
				clientMap.remove(name);
				sendAllMsg(name+"니미 퇴장하셨습니다.");
				System.out.println("현재 접속자 수는"+clientMap.size()+"명 입니다");
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
