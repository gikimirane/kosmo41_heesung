public class localVariable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean a = true;
		int b = 11;
		if (a) {
			// int b = 22;
			b++;
			System.out.println(b);
		}
		{
			int c = 33;
			c++;
			System.out.println(c);
		}
		// System.out.println(c);
	}


}
