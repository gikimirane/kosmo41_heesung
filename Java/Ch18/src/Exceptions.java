import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Exceptions {

	public static void main(String[] args) {
		Path file = Paths.get("c:\\javastudy\\Simple.txt");
		BufferedWriter writer=null;
		
		try {
			writer = Files.newBufferedWriter(file);
			writer.write('A');
			writer.write('E');
			
			if(writer != null)
				writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

}
