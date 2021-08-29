package DifferentDistances;

import java.util.*;
public class DifferentDistances {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while(true) {
			double x1 = s.nextDouble();
			if(x1 == 0) {
				break;
			}
			double y1 = s.nextDouble();
			double x2 = s.nextDouble();
			double y2 = s.nextDouble();
			double p  = s.nextDouble();
			double inv= 1/p;
			double x = Math.pow(Math.abs((x1-x2)), p);
			double y = Math.pow(Math.abs((y1-y2)), p);
			double total = Math.pow(x+y, inv);
			System.out.printf("%.10f\n", total);
		}
	}
}
