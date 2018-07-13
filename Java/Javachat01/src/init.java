import java.util.Scanner;

public class init{
	
	public init() {
		
	}

public void init1() {
	System.out.println("숫자를 입력해 주세요");
	Scanner s = new Scanner(System.in);
	String s_sum = s.nextLine();
	int n_num = Integer.parseInt(s_sum);
	
	try {
		Thread tsub = new ThreadSub1(n_num);
		tsub.start();
	} catch (Exception e) {
		System.out.println("예외:"+e);
	}
	System.out.println("이름을 입력해 주세요");
	String s_name = s.nextLine();

}

public static void main(String[] args) {
	init a = new init();
	a.init1();
}
//////내부클래스
class ThreadSub1 extends Thread{

	int nNum;
	
	public ThreadSub1(int num) {
		this.nNum = num;
	}
	
	//run()메소드 재정의
	@Override
	public void run() {
		int i=0;
		while(i<nNum) {
			try {
				Thread.sleep(1000);
				i=i+1;
				System.out.println("Thread:"+i);
			} catch (Exception e) {
				System.out.println("예외:"+e);
			}
		}
	}
}
}