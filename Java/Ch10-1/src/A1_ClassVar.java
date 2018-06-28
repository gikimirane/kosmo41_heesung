class InstCnt {
	static int instNum = 0;
	// int instNum = 0;

	public InstCnt() {
		instNum++;
		System.out.println("인스턴스 생성 : " + instNum);
	}
}

public class A1_ClassVar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InstCnt a = new InstCnt();
		InstCnt b = new InstCnt();
		InstCnt c = new InstCnt();

	}

}
