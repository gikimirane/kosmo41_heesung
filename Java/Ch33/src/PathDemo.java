import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo {

	public static void main(String[] args) {
		Path a = Paths.get("C:\\JavaStudy\\PathDemo.java");
		Path a1 = a.getRoot(); //get 메소드 호출의 성공 여부는 해당파일 디렉토리 존재여부와 상관 없어.
		Path a2 = a.getParent();
		Path a3 = a.getFileName();
		
		System.out.println("Absolute : "+a);
		System.out.println("Root : "+a1);
		System.out.println("Parent : "+a2);
		System.out.println("File : "+a3);

	}

}
