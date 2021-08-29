package Aaah;

import java.util.*;
public class Aaah {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String yell = s.nextLine();
		char[] split = yell.toCharArray();
		int countA = 0;
		for(int i = 0; i<split.length; i++ ) {
			if(split[i] == 'a')
				countA++;
		}
		String check = s.nextLine();
		char[] splitTwo = check.toCharArray();
		int countB = 0;
		for(int i = 0; i<splitTwo.length; i++ ) {
			if(splitTwo[i] == 'a')
				countB++;
		}
		if(countB<=countA) 
			System.out.println("go");
		else 
			System.out.println("no");
	}
}
