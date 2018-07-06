import java.util.Scanner;

public class OMG {

	public static void main(String[] args) {
		String x[][] = { { "1", "2", "3" }, { "4", "5", "6" }, { "7", "8", "9" } };
		Scanner s = new Scanner(System.in);
		String sc;
		int g=0;
		int t=0;
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x.length; j++) {
				if(j==2) {
					System.out.print(" "+x[i][j]);
					continue;
				}
				System.out.print(" "+x[i][j] +" |");

			}
			System.out.println("");
			System.out.println("---"+"|"+"---"+"|"+"---");
		}
		do {
			int b = 0;
			int c =0;
			int e =0;
	
			System.out.println("Player 1, please enter the number of the square");
			System.out.println("where you want to place your X:");
			sc = s.nextLine();
			for(int i=0; i<x.length; i++) {
				for(int j=0;j <x[i].length; j++) {
					if(x[i][j].equals(sc)) {
						x[i][j] = "x";
					}
				}
			}
			for (int i = 0; i < x.length; i++) {
				for (int j = 0; j < x.length; j++) {
					if(j==2) {
						System.out.print(" "+x[i][j]);
						continue;
					}
					System.out.print(" "+x[i][j] +" |");
				}
				System.out.println("");
				System.out.println("---"+"|"+"---"+"|"+"---");
			}
			for (int i = 0; i < x.length; i++) {
				for (int j = 0; j < x.length; j++) {
					if(x[i][j]=="x")
						b++;
					if((x[i][j]=="x")&& (i==j)) {
						e++;
					}
				}
				if(b==3) {
					System.out.println("Player1 win");
					return;
				}
				if(b!=3)
					b=0;
				if((x[i][0]=="x")||(x[i][1]=="x")||(x[i][2]=="x")) 
					c++;
				
				
			}
			if(c==3) {
				System.out.println("Player1 win");
				break;
			}
			if(c!=3)
				c=0;
			if(e==3) {
				System.out.println("Player1 win");
				break;
			}
			if(e!=3)
				e=0;
			if((x[0][2]==x[1][1])&&(x[1][1]==x[2][0])) {
				System.out.println("Player1 win");
				break;
			}		
			
			
			System.out.println("Player 2, please enter the number of the square");
			System.out.println("where you want to place your O:");
			sc = s.nextLine();
			for(int i=0; i<x.length; i++) {
				for(int j=0;j <x.length; j++) {
					if(x[i][j].equals(sc)) {
						x[i][j] = "o";
					}
				}
			}
			for (int i = 0; i < x.length; i++) {
				for (int j = 0; j < x.length; j++) {
					if(j==2) {
						System.out.print(" "+x[i][j]);
						continue;
					}
					System.out.print(" "+x[i][j] +" |");
				}
				System.out.println("");
				System.out.println("---"+"|"+"---"+"|"+"---");
			}
			for (int i = 0; i < x.length; i++) {
				for (int j = 0; j < x.length; j++) {
					if(x[i][j]=="o")
						b++;
					if((x[i][j]=="o")&& (i==j)) {
						e++;
					}
				}
				if(b==3) {
					System.out.println("Player2 win");
					return;
				}
				if(b!=3)
					b=0;
				if((x[i][0]=="o")||(x[i][1]=="o")||(x[i][2]=="o")) 
					c++;
			}
			if(c==3) {
				System.out.println("Player2 win");
				break;
			}
			if(c!=3)
				c=0;
			if(e==3) {
				System.out.println("Player2 win");
				break;
			}
			if(e!=3)
				e=0;
			if((x[0][2]==x[1][1])&&(x[1][1]==x[2][0])) {
				System.out.println("Player2 win");
				break;
			}
			

		} while (true);

	}
	}


