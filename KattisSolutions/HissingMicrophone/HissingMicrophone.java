package HissingMicrophone;

import java.util.*;
public class HissingMicrophone {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		char[] z = str.toCharArray();
		boolean tracker = false;
		for(int i = 0; i<z.length-1; i++) {
			if(z[i] == 's' && z[i+1] == 's') {
				tracker = true;
				break;
			}
		}
		if(tracker ==true)
			System.out.println("hiss");
		else 
			System.out.println("no hiss");
	}
}
