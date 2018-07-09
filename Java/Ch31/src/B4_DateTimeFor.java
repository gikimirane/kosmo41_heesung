import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class B4_DateTimeFor {

	public static void main(String[] args) {
		ZonedDateTime date = ZonedDateTime.of(
				LocalDateTime.of(2019, 4, 25, 11, 20), ZoneId.of("Asia/Seoul"));
		
		
		//����� ���� ������ java.time.format.DateTimeForMatter �ν��Ͻ��� ����
		DateTimeFormatter a = DateTimeFormatter.ofPattern("yy-M-d");
		DateTimeFormatter b = DateTimeFormatter.ofPattern("yyyy-MM-d,H:m:s");
		DateTimeFormatter c = DateTimeFormatter.ofPattern("yyyy-MM-d,HH:mm:ss a VV");
		
		System.out.println(date.format(a));
		System.out.println(date.format(b));
		System.out.println(date.format(c));
		

	}

}

//y,M,d�� ���� �� �� �� ��� �ǹ�
// H,m,s�� ���� �� , �� ,�� 
//VV�� �ð��� ID�� ����� �ǹ�
//a�� �������ĸ� ��Ÿ��