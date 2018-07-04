import java.math.BigDecimal;

public class WowBigDecimal {

	public static void main(String[] args) {
		
		
		BigDecimal a = new BigDecimal("1.6");
		BigDecimal b = new BigDecimal("0.1");
		
		System.out.println(a.add(b));
		System.out.println(a.multiply(b));

	}

}
