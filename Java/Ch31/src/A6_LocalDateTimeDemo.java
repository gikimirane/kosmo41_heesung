import java.time.LocalDateTime;

public class A6_LocalDateTimeDemo {

	public static void main(String[] args) {
		//현재 날짜와 시각
		LocalDateTime a = LocalDateTime.now();
		System.out.println(a);
		
		//영국 바이어와 22시간 35분 뒤 화상 미팅 예정
		LocalDateTime b = a.plusHours(22);
		b=b.plusMinutes(35);
		
		//영국 바이어와 화싱 미팅 날짜와 시각
		System.out.println(b);
	}

}
