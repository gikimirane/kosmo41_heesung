interface Printable{
	void print(String s);//매개변수 하나, 변환형 void
}
public class A2_OneParamNoreturn {

	public static void main(String[] args) {
		Printable p;
		p=(String s) ->{System.out.println(s);};
		p.print("Lambda exp one.");//줄임없는 표현
		
		p=(String s) -> System.out.println(s);
		p.print("Lambda exp two");//중괄호 {} 생략
		
		p=(s) -> System.out.println(s);
		p.print("Lambda exp three."); //매개변수 형 String 생략
		
		p=s->System.out.println(s);
		p.print("Lambda exp four."); // 매개변수 소괄호 () 생략

	}

}
//메소드 몸체가 둘 이상의 문장이나 매개변수의 수가 둘이상인경우에는 생략 불가능!