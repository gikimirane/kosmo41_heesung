import java.time.LocalDate;
import java.time.Period;

public class A3_LocalDateDemo {

	public static void main(String[] args) {
		//오늘
		LocalDate today = LocalDate.now();
		System.out.println("Today:"+today);
		
		//올해 크리스 마스
		LocalDate xmas = LocalDate.of(today.getYear(), 12, 25);
		System.out.println("Xmas:"+xmas);
		
		Period left = Period.between(today, xmas);
		System.out.println("xmas까지 앞으로"+left.getMonths()+"월"+left.getDays()+"일");
		
		//날짜의 차를 표현해줌 Period

	}

}
