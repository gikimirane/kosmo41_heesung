import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class B4_DateTimeFor {

	public static void main(String[] args) {
		ZonedDateTime date = ZonedDateTime.of(
				LocalDateTime.of(2019, 4, 25, 11, 20), ZoneId.of("Asia/Seoul"));
		
		
		//출력의 포멧 정보는 java.time.format.DateTimeForMatter 인스턴스에 담음
		DateTimeFormatter a = DateTimeFormatter.ofPattern("yy-M-d");
		DateTimeFormatter b = DateTimeFormatter.ofPattern("yyyy-MM-d,H:m:s");
		DateTimeFormatter c = DateTimeFormatter.ofPattern("yyyy-MM-d,HH:mm:ss a VV");
		
		System.out.println(date.format(a));
		System.out.println(date.format(b));
		System.out.println(date.format(c));
		

	}

}

//y,M,d는 각각 년 월 일 출력 의미
// H,m,s는 각각 시 , 분 ,초 
//VV는 시간대 ID의 출력을 의미
//a는 오전오후를 나타냄