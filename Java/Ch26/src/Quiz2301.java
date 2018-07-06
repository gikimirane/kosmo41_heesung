import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Quiz2301 {

	public static void main(String[] args) {
		List list1 = new ArrayList();
		List list2 = new ArrayList();
		List kyo = new ArrayList();
		List cha = new ArrayList();
		List hap = new ArrayList();
		
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(4);
		
		list2.add(3);
		list2.add(4);
		list2.add(5);
		list2.add(6);
		
		kyo.addAll(list1);//다더함
		kyo.retainAll(list2);//겹치는것만출력
		
		cha.addAll(list1);
		cha.removeAll(list2);
		
		hap.addAll(list1);
		hap.removeAll(list2);
		hap.addAll(list2);
		
		
		
		
		System.out.println("list1="+list1);
		System.out.println("list2="+list2);
		System.out.println("kyo="+kyo);
		System.out.println("cha="+cha);
		System.out.println("hap="+hap);
		

	}

}
