import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class A7_FileCopy {

	public static void main(String[] args) {//1K����Ʈ ���� ��� ���� ���� ���α׷�
		Scanner sc = new Scanner(System.in);
		System.out.print("��� ���� :");
		String src = sc.nextLine();
		System.out.print("�纻 �̸� :");
		String dst = sc.nextLine();
		
		try (InputStream in = new FileInputStream(src);
				OutputStream out = new FileOutputStream(dst)){
			byte buf[] = new byte[1024];
			int len;
			while(true) {
				len = in.read(buf);
				if(len == -1)
					break;
				out.write(buf, 0, len);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

}
//public int read(byte[] b) throws IOException -> ���Ͽ� ����� �����͸� b�� ���޵� �迭�� ����
//void write(byte[] b, int off, int len) thorws IOEx 
//-> b�� ���޵� �迭�� �����͸� �ε��� off�������� len����Ʈ��ŭ ���Ͽ� ����