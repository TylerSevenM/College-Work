package RoamingRomans;

import java.util.*;
public class RoamingRomans {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		double x = s.nextDouble();
		double y = (double) x*(5280.0/4854.0)*1000.0 + 0.5;
		System.out.println((int) y);
	}
}
