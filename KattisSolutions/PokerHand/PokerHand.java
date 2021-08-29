package PokerHand;
/*

 
 
 * fix me pls :(



import java.util.*;
public class PokerHand {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		char[] charStr = str.toCharArray();
		int temp = 1;
		int count = 1;
		for(int i = 0; i<charStr.length; i+=3) {
			char value = charStr[i];
			temp = 1;
			for (int j = 3; j<charStr.length; j+=3) {
				char value2 = charStr[j];
				if(value == value2) {
					temp++;
					
					if(temp>count)
						count = temp;
				}
			}
		}
		System.out.println(count);
	}
}
*/
import java.util.*;
public class PokerHand {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		char[] charStr = str.toCharArray();
		int[] arr = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0};
		for(int i = 0; i<charStr.length; i+=3) {
			char value = charStr[i];
			if(value == 'A') {
				arr[0]++;
			}
			if(value == '2') {
				arr[1]++;
			}
			if(value == '3') {
				arr[2]++;
			}
			if(value == '4') {
				arr[3]++;
			}
			if(value == '5') {
				arr[4]++;
			}
			if(value == '6') {
				arr[5]++;
			}
			if(value == '7') {
				arr[6]++;
			}
			if(value == '8') {
				arr[7]++;
			}
			if(value == '9') {
				arr[8]++;
			}
			if(value == 'T') {
				arr[9]++;
			}
			if(value == 'J') {
				arr[10]++;
			}
			if(value == 'Q') {
				arr[11]++;
			}
			if(value == 'K') {
				arr[12]++;
			}
		}
		Arrays.sort(arr);
		int x = arr[arr.length-1];
		System.out.println(x);
	}
}