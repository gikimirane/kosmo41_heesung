import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class D2_StringReader {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new FileReader("String.txt"))){
			String str;
			while(true) {
				str = br.readLine(); //한문장 읽어들이기
				if (str == null)
					break;
				System.out.println(str);
				
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

}
