package SevenWonders;

import java.util.*;
public class SevenWonders {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		char[] yes = str.toCharArray();
		int c = 0;
		int t = 0;
		int g = 0;
		int total = 0;
		for(int i = 0; i<yes.length; i++) {
			if(yes[i] == 'C')
				c++;
			if(yes[i] == 'G')
				g++;
			if(yes[i] == 'T')
				t++;
		}
		int x = 0;
		if(g>c) 
			x = Math.min(t, c);
		else
			x = Math.min(g, t);
		total+= x*7;
		total+= Math.pow(c, 2);
		total+= Math.pow(g, 2);
		total+= Math.pow(t, 2);
		System.out.println(total);
	}
}
