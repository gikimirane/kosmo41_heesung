interface Printable{
	void print(String s);//�Ű����� �ϳ�, ��ȯ�� void
}
public class A2_OneParamNoreturn {

	public static void main(String[] args) {
		Printable p;
		p=(String s) ->{System.out.println(s);};
		p.print("Lambda exp one.");//���Ӿ��� ǥ��
		
		p=(String s) -> System.out.println(s);
		p.print("Lambda exp two");//�߰�ȣ {} ����
		
		p=(s) -> System.out.println(s);
		p.print("Lambda exp three."); //�Ű����� �� String ����
		
		p=s->System.out.println(s);
		p.print("Lambda exp four."); // �Ű����� �Ұ�ȣ () ����

	}

}
//�޼ҵ� ��ü�� �� �̻��� �����̳� �Ű������� ���� ���̻��ΰ�쿡�� ���� �Ұ���!