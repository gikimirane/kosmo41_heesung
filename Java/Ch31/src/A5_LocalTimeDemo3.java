import java.time.Duration;
import java.time.LocalTime;

public class A5_LocalTimeDemo3 {
	public static void main(String[] args) {
	//pc�� pc �̿� ���� �ð�
	LocalTime start = LocalTime.of(14, 24, 35);
	System.out.println("PC �̿� ���� �ð�"+start);
	
	//end time
	LocalTime end = LocalTime.of(17, 31, 19);
	System.out.println("PC �̿� ���� �ð� : "+end);
	
	Duration between = Duration.between(start, end);
	System.out.println("�̿�ð�: "+between);
	System.out.println(between.toHours()+":"+between.toMinutes()+":"+between.toMillis());
	}
}
