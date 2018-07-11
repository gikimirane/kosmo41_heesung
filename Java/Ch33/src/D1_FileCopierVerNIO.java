import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class D1_FileCopierVerNIO {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("��� ����:");
		Path src = Paths.get(sc.nextLine());
		System.out.println("�纻�̸�:");
		Path dst = Paths.get(sc.nextLine() );
		
		
		//�ϳ��� ���� ����
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		//try���� �ΰ��� ä�� ����
		try(FileChannel ifc = FileChannel.open(src, StandardOpenOption.READ);
				FileChannel ofc = FileChannel.open(dst, StandardOpenOption.WRITE,StandardOpenOption.CREATE)){
			int num;
			while(true) {
				num = ifc.read(buf);//ä�� ifc ���� ���۷� �о����
				if(num==-1)
					break;
				
				buf.flip(); //��� ��ȯ
				ofc.write(buf); //���ۿ��� ä�� ofc�� ������ ����
				buf.clear(); //���ۺ���
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}	

}
