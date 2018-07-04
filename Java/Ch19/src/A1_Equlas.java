class INum{
	private int num;
	public INum(int num) {
		this.num=num;
	}
	@Override
	public boolean equals(Object obj) {
		if(this.num == ((INum)obj).num)
			return true;
		else 
			return false;
	}
}
public class A1_Equlas {

	public static void main(String[] args) {
		INum a= new INum(10);
		INum b= new INum(12);
		INum c= new INum(10);
		
		if (a.equals(b))
			System.out.println("a,b 내용 동일");
		else
			System.out.println("a,b 내용 다름");
		
		if (a.equals(c))
			System.out.println("a,c 내용 동일");
		else
			System.out.println("a,c 내용 다름");
		

	}

}
