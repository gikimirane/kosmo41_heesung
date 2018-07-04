import java.math.BigInteger;

public class A1_SoBigInteger {

	public static void main(String[] args) {
		//long������ ǥ�� ������ ���� ũ�� ���
		System.out.println("�ִ� ���� : "+ Long.MAX_VALUE);
		System.out.println("�ּ� ����: "+Long.MIN_VALUE);
		System.out.println();
		
		//�ſ� ū ���� �ν��Ͻ��� ǥ��
		BigInteger a = new BigInteger("100000000000000");
		BigInteger b = new BigInteger("-99999999999999");
		
		//Big ��� ���� ����
		BigInteger r1 = a.add(b);
		System.out.println(r1);
		
		//����
		BigInteger r2 = a.multiply(b);
		System.out.println(r2);
		
		//�ν��Ͻ��� ���尪�� int�� ������ ��ȯ
		int c = r1.intValueExact();
		System.out.println("From BigInteger : "+c);
		

	}

}
