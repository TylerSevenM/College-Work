package NastyHacks;

import java.util.Scanner;
public class NastyHacks {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		int[] AR = new int[3];
		int y=0;
		for(int j =0; j<x; j++) {
			for(int i=0; i<3;i++) {
				y = s.nextInt();
				AR[i] = y;
			}
			if (AR[1]-AR[2]>AR[0]) {
				System.out.println("advertise");
			}
			else if (AR[1]-AR[2]==AR[0])
				System.out.println("does not matter");
			else 
				System.out.println("do not advertise");
	}
	}
}
