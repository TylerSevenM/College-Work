package Kleptography;

//come back to this

import java.util.*;
public class Kleptography {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		char[] alphabet = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p',
				'q','r','s','t','u','v','w','x','y','z'};
		int n = s.nextInt();
		int m = s.nextInt();
		String nStr = s.next();
		String mStr = s.next();
		int shift = (n+m)%26;
		int count = 0;
		boolean find = true;
		char[] charMStr = mStr.toCharArray();
		for(int i = 0; i<charMStr.length; i++) {
			
			
			find = true;
			count = 0;
			while(find) {
				if(alphabet[count] == charMStr[i])
					find = false; 
				count++;
				
			}
			charMStr[i] = alphabet[Math.abs((count+shift+1)%26)];
		}
		String stringer = new String(charMStr);
		System.out.println(stringer);
		
	}
}


