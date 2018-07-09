import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class B2_ZoneIdDate {

	public static void main(String[] args) {
		//�̰��� ���糯���� ����
		ZonedDateTime here = ZonedDateTime.now();
		System.out.println(here);
		
		// ������ ��¥�� �ð��� �ĸ�
		ZonedDateTime paris = ZonedDateTime.of(
		here.toLocalDateTime(), ZoneId.of("Europe/Paris"));
		//��¥�� �ð������� LocalDateTime �ν��Ͻ��� ��Ƽ� ��ȯ
		System.out.println(paris);
		
		//�̰��� �ĸ��� ����
		Duration diff = Duration.between(here, paris);
		System.out.println(diff);

	}

}
