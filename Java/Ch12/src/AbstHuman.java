abstract class Hu {
	abstract void print();
}

class Man extends Hu{
	String str;
	
	Man(String str){
		this.str = str;
	}
	public void print() {
		System.out.println(str+"持失1");
		System.out.println(str+"持失2");
	}
}
class Woman extends Hu{
	String str;
	
	Woman(String str){
		this.str=str;
	}
	public void print() {
		System.out.println(str+"持失1");
		System.out.println(str+"持失2");
		System.out.println(str+"持失3");
	}
	
}
class HumanFactory{
	public static Hu asd(String str) {
		if(str=="害切") {
			return new Man(str);
		}
		else if (str=="食切") {
			return new Woman(str);
			
		}
		return null;
	}
}
public class AbstHuman {

	public static void main(String[] args) {
		Hu a= HumanFactory.asd("害切");
		a.print();
		
		Hu b= HumanFactory.asd("食切");
		b.print();
		

	}

}
