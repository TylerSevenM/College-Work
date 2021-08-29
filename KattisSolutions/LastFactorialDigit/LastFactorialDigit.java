package LastFactorialDigit;

import java.util.*;
public class LastFactorialDigit {

	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		int  T  = s.nextInt();
		for(int i =0; i<T;i++) {
			int x = s.nextInt();
			int y = factorial(x);
			String[] nums = Integer.toString(y).split("");
			if (y<11) 
				System.out.println(y);
			else
				System.out.println(nums[nums.length-1]);
		}
		
	}
	public static int factorial(int x) {
		if (x==0)
			return 1;
		else
			return (x*factorial(x-1));
	}

}
