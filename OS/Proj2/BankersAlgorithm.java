//Tyler Merrett - N01146025
//Banker's Algorithm implementation with resource allocation call
import java.util.*;

public class BankersAlgorithm{

    static int p;
    static int r;
    // reminder for me: Matrix [#rows][#columns] so, [p][r]
    static int alloc[][];
    static int max[][];
    static int need[][];
    static int avail[];
    static boolean SAFE = false;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        //matrix building information
        System.out.println("How many processes?");
        p = s.nextInt();
        System.out.println("How many resources?");
        r = s.nextInt();
        alloc = new int[p][r];
        max = new int[p][r];
        need = new int[p][r];
        avail = new int[r];
        // now we have to fill in the data for alloc max and avail - need will need to be calc'd
        System.out.println("Enter Allocation's Matrix: ");
        for(int i = 0; i<p ; i++){
            for(int j = 0; j<r; j++){
                alloc[i][j] = s.nextInt();
            }
        }
        System.out.println("Enter Maximum's Matrix: ");
        for(int i = 0; i<p ; i++){
            for(int j = 0; j<r; j++){
                max[i][j] = s.nextInt();
            }
        }
        System.out.println("Enter Available's Matrix: ");
        for(int i = 0; i<r ; i++){
                avail[i] = s.nextInt();
        }
        fillNeed();
        checkSafety();
        if(SAFE == true){
            System.out.println("Safely allocated!");
        }
        else{
            System.out.println("Failed to allocate safely.");
        }
        s.close();
    }
    public static void fillNeed() {
        for(int i = 0; i<p ; i++){
            for(int j = 0; j<r; j++){
                //uses need calc rule max-alloc
                need[i][j] = max[i][j] - alloc[i][j];
            }
        }
    }

    //safety check to ensure we can even do the math
    public static Boolean availLessThanNeed(int x) {
        for(int i = 0; i<r ; i++){
            if(avail[i]<need[x][i]){
                //false return is a good thing
                return false;
            }
        }
        return true; 
    }

    //driving function
    public static void checkSafety() {
        boolean[] progress = new boolean[p];
        int i = 0;
        for(i = 0; i<p; i++){
            boolean done = false;
            for(int j = 0; j<p; j++){
                if(progress[j] == false && availLessThanNeed(j)){
                    for(int k = 0; k<r; k++){
                        //new avail uses old avail rather than manipulating another matrix
                        avail[k] = avail[k] - need[j][k] + max[j][k];
                    }
                done = true;
                progress[j] = true;
                i++;}}
            if(done == false){
                break;}
            //final result
            if(i==p){
                SAFE = true;}
            else{SAFE = false;}
        }
    }
}