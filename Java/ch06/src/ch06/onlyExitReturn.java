

public class onlyExitReturn {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		a(4,2);
		a(6,2);
		a(9,0);
		

	}
	public static void a(int b, int c) {
		if(c==0) {
			System.out.println("0으로 나눌 수 없습니다.");
			return;
		}
		System.out.println("나눗셈 결과 : " + (b/c));
		
	}

}
