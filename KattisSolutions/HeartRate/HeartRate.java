package HeartRate;

import java.util.*;
public class HeartRate {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		for(int i=0; i<n;i++) {
			int b = s.nextInt();
			float p = s.nextFloat();
			float a = (60/(p/(b-1)));
			float y = (60*b) /p;
			float c = (60/(p/(b+1)));
			System.out.printf("%.4f" + " ",a);
			System.out.printf("%.4f" + " ", y);
			System.out.printf("%.4f \n", c);
		}
	}

}
