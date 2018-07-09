import java.time.Duration;
import java.time.LocalTime;

public class A5_LocalTimeDemo3 {
	public static void main(String[] args) {
	//pc방 pc 이용 시작 시각
	LocalTime start = LocalTime.of(14, 24, 35);
	System.out.println("PC 이용 시작 시각"+start);
	
	//end time
	LocalTime end = LocalTime.of(17, 31, 19);
	System.out.println("PC 이용 종료 시각 : "+end);
	
	Duration between = Duration.between(start, end);
	System.out.println("이용시간: "+between);
	System.out.println(between.toHours()+":"+between.toMinutes()+":"+between.toMillis());
	}
}
