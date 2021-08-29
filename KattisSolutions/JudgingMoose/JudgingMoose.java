package JudgingMoose;

import java.util.*;
public class JudgingMoose {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		int y = s.nextInt();
		int points = 0;
		String bean = "";
		if((x!=0 || y!=0) && (x<21 && y<21)) {
			if(x!=y) {
				if(x>y) {
					points = x*2;
				}
				else {
					points  = y*2;
				}
				bean = "Odd";
			}
			else {
				points = 2*x;
				bean = "Even";
			}
			System.out.println(bean +" " + points);
		}
		else
			System.out.println("Not a moose");
	}
}