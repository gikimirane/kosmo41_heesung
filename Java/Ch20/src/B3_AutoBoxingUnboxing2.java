
public class B3_AutoBoxingUnboxing2 {

	public static void main(String[] args) {
		Integer a = 10;
		
		a++;	//new Integer(a.intValue()+1); ����ڽ̰� �����ڽ� ���ÿ�����
		System.out.println(a);
		
		a+=3;	//new Integer(a.intValue()+3);
		System.out.println(a);
		
		int r = a+5;		//���� ��ڽ� ����
		Integer r1 = a-5;	//���� ��ڽ� ����
		
		System.out.println(r);
		System.out.println(r1);

	}

}
