enum Animal2{ DOG, CAT}
enum Person2{MAN, WOMAN}
public class A4_SafeEnum {

	public static void main(String[] args) {
		System.out.println(Animal2.DOG);
		//정상적인 메소드 호출
		who(Person2.MAN);
		
		//비정상
		//who(Animal2.DOG);
		
		//if(Person2.MAN == 0){}  c처럼 숫자로 비교하면 에러남
		

	}
	public static void who(Person2 man) {
		switch(man) {
		case MAN:
			System.out.println("남자손님");
			break;
		case WOMAN:
			System.out.println("여성 손님");
			break;
		}
	}

}
