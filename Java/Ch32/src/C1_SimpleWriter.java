import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class C1_SimpleWriter {

	public static void main(String[] args) {
		try(Writer out = new FileWriter("data.txt")){//문자출력스트림생성
			out.write('A');
			out.write('한');
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

}
