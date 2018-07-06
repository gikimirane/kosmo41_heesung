class Customer{
	enum Gender{MALE, FEMALE}
	
	private String name;
	private Gender gen;
	
	Customer(String n, String g){
		name = n;
		
		if(g.equals("man"))
			gen = Gender.MALE;
		else
			gen=Gender.FEMALE;
	}
	@Override
	public String toString() {
		if(gen==Gender.MALE)
			return "Thank you, Mr." + name;
		else
			return "Thank you, Mrs." + name;
	}
}
public class A5_InnerEnum {

	public static void main(String[] args) {
		Customer a = new Customer("Brown","man");
		Customer b = new Customer("Sysan Hill","woman");
		
		System.out.println(a);
		System.out.println(b);
		

	}

}
