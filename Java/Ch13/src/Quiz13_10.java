
public class Quiz13_10 {

	public static void main(String[] args) {
		int a[] = {210,19,72,129,34};
		int j;
		int e,i;
		System.out.println("���� �� ������ ǥ��");
		for(int g:a) {
			System.out.print(g+"  ");
		}
		System.out.println("");
		System.out.println("���� �� ������ ǥ��");
		for ( i = 0; i < a.length; i++) {

			for (j = i + 1; j <a.length; j++) {
				if(a[i]>a[j]) {
					e= a[i];
					a[i]=a[j];
					a[j]=e;
				}
			}
		}
		for(int f:a) {
			System.out.print(f+"  ");
		}
	}
}

