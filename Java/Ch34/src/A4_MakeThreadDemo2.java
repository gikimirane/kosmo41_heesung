class Task extends Thread{
	public void run() {//쓰레드의 run 메소드 오버라이딩
		int n1=10;
		int n2=20;
		String name = Thread.currentThread().getName();
		System.out.println(name+":"+(n1+n2));
	}
}
public class A4_MakeThreadDemo2 {

	public static void main(String[] args) {
		Task t1 = new Task();
		Task t2 = new Task();
		t1.start();
		t2.start();
		System.out.println("end"+Thread.currentThread().getName());

	}

}
