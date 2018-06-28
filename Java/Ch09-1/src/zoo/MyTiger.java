package zoo;

class Tiger{
	//빈클래스 - default 선언으로 동일 패키지 내에서만 생성가능
}
//MyTiger는 퍼블릭으로 선언되서 어디서든 생성 가능 ㅇㅋ 다른패키지도 ㅇㅋ
public class MyTiger {
		public void makeTiger1() {
			//Tigre와 같은 패키지로 묶여있으니 생성 가능
			Tiger hodol = new Tiger();
		}

}
