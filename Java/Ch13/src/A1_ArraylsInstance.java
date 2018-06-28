class Box {
	private String conts;

	Box(String cont) {
		this.conts = cont;
	}

	public String toString() {
		return conts;
	}
}

public class A1_ArraylsInstance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Box[] ar = new Box[5];
		System.out.println("length :" + ar.length);

	}

}
