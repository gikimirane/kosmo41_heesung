import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receiver3 extends Thread{
	Socket socket;
	BufferedReader in = null;
	//socket�� �Ű������� �޴� ������
	
	public Receiver3(Socket socket) {
		this.socket = socket;
		try {
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		} catch (Exception e) {
			System.out.println("����1:"+e);
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		while(in!=null) {
			try {
				System.out.println("Thread Receive:"+in.readLine());
			} catch (Exception e) {
				System.out.println("����2:"+e);
				// TODO: handle exception
			}
		}
		try {
			in.close();
		} catch (Exception e) {
			System.out.println("����3:"+e);
			// TODO: handle exception
		}
	}
}
