import java.io.FileInputStream;
import java.io.IOException;

import org.omg.CORBA_2_3.portable.InputStream;

public class A2_wdasf {
	public static void main(String[] arge) throws IOException{
		try{
		FileInputStream in = new FileInputStream("data.dat");//입력스트림 생성
		int dat = in.read(); //데이터 읽음
		in.close();//입력 스트림 종료
		System.out.println(dat);
		System.out.printf("%c",dat);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}


