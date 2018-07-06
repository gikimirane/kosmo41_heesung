interface ViewableA{
	public void showIt(String str);
}
class Viewer1 implements ViewableA {

	@Override
		public void showIt(String str) {
			System.out.println(str);
		}
	}

public class ViewerA{
	public static void main(String[] args) {
		ViewableA a = new Viewer1();
		a.showIt("Hello Annotations");

	}

}
