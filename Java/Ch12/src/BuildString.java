
public class BuildString {

	public static void main(String[] args) {
		StringBuilder stbuf = new StringBuilder("123");
		
		stbuf.append(45678);//���ڿ� ������
		System.out.println(stbuf.toString());
		
		stbuf.delete(0, 2);//���ڿ� �Ϻ� ����
		System.out.println(stbuf.toString());
		
		stbuf.replace(0, 3, "AB");//���ڿ� 0~3�ڸ��� "ab�� ��ü
		System.out.println(stbuf.toString());
		
		stbuf.reverse();//���ڿ� ���� ������
		System.out.println(stbuf.toString());
		
		String sub = stbuf.substring(2, 4);
		System.out.println(sub);
	}

}
