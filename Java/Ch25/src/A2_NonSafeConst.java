interface Animal1{
	int DOG=1;
	int CAT=2;
}

interface Person1{
	int MAN=1;
	int WOMAN=2;
}
public class A2_NonSafeConst {

	public static void main(String[] args) {
		who(Person1.MAN); //�������� �޼ҵ� ȣ��
		
		who(Animal1.DOG);//���������θ޼ҵ�ȣ��
	}
		public static void who(int man) {
			switch(man) {
			case Person1.MAN:
				System.out.println("���� �մ�");
				break;
			case Person1.WOMAN:
				System.out.println("���� �մ�");
				break;
			}
		}
	

}
