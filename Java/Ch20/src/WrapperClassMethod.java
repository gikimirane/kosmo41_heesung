
public class WrapperClassMethod {

	public static void main(String[] args) {
		Integer n1 = Integer.valueOf(5);
		Integer n2 = Integer.valueOf("1024");
		
		System.out.println("ū�� : "+Integer.max(n1, n2));
		System.out.println("������: "+Integer.min(n1, n2));
		System.out.println("��: "+Integer.sum(n1, n2));
		System.out.println();
		
		//������ ���� 2��,8��,16���� ǥ����� ��ȯ Ŭ���� �޼ҵ�
		System.out.println("2��ǥ��"+Integer.toBinaryString(12));
		System.out.println("8"+Integer.toOctalString(12));
		System.out.println("16"+Integer.toHexString(12));

	}

}
