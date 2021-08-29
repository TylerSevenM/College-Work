package Bela;

import java.util.*;
public class Bela {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		char[] cardValue = {'A', 'K', 'Q', 'J', 'T', '9', '8', '7'};
		int[] dom = {11,4,3,20,10,14,0,0};
		int[] nondom = {11,4,3,2,10,0,0,0};
		int domain = s.nextInt()*4;
		char dominant = s.next().charAt(0);
		int result = 0;
		for(int i = 0; i<domain;i++) {
			String str = s.next();
			int index = 0;
			for(int j = 0; j<cardValue.length; j++) {
				if(cardValue[j] == str.charAt(0))
					index = j;
			}
			if(str.charAt(1) == dominant)
				result+= dom[index];
			else
				result+= nondom[index];
		}
		System.out.println(result);
	}
}
