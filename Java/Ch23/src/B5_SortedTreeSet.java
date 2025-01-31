import java.util.Iterator;
import java.util.TreeSet;

public class B5_SortedTreeSet {

	public static void main(String[] args) {
		TreeSet<Integer> tree = new TreeSet<Integer>();
		tree.add(3);
		tree.add(1);
		tree.add(4);
		tree.add(2);
		
		for(Integer n : tree)
			System.out.print(n.toString()+'\t');
		System.out.println();
		
		for(Iterator<Integer> itr = tree.iterator(); itr.hasNext();)
			System.out.print(itr.next().toString()+'\t');
		System.out.println();
	}

}
