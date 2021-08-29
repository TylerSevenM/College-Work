package Apaxiaaaaaaaaaaaans;

import java.util.*;
public class Apaxiaaaaaaaaaaaans {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String apaxia = s.nextLine() + " ";
		char[] z = apaxia.toCharArray();
		String removeDuplicates = "";
		for(int i = 0; i<z.length-1; i++) {
			if(z[i] != z[i+1])
				removeDuplicates+= z[i];
		}
		System.out.println(removeDuplicates);
	}
}
