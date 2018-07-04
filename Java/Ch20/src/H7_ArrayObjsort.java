import java.util.Arrays;

class Person1 implements Comparable{
	private String name;
	private int age;
	
	public Person1(String name, int age) {
		this.name=name;
		this.age=age;
	}
	
	@Override
	public int compareTo(Object o) {
		Person1 p = (Person1)o;
		int nu = this.name.compareTo(p.name);
		return nu;
/*		if(this.age > p.age)
			return 1;
		else if (this.age<p.age)
			return -1;
		else
			return 0;*/
	}
	
	public String toString() {
		return name+":"+age;
	}
}
public class H7_ArrayObjsort {

	public static void main(String[] args) {
		Person1[] a = new Person1[3];
		
		a[0] = new Person1("Goo",29);
		a[1] = new Person1("Soo",15);
		a[2] = new Person1("Lee",37);
		
		Arrays.sort(a);
		for(Person1 p : a)
			System.out.println(p);
		

	}

}
