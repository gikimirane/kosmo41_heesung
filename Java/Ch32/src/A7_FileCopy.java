import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class A7_FileCopy {

	public static void main(String[] args) {//1K바이트 버퍼 기반 파일 복사 프로그램
		Scanner sc = new Scanner(System.in);
		System.out.print("대상 파일 :");
		String src = sc.nextLine();
		System.out.print("사본 이름 :");
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
//public int read(byte[] b) throws IOException -> 파일에 저장된 데이터를 b로 전달된 배열에 저장
//void write(byte[] b, int off, int len) thorws IOEx 
//-> b로 전달된 배열의 데이터를 인덱스 off에서부터 len바이트만큼 파일에 저장