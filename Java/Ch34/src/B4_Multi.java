import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Counter4{
	int count =0;
	ReentrantLock criticObj = new ReentrantLock();
	
	public void increment4(){
		criticObj.lock();
		
		try {
			count++;
		} catch (Exception e) {
			criticObj.unlock();
		}
	}
	
	public void decrement4() {
		criticObj.lock();
		try {
			count--;
		} catch (Exception e) {
			criticObj.unlock();
		}
	}
	public int getCount() {return count;}
}
public class B4_Multi {
	public static Counter4 cnt = new Counter4();

	public static void main(String[] args) throws InterruptedException{
		Runnable task1 = ()->{
			for(int i=0; i<1000;i++)
				cnt.increment4();
		};
		Runnable task2 = ()->{
			for(int i=0;i<1000;i++)
				cnt.decrement4();
		};
		ExecutorService exr = Executors.newFixedThreadPool(2);
		exr.submit(task1);
		exr.submit(task2);
		exr.shutdown();
		exr.awaitTermination(100, TimeUnit.SECONDS);
		System.out.println(cnt.getCount());

	}

}
