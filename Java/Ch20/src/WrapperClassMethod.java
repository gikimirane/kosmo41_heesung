
public class WrapperClassMethod {

	public static void main(String[] args) {
		Integer n1 = Integer.valueOf(5);
		Integer n2 = Integer.valueOf("1024");
		
		System.out.println("큰수 : "+Integer.max(n1, n2));
		System.out.println("작은수: "+Integer.min(n1, n2));
		System.out.println("합: "+Integer.sum(n1, n2));
		System.out.println();
		
		//정수에 대한 2진,8진,16진수 표현결과 반환 클래스 메소드
		System.out.println("2진표현"+Integer.toBinaryString(12));
		System.out.println("8"+Integer.toOctalString(12));
		System.out.println("16"+Integer.toHexString(12));

	}

}
