class Box<T>{
	private T ob;
	
	public void set(T o) {
	ob = o;
	}
	
	public T get() {
		return ob;
	}
}
public class PrimitiverAndGeneric {

	public static void main(String[] args) {
		Box<Integer> a = new Box<Integer>();
		a.set(125);//����ڽ�����
		int b = a.get(); //���� ��ڽ� ����
		System.out.println(b);
		
		//Ÿ�����ڷ� �⺻�ڷ����ü�����. int x Integer o
		//����Ŭ������ �ʿ��� ����

	}

}
