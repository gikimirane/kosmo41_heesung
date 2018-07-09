import java.time.LocalDate;
import java.time.Period;

public class A3_LocalDateDemo {

	public static void main(String[] args) {
		//����
		LocalDate today = LocalDate.now();
		System.out.println("Today:"+today);
		
		//���� ũ���� ����
		LocalDate xmas = LocalDate.of(today.getYear(), 12, 25);
		System.out.println("Xmas:"+xmas);
		
		Period left = Period.between(today, xmas);
		System.out.println("xmas���� ������"+left.getMonths()+"��"+left.getDays()+"��");
		
		//��¥�� ���� ǥ������ Period

	}

}
