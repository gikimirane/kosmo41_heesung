import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class A2_MakeFileAndDir {

	public static void main(String[] args) throws IOException {
		Path fp = Paths.get("D:\\gmltjd25166\\Java\\empty.txt");
		fp = Files.createFile(fp);  //���ϻ���
		
		Path dp1 = Paths.get("D:\\gmltjd25166\\Java\\Empty");
		dp1 = Files.createDirectory(dp1);//���丮����
		
		Path dp2 = Paths.get("D:\\gmltjd25166\\Java\\Empty");
		dp2 = Files.createDirectories(dp2);	//����� ��� ���丮 ����
		
		System.out.println(fp);
		System.out.println(dp1);
		System.out.println(dp2);
	}

}
