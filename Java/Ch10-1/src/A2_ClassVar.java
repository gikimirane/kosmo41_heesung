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
	//	way.num++;// �ܺο��� �̸��� �������� (���� ����� x)
		AccessWay.num++; //�ܺο��� �̸��� �������� (���� ����ϴ¹��)
		System.out.println("num = "+AccessWay.num);

	}

}
