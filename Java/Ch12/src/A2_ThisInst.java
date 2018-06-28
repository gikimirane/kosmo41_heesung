class SimplBox{
	private int data;
	
	SimplBox(int data){
		this.data = data;
	}
	void setData(int data) {
		this.data =data;
	}
	
	int getData() {
		return this.data;
	}
}
public class A2_ThisInst {

	public static void main(String[] args) {
		SimplBox box = new SimplBox(99);
		System.out.println(box.getData());
		
		box.setData(88);
		System.out.println(box.getData());

	}

}
