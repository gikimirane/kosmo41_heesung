import java.util.LinkedList;
import java.util.Queue;

public class D1_LinkedListQurue {

	public static void main(String[] args) {
		Queue<String> que = new LinkedList<>();
		que.offer("Box");
		que.offer("Toy");
		que.offer("Robot");
		//무엇이 다음에 나올지 확인	
		System.out.println("next: "+que.peek());
		
		//첫ㅎ번쨰 , 두번쨰 인스턴스 꺼내기
		System.out.println(que.poll());
		System.out.println(que.poll());
		
		//무엇이 다음에 나올지 확인
		System.out.println(que.peek());
		
		System.out.println(que.poll());

	}

}
