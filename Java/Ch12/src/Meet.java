interface Greet{
	void greet();
}
interface Talk{
	void talk();
}
class Morning implements Greet, Talk{
	public void greet() {
		System.out.println("�ȳ��ϼ���!");
	}
	public void talk() {
		System.out.println("���� ���׿�");
	}
}
public class Meet {

	public static void main(String[] args) {
		
		Morning a = new Morning();
		a.greet();
		a.talk();

	}

}
