package src;

import java.util.Random;

public class Quiz5_15 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = (int)(Math.random()*9)+1;
		int b = (int)(Math.random()*9);
		int c = (int)(Math.random()*9);
		
		do {
		if(a==b)  
			a++;
		else if (b==c)
			b++;
		else if (c==a)
			c++;
		}while((a==b) || (b == c) || (a == c));
		
		System.out.println((a*100)+(b*10)+(c*1));
		
		
	}
}

