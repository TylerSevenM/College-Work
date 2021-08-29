package ColdputerScience;

import java.util.*;
public class ColdputerScience {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		int count = 0;
		for (int i = 0; i<x; i++) {
			int y = s.nextInt();
			if (y<0) 
				count++;
		}
		System.out.println(count);
	}
}