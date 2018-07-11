
public class A2_MakeThreadDemo {

	public static void main(String[] args) {
		Runnable task = ()->{//쓰레드가 실행하게 할 내용
//			try {
//				Thread.sleep(3000);//3초후 쓰레드를 실행함.
//			} catch (Exception e) {//알아두면 유용
//			}
			int n1 = 10;
			int n2 = 20;
			String name = Thread.currentThread().getName();
			System.out.println(name +":"+(n1+n2));
		};
		
		Thread t = new Thread(task);
		t.start();//쓰레드 생성 및 실행
		System.out.println("end "+Thread.currentThread().getName());

	}

}
