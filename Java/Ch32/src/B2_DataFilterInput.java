import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.IOException;

public class B2_DataFilterInput {

	public static void main(String[] args) {
		try(DataInputStream in = new DataInputStream(new FileInputStream("data.dat"))){
			int num1 = in.readInt();//µ•¿Ã≈Õ≤®≥ø
			double num2 = in.readDouble();
			System.out.println(num1);
			System.out.println(num2);
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

}
