import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.IOException;

public class B5_BufferedDataInput {

	public static void main(String[] args) {
		try(DataInputStream in = new DataInputStream(
				new BufferedInputStream(
						new FileInputStream("data.dat")))){
			int a = in.readInt();
			double b = in.readDouble();
			
			System.out.println(a);
			System.out.println(b);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
