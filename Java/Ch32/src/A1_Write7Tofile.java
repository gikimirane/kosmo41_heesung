import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.omg.CORBA_2_3.portable.InputStream;

public class A1_Write7Tofile {

	public static void main(String[] args) throws IOException 	{
		OutputStream out = new FileOutputStream("data.dat");//출력 스트림 생성
		out.write(65); //65 저장
		out.close();// 스티림 종료

	}

}
