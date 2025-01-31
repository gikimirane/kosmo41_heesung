package sad;

class Point implements Cloneable {
	public int xPos;
	public int yPos;

	public Point(int x, int y) {
		xPos = x;
		yPos = y;
	}

	public void showPosition() {
		System.out.printf("[%d, %d]", xPos, yPos);
		System.out.println();
	}

	public void changePos(int x, int y) {
		xPos = x;
		yPos = y;
	}
}

class Rectangle implements Cloneable {
	public Point upLeft;
	public Point lowRight;

	public Rectangle(int x1, int y1, int x2, int y2) {
		upLeft = new Point(x1, y1);
		lowRight = new Point(x2, y2);
	}

	public void changePos(int x1, int y1, int x2, int y2) {
		upLeft.changePos(x1, y1);
		lowRight.changePos(x2, y2);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public void showPosition() {
		System.out.println("좌측 상단 : ");
		upLeft.showPosition();

		System.out.println("우측 하단 : ");
		lowRight.showPosition();
		System.out.println();
	}

}

public class asdf {

	public static void main(String[] args) {
		Rectangle org = new Rectangle(1, 1, 9, 9);
		Rectangle cpy;

		try {
			cpy = (Rectangle) org.clone();// 인스턴스복사
			org.changePos(2, 2, 7, 7);// 한 인스턴스의 좌표 정보 수정
			org.showPosition();
			cpy.showPosition();

			if (org.equals(cpy))
				System.out.println("aaaaaa");
			else
				System.out.println("bbbbb");

			if (org.lowRight.equals(cpy.lowRight))
				System.out.println("cccccc");
			else
				System.out.println("dddddd");
		}

		catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

	}

}
