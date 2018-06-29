abstract class Hu {
	abstract void print();
}

class Man extends Hu{
	String str;
	
	Man(String str){
		this.str = str;
	}
	public void print() {
		System.out.println(str+"����1");
		System.out.println(str+"����2");
	}
}
class Woman extends Hu{
	String str;
	
	Woman(String str){
		this.str=str;
	}
	public void print() {
		System.out.println(str+"����1");
		System.out.println(str+"����2");
		System.out.println(str+"����3");
	}
	
}
class HumanFactory{
	public static Hu asd(String str) {
		if(str=="����") {
			return new Man(str);
		}
		else if (str=="����") {
			return new Woman(str);
			
		}
		return null;
	}
}
public class AbstHuman {

	public static void main(String[] args) {
		Hu a= HumanFactory.asd("����");
		a.print();
		
		Hu b= HumanFactory.asd("����");
		b.print();
		

	}

}
