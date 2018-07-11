import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo {

	public static void main(String[] args) {
		Path a = Paths.get("C:\\JavaStudy\\PathDemo.java");
		Path a1 = a.getRoot(); //get �޼ҵ� ȣ���� ���� ���δ� �ش����� ���丮 ���翩�ο� ��� ����.
		Path a2 = a.getParent();
		Path a3 = a.getFileName();
		
		System.out.println("Absolute : "+a);
		System.out.println("Root : "+a1);
		System.out.println("Parent : "+a2);
		System.out.println("File : "+a3);

	}

}
