
public class A2_MakeThreadDemo {

	public static void main(String[] args) {
		Runnable task = ()->{//�����尡 �����ϰ� �� ����
//			try {
//				Thread.sleep(3000);//3���� �����带 ������.
//			} catch (Exception e) {//�˾Ƶθ� ����
//			}
			int n1 = 10;
			int n2 = 20;
			String name = Thread.currentThread().getName();
			System.out.println(name +":"+(n1+n2));
		};
		
		Thread t = new Thread(task);
		t.start();//������ ���� �� ����
		System.out.println("end "+Thread.currentThread().getName());

	}

}
