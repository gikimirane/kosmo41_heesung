class Person{
	String name;
	public Person (String name) {
		this.name = name;
	}
	@Override
	protected void finalize() throws Throwable{
		super.finalize();
		System.out.println("destroyed:"+name);
	}
}
public class A1_ObjectFinalize {

	public static void main(String[] args) {
		Person a=new Person("Yoon");
		Person b=new Person("Park");
		a=null;
		b=null;
		
		System.gc();
		//System.runFinalization();
		
		System.out.println("end of program");

	}

}
