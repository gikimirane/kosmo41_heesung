import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MultiClient5 
{

 
	public static void main(String[] args) throws UnknownHostException, IOException, NullPointerException
	{
		System.out.println("����� �̸��� �Է����ּ���.");
		Scanner s = new Scanner(System.in);
		String s_name = s.nextLine();
		
			try {
					String ServerIP = "192.168.0.111";
					Socket socket = new Socket(ServerIP,9999);
			
					//�������� ������ �޽����� ������� �ֿܼ� ����ϴ� ������
					Thread receiver = new Receiver5(socket);
					receiver.start();
			
					//����ڷκ��� ���� ���ڿ��� ������ �������ִ� �����ϴ� ������
					Thread sender = new Sender5(socket,s_name);
					sender.start();
			} 	
			catch (Exception e) 
			{
			System.out.println("����[MultiClient]:"+e);
			}
		
		}
	}
