package Sibice;

import java.util.*;
public class Sibice {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int trials = s.nextInt();
		int w = s.nextInt();
		int h = s.nextInt();
		double vol = Math.sqrt(Math.pow(w, 2) + Math.pow(h, 2));
		for(int i = 0; i<trials; i++) {
			int fill= s.nextInt();
			if(fill>vol) {
				System.out.println("NE");
			}
			else
				System.out.println("DA");
		}
	}
}
