import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class C2_TextReader {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("읽을 파일: ");
		String src = sc.nextLine();
		
		try(Reader in = new FileReader(src)){//문자입력스트림생성
			
			int ch;
			while(true) {
				ch = in.read();
				if (ch ==-1)//더이상 읽을 문자가 없다면
					break;
				System.out.println((char)ch);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

}
