import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class A4_Read7 {

	public static void main(String[] args) throws IOException{
		InputStream in = null;
		try {
			in = new FileInputStream("data.dat");
			int dat = in.read();
			System.out.println(dat);
		}
		finally {
			if (in!=null) //입력스트림 생성 성공했다면
				in.close();
		}

	}

}
