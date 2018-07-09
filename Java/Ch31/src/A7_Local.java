import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class A7_Local {

	public static void main(String[] args) {
		LocalDateTime today = LocalDateTime.of(2020, 4, 25, 11, 20);//����ð�
		LocalDateTime flight1 = LocalDateTime.of(2020, 5, 14, 11, 15);//�װ��� �ð� 1
		LocalDateTime flight2 = LocalDateTime.of(2020, 5, 13, 15, 30);//�װ��� �ð� 2
		
		//���� �װ��� ���� ����
		LocalDateTime myFlight;
		if(flight1.isBefore(flight2))
			myFlight = flight1;
		else
			myFlight = flight2;
		
		//���� �װ��� ž�±��� ���� ��¥ ���
		Period day = Period.between(today.toLocalDate(), myFlight.toLocalDate());
		//���� �װ��� ������� ���� �ð� ���
		Duration time = Duration.between(today.toLocalTime(), myFlight.toLocalTime());
		
		//���
		System.out.println(day);
		System.out.println(time);
	}

}
