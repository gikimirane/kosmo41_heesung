package src;

class Random {
	void Randoms() {
		for(;;) {
	int a = (int) (Math.random()*898)+102;
	int b = a / 100;
	int c = (a - (b * 100)) / 10;
	int d = (a - (b * 100)) - (c * 10);
	if ((b != c) && (c != d) && (d != b))
		System.out.println(a);
	break;
		}
	}
}
public class Quiz5_14 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a, z;
		int sum;
		for (a = 1; a < 10; a++) {
			for (z = 1; z < 10; z++) {
				if (a == z)
					continue;
				sum = (a * 10 + z) + (z * 10 + a);
				if (sum == 99)
					System.out.println((a * 10 + z) + "+" + (z * 10 + a) + "=" + sum);

			}
			

		}

	}

}
