import java.time.Duration;
import java.time.Instant;

public class A1_InstantDemo {

	public static void main(String[] args) {
		Instant start = Instant.now();
		System.out.println("시작 : "+start.getEpochSecond());
		
		System.out.println("Time flies like an arrow.");
		
		Instant end = Instant.now();
		System.out.println("끝: "+end.getEpochSecond());
		
		Duration between = Duration.between(start, end); // 두 시각의 차 계산
		//Duration은 시각 차르 표현하기 위한 클래스
		
		System.out.println("밀리 초 단위 차:"+between.toMillis());

	}

}
