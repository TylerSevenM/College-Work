package Tarifa;

import java.util.*;
public class Tarifa {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int monthly = s.nextInt();
		int monthsOfUse = s.nextInt();
		int payItForward = 0;
		for(int i = 0; i<monthsOfUse;i++) {
			int temp = s.nextInt();
			payItForward+= (monthly-temp);
		}
		payItForward+= monthly;
		System.out.println(payItForward);
	}
}
