package Spavanac;

import java.util.*;
public class Spavanac {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int bigHand = s.nextInt();
		int littleHand = s.nextInt();
		if(littleHand <45) {
			if(bigHand<1) {
				bigHand = 23;
			}
			else
				bigHand-=1;
			littleHand = ((littleHand-45)%60)+60;
			System.out.println(bigHand + " " + littleHand);
		}
		else {
			littleHand-=45;
			System.out.println(bigHand + " " + littleHand);
		}
	}
}
