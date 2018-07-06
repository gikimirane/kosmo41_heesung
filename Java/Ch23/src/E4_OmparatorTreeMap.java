import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

class AgeComparator implements Comparator<Integer>{
	public int compare(Integer n1, Integer n2) {
		return n2.intValue() - n1.intValue();
	}
}

public class E4_OmparatorTreeMap {

	public static void main(String[] args) {
		TreeMap<Integer, String> map = new TreeMap<>(new AgeComparator());
		
		map.put(45, "Brown");
		map.put(37, "james");
		map.put(23, "martin");
		
		//key만 담고있는 컬렉션 인스턴스 생성
		Set<Integer> ks = map.keySet();
		
		//전체 키 출력
		for(Integer n : ks)
			System.out.print(n.toString()+'\t');
		System.out.println();
		
		//value 출력
		for(Integer n : ks)
			System.out.print(map.get(n).toString()+'\t');
		System.out.println();
		
		for(Iterator<Integer> itr=ks.iterator(); itr.hasNext();)
			System.out.print(map.get(itr.next())+'\t');
		System.out.println();

	}
}
