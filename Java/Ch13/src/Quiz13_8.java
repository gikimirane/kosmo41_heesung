import java.util.Scanner;

public class Quiz13_8 {

	public static void main(String[] args) {
		int a[][] = new int[4][4];
		int b;
		int sum1 = 0;
		int sum2 = 0;
		int sum3 = 0;
		int sum4 = 0;
		int sum5 = 0;
		int sum6 = 0;
		int sum7 = 0;
		int sum8 = 0;
		Scanner s = new Scanner(System.in);
		System.out.println("�̼����� ����,����,����,���� ������ ���ʴ�� �Է��ϼ���");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 1; j++) {
				b = s.nextInt();
				a[j][i] = b;
				sum1 += b;
			}
		}
		System.out.println("�������� ����,����,����,���� ������ ���ʴ�� �Է��ϼ���");
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < 2; j++) {
				b = s.nextInt();
				a[j][i] = b;
				sum2 += b;
			}
		}

		System.out.println("���������� ����,����,����,���� ������ ���ʴ�� �Է��ϼ���");
		for (int i = 0; i < 4; i++) {
			for (int j = 2; j < 3; j++) {
				b = s.nextInt();
				a[j][i] = b;
				sum3 += b;
			}
		}

		System.out.println("�̼����� ����,����,����,���� ������ ���ʴ�� �Է��ϼ���");
		for (int i = 0; i < 4; i++) {
			for (int j = 3; j < 4; j++) {
				b = s.nextInt();
				a[j][i] = b;
				sum4 += b;
			}
		}
		for(int i=0;i <4 ; i++) {
			for(int j=0;j<4;j++) {
				if(i==0)
					sum5 += a[i][j];
				else if(i==1)
					sum6 += a[i][j];
				else if(i==2)
					sum7 += a[i][j];
				else if(i==3)
					sum8 += a[i][j];
				
			}
		}
		System.out.println("����"+"\t"+"����"+"\t"+"����"+"\t"+"����"+"\t"+"����"+"\t"+"����");
		System.out.println("����"+"\t"+a[0][0]+"\t"+a[0][1]+"\t"+a[0][2]+"\t"+a[0][3]+"\t"+sum5+"\t");
		System.out.println("����"+"\t"+a[1][0]+"\t"+a[1][1]+"\t"+a[1][2]+"\t"+a[1][3]+"\t"+sum6+"\t");
		System.out.println("����"+"\t"+a[2][0]+"\t"+a[2][1]+"\t"+a[2][2]+"\t"+a[2][3]+"\t"+sum7+"\t");
		System.out.println("����"+"\t"+a[3][0]+"\t"+a[3][1]+"\t"+a[3][2]+"\t"+a[3][3]+"\t"+sum8+"\t");
		System.out.println("����"+"\t"+sum1+"\t"+sum2+"\t"+sum3+"\t"+sum4);
	}

}
