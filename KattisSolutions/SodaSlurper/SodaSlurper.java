package SodaSlurper;

import java.util.*;
public class SodaSlurper {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		int y = s.nextInt();
		int z = s.nextInt();
		int bottles = x+y;
		int count = 0;
		while(bottles>=z) {
			bottles-=z;
			count++;
			bottles++;
		}
		System.out.println(count);
	}
}
