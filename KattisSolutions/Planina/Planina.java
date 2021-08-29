package Planina;

/*
 * this program taught me a lesson in focusing on the problem.
 * I focused more on the squares and how to create points off of them rather
 * than just looking at the rate of expansion of the dots and how to achieve
 * the next row which is just a simple (n+(n-1), for a square it's just ^2
 */
import java.util.*;
public class Planina {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		int nextIteration = 2;
		for(int i = 0; i<x; i++) {
			nextIteration += (nextIteration -1); 
		}
		System.out.println(nextIteration*nextIteration);
	}
}
