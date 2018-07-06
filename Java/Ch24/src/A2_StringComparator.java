import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Strcomp implements Comparator<String>{
	@Override
	public int compare(String s1, String s2) {
		return s1.compareToIgnoreCase(s2);
	}
}
public class A2_StringComparator {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("ROBOT");
		list.add("APPLE");
		list.add("BOX");
		
		Strcomp cmp = new Strcomp();//정렬과 탐색 기준
		Collections.sort(list, cmp);//정렬
		int a = Collections.binarySearch(list,"Robot", cmp);//탐색
		System.out.println(list.get(a));

	}

}
