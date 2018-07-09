import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class B3_ZinedDate {

	public static void main(String[] args) {
		
		//한국 출발 201-09-09 14:30
		ZonedDateTime departure = ZonedDateTime.of(
				LocalDateTime.of(2017, 12, 9, 17, 25), ZoneId.of("Asia/Seoul"));
		System.out.println(departure);
		
		//파리 도착 2017-09-09 17-25
		ZonedDateTime arrival = ZonedDateTime.of(LocalDateTime.of(2017, 12, 9, 17, 25), ZoneId.of("Europe/Paris"));
		System.out.println(arrival);
		
		//비행시간
		System.out.println(Duration.between(departure, arrival));

	}

}
