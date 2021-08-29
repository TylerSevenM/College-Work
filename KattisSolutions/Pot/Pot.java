package Pot;

import java.util.*;
public class Pot {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int x = 0;
		int total = 0;
		int size = s.nextInt();
		String[] arr = new String[size];
		for(int i = 0; i<size;i++) {
			x = s.nextInt();
			arr[i] = Integer.toString(x);//
			char[] carr = arr[i].toCharArray();//
			char pc = carr[carr.length-1];//
			int power = Character.getNumericValue(pc);//
			//System.out.println(power);//
			String str = arr[i].substring(0,carr.length-1);
			int y = Integer.parseInt(str);
			total+= Math.pow(y,power);
		}
		System.out.println(total);
	}
}
