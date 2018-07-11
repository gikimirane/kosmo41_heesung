class Counter{
	int count=0; //공유되는 변수
	public void increment() {
		count++; //첫번째 쓰레드에 의해 실행
	}
	
	public void decrement() {
		count--; //또다른 쓰레드에 의해 실행
	}
	
	public int getCount() {return count;}
}
public class A5_MutualAccess {
	public static Counter2 cnt = new Counter2();
	public static void main(String[] args) throws InterruptedException{
		Runnable task1 = ()->{
			for(int i=0;i<1000;i++)
				cnt.increment2();
		};
		
		Runnable task2 = ()->{
			for(int i=0;i<1000;i++)
				cnt.decrement2();
		};
		Thread t1 = new Thread(task1);
		Thread t2 = new Thread(task2);
		
		t1.start();
		t2.start();
		
		t1.join();//t1이 참조하는 쓰레드의 종료를 기다림
		t2.join();
		
		//쓰레드가 종료되면 출력을 진행함. 위 join의 영향
		System.out.println(cnt.getCount());

	}

}
