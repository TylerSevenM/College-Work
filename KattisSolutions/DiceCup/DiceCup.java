package DiceCup;

import java.util.*;
public class DiceCup {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		int y = s.nextInt();
		int k = 0;
		int max = x+y;
		double a = (double) x;
		double b = (double) y;
		double res;
		double[] z = new double[max-1];
		for(int i = 0; i<x; i++) {
			for(int j = 0; j<y; j++) {
				res = (1/a) + (1/b);
				z[i+j]+= res;
			}
		}
			while(z[k]<z[k+1]) {
				k++;
			}
			int c = max-k-1;	
			while(k<c) {
				System.out.println(k+2);
				k++;
			}
	}
}
