import java.time.LocalDateTime;

public class A6_LocalDateTimeDemo {

	public static void main(String[] args) {
		//���� ��¥�� �ð�
		LocalDateTime a = LocalDateTime.now();
		System.out.println(a);
		
		//���� ���̾�� 22�ð� 35�� �� ȭ�� ���� ����
		LocalDateTime b = a.plusHours(22);
		b=b.plusMinutes(35);
		
		//���� ���̾�� ȭ�� ���� ��¥�� �ð�
		System.out.println(b);
	}

}
