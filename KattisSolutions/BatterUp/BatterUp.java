package BatterUp;

import java.util.*;
public class BatterUp {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int atBat = s.nextInt();
		int count = 0;
		double valueTown = 0;
		for(int i = 0; i<atBat; i++) {
			int temp = s.nextInt();
			if (temp != -1) {
				count++;
				valueTown+= temp;
			}
		}
			System.out.println((double) valueTown/count);
	}
}
