
public class B1_BoxingUnboxing {

	public static void main(String[] args) {
		//�ν��Ͻ��� ���� ���δ� �ڽ�
		Integer a = new Integer(10);
		Double b = new Double(3.14);
		
		System.out.println(a);
		System.out.println(b);
		System.out.println();
		
		//�޼ҵ� ȣ���� ���� ��ڽ�
		int i = a.intValue();
		double j = b.doubleValue();
		
		System.out.println(a);
		System.out.println(b);
		System.out.println();
		
		//��Ǵ �ν��Ͻ� ���� ������� ������
		a=new Integer(a.intValue()+10);
		b=new Double(b.doubleValue()+1.2);
		
		
		System.out.println(a);
		System.out.println(b);

	}

}
