package IsItHalloween;
import java.util.*;
public class IsItHalloweencom {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String spooker = s.nextLine() + "0" ;
		//System.out.println(spooker.length());
		char[] spook = spooker.toCharArray();
		String one = String.valueOf(spook[4]);
		String resolution = spooker;
		String two = String.valueOf(spook[5]);
		resolution = one.concat(two);
		if(spook[0] == 'O' && spook[1] == 'C' && spook[2] == 'T' && Integer.parseInt(resolution)==31)
			System.out.println("yup");
		else if(spook[0] == 'D' && spook[1] == 'E' && spook[2] == 'C' &&Integer.parseInt(resolution)== 25)
			System.out.println("yup");
		else
			System.out.println("nope");
		
		
	}

}
/*
BETTER VERSION TBH, might need tweaking
import java.util.*;
public class IsItHalloweencom {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String spooker = s.nextLine() ;
		//System.out.println(spooker.length());
		char[] spook = spooker.toCharArray();
		String one = String.valueOf(spook[4]);
		String resolution = spooker;
		if(spook.length< 6) {
			if(spook[0] == 'N' && spook[1] == 'O' && spook[2] == 'V' )
				System.out.println("yup");
			else if(spook[0] == 'D' && spook[1] == 'E' && spook[2] == 'C')
				System.out.println("yup");
			else
				System.out.println("nope");
		}
		else {
			String two = String.valueOf(spook[5]);
			resolution = one.concat(two);
			if(spook[0] == 'O' && spook[1] == 'C' && spook[2] == 'T' && Integer.parseInt(resolution)==31 || spook[0] == 'N' && spook[1] == 'O' && spook[2] == 'V' )
				System.out.println("yup");
			else if(spook[0] == 'D' && spook[1] == 'E' && spook[2] == 'C' &&Integer.parseInt(resolution) < 26)
				System.out.println("yup");
			else
				System.out.println("nope");
		}
		
	}

}
*/