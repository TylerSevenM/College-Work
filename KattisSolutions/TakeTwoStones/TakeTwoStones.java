package TakeTwoStones;

import java.util.*;
public class TakeTwoStones {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		if(x%2 == 0)
			System.out.println("Bob");
		else
			System.out.println("Alice");
	}
}
