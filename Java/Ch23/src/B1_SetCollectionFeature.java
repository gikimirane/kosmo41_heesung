import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class B1_SetCollectionFeature {

	public static void main(String[] args) {
		Set<String> set = new HashSet<>();
		set.add("Toy");	set.add("Box");
		set.add("Robot"); set.add("Box");
		System.out.println("인스턴스 수 :" + set.size());
		
		//반복자 이용한 전체 출력
		for(Iterator<String> itr = set.iterator(); itr.hasNext();)
			System.out.print(itr.next()+'\t');
		System.out.println();
		
		for(String s : set)
			System.out.print(s+'\t');
		System.out.println();
		
		//Set<E> 제네릭클래스는 저장순서유지 x 데이터중복 x
	}

}
