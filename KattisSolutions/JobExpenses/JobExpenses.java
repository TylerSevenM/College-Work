package JobExpenses;

import java.util.*;
public class JobExpenses {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int trials = s.nextInt();
		int temp = 0;
		int sum = 0;
		for(int i = 0; i<trials;i++) {
			temp = s.nextInt();
			if(temp<0)
				sum+=temp;
		}
		System.out.println(sum*-1);
	}
}