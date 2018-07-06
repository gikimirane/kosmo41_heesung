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
		who(Person1.MAN); //정상적인 메소드 호출
		
		who(Animal1.DOG);//비정상적인메소드호출
	}
		public static void who(int man) {
			switch(man) {
			case Person1.MAN:
				System.out.println("남자 손님");
				break;
			case Person1.WOMAN:
				System.out.println("여자 손님");
				break;
			}
		}
	

}
