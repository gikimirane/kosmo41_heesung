import java.time.LocalDate;
import java.time.LocalDateTime;

public class Date0fExecution {
	static String date;
	static String a;
	
	static {
		LocalDate a = LocalDate.now();
		date = a.toString();
	}
	


	public static void main(String[] args) {
		System.out.println(date);

	}

}
