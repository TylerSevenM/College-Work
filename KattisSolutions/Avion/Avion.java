package Avion;
import java.util.*;

public class Avion {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		char[] chars;
		int count = 0;
		int correctness = 0;
		String code;
		while (count<5) {
			count++;
			code= s.nextLine();
			chars = code.toCharArray();
			for (int i = 0; i<chars.length-2; i++) {
				if(chars[i]==('F') &&chars[i+1]==('B') && chars[i+2]==('I')) {
					correctness++;
					System.out.println(count);
				}
				else {
					System.out.println();
				}
				
			}
			
		}
		if(correctness<1) {
			System.out.println("HE GOT AWAY!");
		}
	}

}
