package AlphabetSpam;

import java.util.*;
public class AlphabetSpam {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		char[] strs = str.toCharArray();
		int spaces = 0;
		int lowercase = 0;
		int uppercase = 0;
		int inverse = 0;
		for(int i = 0; i<strs.length;i++) {
			if (Character.isWhitespace(strs[i]) || strs[i] == '_') {
				spaces++;
			}
			if (Character.isUpperCase(strs[i]))
				uppercase++;
			if (Character.isLowerCase(strs[i]))
				lowercase++;
			if (Character.isLetter(strs[i])) 
				inverse++;
		}
		int symbols = strs.length-inverse - spaces;
		double spaceRatio = (double) spaces/strs.length;
		double lcRatio = (double) lowercase/strs.length;
		double ucRatio = (double) uppercase/strs.length;
		double symbolRatio = (double) symbols/strs.length;
		System.out.printf("%.16f\n",spaceRatio);
		System.out.printf("%.16f\n",lcRatio);
		System.out.printf("%.16f\n",ucRatio);
		System.out.printf("%.16f",symbolRatio);
	}
}
