import java.io.FileInputStream;
import java.io.IOException;

import org.omg.CORBA_2_3.portable.InputStream;

public class A2_wdasf {
	public static void main(String[] arge) throws IOException{
		try{
		FileInputStream in = new FileInputStream("data.dat");//�Է½�Ʈ�� ����
		int dat = in.read(); //������ ����
		in.close();//�Է� ��Ʈ�� ����
		System.out.println(dat);
		System.out.printf("%c",dat);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}


