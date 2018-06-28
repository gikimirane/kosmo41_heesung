class AccessWay{
	static int num = 0;
	
	AccessWay(){incrCnt();}
	void incrCnt() {
		num++;
	}
}
public class A2_ClassVar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccessWay way=new AccessWay();
	//	way.num++;// 외부에서 이름을 통한접근 (보통 사용방법 x)
		AccessWay.num++; //외부에서 이름을 통한접근 (보통 사용하는방법)
		System.out.println("num = "+AccessWay.num);

	}

}
