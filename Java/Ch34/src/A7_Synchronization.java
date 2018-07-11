class Counter2{
	int count=0;
  public void increment2() {
	  synchronized(this) {
		count++;
	  }
	}
public void decrement2() {
	synchronized(this) {
		count--;
	}
	}
	public int getCount() {return count;}
}
public class A7_Synchronization {

	public static void main(String[] args) {

	}

}
