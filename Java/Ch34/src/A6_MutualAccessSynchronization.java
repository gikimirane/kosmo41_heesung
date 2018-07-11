
class Counter1{
	int count=0;
	synchronized public void increment1() {
		count++;
	}
	synchronized public void decrement1() {
		count--;
	}
	public int getCount() {return count;}
}
public class A6_MutualAccessSynchronization {

	public static void main(String[] args) {

	}

}
