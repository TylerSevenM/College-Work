package Beavergnaw;

import java.util.*;
public class Beavergnaw {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		double x = (double)s.nextDouble();
		double y = (double) s.nextDouble();
		double volume = (double) x*x*Math.PI;
		System.out.println(volume);
		double total = (double) volume - y;
		System.out.println(total);
		total = (double) Math.sqrt(total);
		System.out.println((double) total);
	}

}
