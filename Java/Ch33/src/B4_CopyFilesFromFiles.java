import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class B4_CopyFilesFromFiles {

	public static void main(String[] args) throws IOException {
		Path src = Paths.get("D:\\gmltjd25166\\study1\\Java\\Ch33\\src\\1.java");
		Path dst = Paths.get("D:\\gmltjd25166\\study1\\Java\\Ch33\\src\\2.java");
		
		//src�� �����ϴ� ������ dst�� �����ϴ� ��ġ�� �̸����� ����
		Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);

	}

}
