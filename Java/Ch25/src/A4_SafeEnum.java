enum Animal2{ DOG, CAT}
enum Person2{MAN, WOMAN}
public class A4_SafeEnum {

	public static void main(String[] args) {
		System.out.println(Animal2.DOG);
		//�������� �޼ҵ� ȣ��
		who(Person2.MAN);
		
		//������
		//who(Animal2.DOG);
		
		//if(Person2.MAN == 0){}  có�� ���ڷ� ���ϸ� ������
		

	}
	public static void who(Person2 man) {
		switch(man) {
		case MAN:
			System.out.println("���ڼմ�");
			break;
		case WOMAN:
			System.out.println("���� �մ�");
			break;
		}
	}

}
