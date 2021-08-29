package DiceGame;

import java.util.*;
public class DiceGame {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int Gunnar = 0;
		int Emma = 0;
		for(int i = 0; i<8; i++) {
			if(i<4)
				Gunnar+= s.nextInt();
			else
				Emma+= s.nextInt();
		}
		if(Gunnar>Emma)
			System.out.println("Gunnar");
		else if(Emma>Gunnar)
			System.out.println("Emma");
		else
			System.out.println("Tie");
	}
}
