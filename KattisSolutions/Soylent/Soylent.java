package Soylent;

import java.util.Scanner;
public class Soylent {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int tests = s.nextInt();
		for(int i = 0; i<tests; i++) {
			int x = s.nextInt();
			System.out.println((int) Math.ceil(x/400.0));
		}
	}
}
