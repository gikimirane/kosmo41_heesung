import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class C1_SimpleWriter {

	public static void main(String[] args) {
		try(Writer out = new FileWriter("data.txt")){//������½�Ʈ������
			out.write('A');
			out.write('��');
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

}
