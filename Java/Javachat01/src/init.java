import java.util.Scanner;

public class init{
	
	public init() {
		
	}

public void init1() {
	System.out.println("���ڸ� �Է��� �ּ���");
	Scanner s = new Scanner(System.in);
	String s_sum = s.nextLine();
	int n_num = Integer.parseInt(s_sum);
	
	try {
		Thread tsub = new ThreadSub1(n_num);
		tsub.start();
	} catch (Exception e) {
		System.out.println("����:"+e);
	}
	System.out.println("�̸��� �Է��� �ּ���");
	String s_name = s.nextLine();

}

public static void main(String[] args) {
	init a = new init();
	a.init1();
}
//////����Ŭ����
class ThreadSub1 extends Thread{

	int nNum;
	
	public ThreadSub1(int num) {
		this.nNum = num;
	}
	
	//run()�޼ҵ� ������
	@Override
	public void run() {
		int i=0;
		while(i<nNum) {
			try {
				Thread.sleep(1000);
				i=i+1;
				System.out.println("Thread:"+i);
			} catch (Exception e) {
				System.out.println("����:"+e);
			}
		}
	}
}
}