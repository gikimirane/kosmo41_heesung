import java.time.LocalTime;

public class A4_LocalTimeDemo {

	public static void main(String[] args) {
		LocalTime now = LocalTime.now();
		System.out.println("���� �ð�: "+now);
		
		//2�ð� 10�� �� ȭ�� ���� ����
		LocalTime mt = now.plusHours(2);
		mt = mt.plusMinutes(10);
		
		System.out.println("ȭ�� ���� �ð� : "+mt);

	}

}
