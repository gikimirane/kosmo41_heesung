import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class C2_TextReader {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("���� ����: ");
		String src = sc.nextLine();
		
		try(Reader in = new FileReader(src)){//�����Է½�Ʈ������
			
			int ch;
			while(true) {
				ch = in.read();
				if (ch ==-1)//���̻� ���� ���ڰ� ���ٸ�
					break;
				System.out.println((char)ch);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

}
