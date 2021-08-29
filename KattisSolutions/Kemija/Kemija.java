package Kemija;

//fails on Kattis but appears to work.
import java.util.*;
public class Kemija {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		char[] ch = str.toCharArray();
		int count = 0;
		String storage = "";
		for(int i = 1; i<ch.length-1; i++) {
			count++;
			char[] chorus = {ch[i-1]};
			if(ch[i-1] == ch[i+1] && (chorus[0] == 'a' || chorus[0] == 'e' || chorus[0] == 'i' || chorus[0] == 'o' || chorus[0] == 'u') && (ch[i] != 'a' || ch[i] != 'e' || ch[i] != 'i' || ch[i] != 'o' || ch[i] != 'u')) {
				char[] chartoString = {ch[i-1], ch[i], ch[i+1]};
				
				String stringer = new String(chartoString);
				String chorusString = new String(chorus);
				str = str.replaceFirst(stringer,chorusString);
				storage = str.replaceFirst(stringer, chorusString);
				i+=2;
			}
				
		}
		System.out.println(storage);
	}
}
