import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class A4_IteratorClollection {

	public static void main(String[] args) {
		
		List<String> list = new LinkedList<>();
		
		list.add("Toy");
		list.add("Box");
		list.add("Robot");
		list.add("Box");
		
		//반복자 획득
		Iterator<String> itr = list.iterator();
		
		//반복자를 이용한 순차적 ㅈ참조
		while(itr.hasNext())
			System.out.print(itr.next()+'\t');
		System.out.println();
		
		//반복자 다시 획득
		itr = list.iterator();
		
		String str;
		while(itr.hasNext()) {
			str=itr.next();
			
			if(str.equals("Box"))
				itr.remove();
		}
		
		//반복자 다시 획득
		itr = list.iterator();
		
		while(itr.hasNext())
			System.out.print(itr.next()+'\t');
		System.out.println();
		

	}

}
