package Pet;

import java.util.*;
import java.util.stream.*;
public class Pet {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int winnerRow = 0;
		int winnerPoints = 0;
		int rowSum = 0;
		int[] arr = new int[4];
		for(int j = 0; j<5;j++) {
			for(int i = 0; i<4;i++) {
				arr[i] = s.nextInt();
			}
			rowSum = IntStream.of(arr).sum();
			if(winnerPoints<rowSum) {
				winnerPoints = rowSum;
				winnerRow = j+1;
			}
		}
		System.out.println(winnerRow + " " + winnerPoints);
	}
}
