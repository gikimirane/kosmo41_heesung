abstract class Unit{
	abstract void attack();
}
class Marine extends Unit{
	public void attack() {
		System.out.println("총으로 공격");
		System.out.println("공격력 10으로 떄림");
	}
}
class Zealot extends Unit{
	public void attack() {
		System.out.println("손으로 공격");
		System.out.println("공격력 8로 공격");
	}
}
class Zergling extends Unit{
	public void attack() {
		System.out.println("입으로공격");
		System.out.println("공격력 9로 공격");
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
