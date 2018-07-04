import java.util.Arrays;

class A{
	private int num;
	public A(int num) {
		this.num = num;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this.num == ((A)obj).num)
			return true;
		else
			return false;
	}
}
public class I1_ArrayObjEquals {

	public static void main(String[] args) {
		
		A[] a = new A[3];
		A[] b = new A[3];
		
		a[0] = new A(1); b[0] = new A(1);
		a[1] = new A(2); b[1] = new A(2);
		a[2] = new A(3); b[2] = new A(3);
		System.out.println(Arrays.equals(a, b));

	}

}
