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
		System.out.println("이순신의 국어,영어,수학,국사 점수를 차례대로 입력하세요");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 1; j++) {
				b = s.nextInt();
				a[j][i] = b;
				sum1 += b;
			}
		}
		System.out.println("강감찬의 국어,영어,수학,국사 점수를 차례대로 입력하세요");
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < 2; j++) {
				b = s.nextInt();
				a[j][i] = b;
				sum2 += b;
			}
		}

		System.out.println("을지문덕의 국어,영어,수학,국사 점수를 차례대로 입력하세요");
		for (int i = 0; i < 4; i++) {
			for (int j = 2; j < 3; j++) {
				b = s.nextInt();
				a[j][i] = b;
				sum3 += b;
			}
		}

		System.out.println("이순신의 국어,영어,수학,국사 점수를 차례대로 입력하세요");
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
		System.out.println("구분"+"\t"+"순신"+"\t"+"감찬"+"\t"+"문덕"+"\t"+"권율"+"\t"+"총점");
		System.out.println("국어"+"\t"+a[0][0]+"\t"+a[0][1]+"\t"+a[0][2]+"\t"+a[0][3]+"\t"+sum5+"\t");
		System.out.println("영어"+"\t"+a[1][0]+"\t"+a[1][1]+"\t"+a[1][2]+"\t"+a[1][3]+"\t"+sum6+"\t");
		System.out.println("수학"+"\t"+a[2][0]+"\t"+a[2][1]+"\t"+a[2][2]+"\t"+a[2][3]+"\t"+sum7+"\t");
		System.out.println("국사"+"\t"+a[3][0]+"\t"+a[3][1]+"\t"+a[3][2]+"\t"+a[3][3]+"\t"+sum8+"\t");
		System.out.println("총점"+"\t"+sum1+"\t"+sum2+"\t"+sum3+"\t"+sum4);
	}

}
