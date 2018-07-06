import java.util.HashMap;

public class E1_HashMapCollection {

	public static void main(String[] args) {
		HashMap<Integer, String> map = new HashMap<>();
		
		map.put(45, "Brown");
		map.put(37, "james");
		map.put(23, "martin");
		
		System.out.println("23 : "+map.get(23));
		System.out.println("37 : "+map.get(37));
		System.out.println("45 : "+map.get(45));
		System.out.println();
		//Delete
		map.remove(37);
		
		System.out.println(map.get(37));

	}

}
