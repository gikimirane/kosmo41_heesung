import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class A7_Local {

	public static void main(String[] args) {
		LocalDateTime today = LocalDateTime.of(2020, 4, 25, 11, 20);//현재시각
		LocalDateTime flight1 = LocalDateTime.of(2020, 5, 14, 11, 15);//항공편 시간 1
		LocalDateTime flight2 = LocalDateTime.of(2020, 5, 13, 15, 30);//항공편 시간 2
		
		//빠른 항공편 선택 과정
		LocalDateTime myFlight;
		if(flight1.isBefore(flight2))
			myFlight = flight1;
		else
			myFlight = flight2;
		
		//빠른 항공편 탑승까지 남은 날짜 계산
		Period day = Period.between(today.toLocalDate(), myFlight.toLocalDate());
		//빠른 항공편 비행까지 남은 시간 계산
		Duration time = Duration.between(today.toLocalTime(), myFlight.toLocalTime());
		
		//출력
		System.out.println(day);
		System.out.println(time);
	}

}
