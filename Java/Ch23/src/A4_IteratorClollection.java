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
		
		//�ݺ��� ȹ��
		Iterator<String> itr = list.iterator();
		
		//�ݺ��ڸ� �̿��� ������ ������
		while(itr.hasNext())
			System.out.print(itr.next()+'\t');
		System.out.println();
		
		//�ݺ��� �ٽ� ȹ��
		itr = list.iterator();
		
		String str;
		while(itr.hasNext()) {
			str=itr.next();
			
			if(str.equals("Box"))
				itr.remove();
		}
		
		//�ݺ��� �ٽ� ȹ��
		itr = list.iterator();
		
		while(itr.hasNext())
			System.out.print(itr.next()+'\t');
		System.out.println();
		

	}

}
