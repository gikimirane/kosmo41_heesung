
public class B2_ImmutableString {

	public static void main(String[] args) {
		String str1 = "Simple String";
		String str2 = "Simple String";
		
		String str3=new String("Simple String");
		String str4=new String("Simple String");
		
		if(str1 == str2)
			System.out.println("str1과 str2는 동일 인스턴스 참조");
		else
			System.out.println("str1과 str2는 다른 인스턴스 참조");
		if(str3 == str4)
			System.out.println("str3과 str4는 동일 인스턴스 참조");
		else
			System.out.println("str3과 str4는 다른 인스턴스 참조");
// == 두개가 동일한 참조변수인지 참조변수의 참조 값 비교
		//equals는 객체의 값 을비교 == 두 객제가 동일한걸 참조하는지 비교
		//string 인스턴스는 Immutable 인스턴스!
		//생성되는 인스턴스의 수 최소화!
	}

}
