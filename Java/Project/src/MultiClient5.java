import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MultiClient5 
{

 
	public static void main(String[] args) throws UnknownHostException, IOException, NullPointerException
	{
		System.out.println("사용할 이름을 입력해주세요.");
		Scanner s = new Scanner(System.in);
		String s_name = s.nextLine();
		
			try {
					String ServerIP = "192.168.0.111";
					Socket socket = new Socket(ServerIP,9999);
			
					//서버에서 보내는 메시지를 사용자의 콘솔에 출력하는 쓰레드
					Thread receiver = new Receiver5(socket);
					receiver.start();
			
					//사용자로부터 얻은 문자열을 서버로 전송해주는 역할하는 쓰레드
					Thread sender = new Sender5(socket,s_name);
					sender.start();
			} 	
			catch (Exception e) 
			{
			System.out.println("예외[MultiClient]:"+e);
			}
		
		}
	}
