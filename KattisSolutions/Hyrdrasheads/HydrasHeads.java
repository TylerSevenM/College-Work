package Hyrdrasheads;
//UNFINISHED
//UNFINISHED
//UNFINISHED
//UNFINISHED
//UNFINISHED
import java.util.Scanner;

public class HydrasHeads {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int head = s.nextInt();
		int tail = s.nextInt();
		boolean cando = true;
		int count = 0;
		//second move: -1tail; +2tail;
		// third move: -2tail; +1head;
		//fourth move: -2head; +0rest;
		if(tail == 0) {
			count = -1;
			cando = false;
		}
		while(head !=0 && tail!= 0 && cando==true) {
			if(head<0)
				head=0;
			if(tail<0)
				tail=0;
			if(head==0 && tail==0) {
				cando =false;
			}
			
			
			
			
		System.out.println(count);
	}

}
