class Circle1 {
	private double rad = 0;
	final double PI = 3.14;

	public Circle1(double r) {
		setRad(r);
	}

	public void setRad(double r) {
		if (r < 0) {
			rad = 0;
			return;
		}
		rad = r;
	}
	public double getArea() {
		return(rad*rad)*PI;
	}
}

public class B_Infohide {
	public static void main(String args[]) {
	Circle1 c = new Circle1(1.5);
	System.out.println(c.getArea());
	
	c.setRad(2.5);
	System.out.println(c.getArea());
	c.setRad(-3.3);
	System.out.println(c.getArea());
	//c.rad = -5.5;
	System.out.println(c.getArea());
	}
}
