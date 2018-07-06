import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class A3_F_CopyList {

	public static void main(String[] args) {
		List<String> a = Arrays.asList("Box","Apple","Toy","Robot");
		
		//���纻�� ���� ���
		List<String> d = new ArrayList<>(a);
		
		//�����Ͽ� �� ��� ���
		Collections.sort(d);
		System.out.println(d);
		
		//������ ���� ������ ���·� �ǵ�������
		Collections.copy(d, a);
		
		//�ǵɸ����¤���Ȯ��	
		System.out.println(d);

	}

}
