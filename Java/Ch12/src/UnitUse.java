abstract class Unit{
	abstract void attack();
}
class Marine extends Unit{
	public void attack() {
		System.out.println("������ ����");
		System.out.println("���ݷ� 10���� ����");
	}
}
class Zealot extends Unit{
	public void attack() {
		System.out.println("������ ����");
		System.out.println("���ݷ� 8�� ����");
	}
}
class Zergling extends Unit{
	public void attack() {
		System.out.println("�����ΰ���");
		System.out.println("���ݷ� 9�� ����");
	}
}
public class UnitUse {

	public static void main(String[] args) {
		Unit a=new Marine();
		a.attack();
		Unit b=new Zealot();
		b.attack();
		Unit c=new Zergling();
		c.attack();

	}

}
