//Tyler Merrett - N01146025

import java.io.*;
import java.util.*;

public class Controller{
    public static void main(String[] args) throws IOException{
        try {
            CLexicalAnalyzer p1 = new CLexicalAnalyzer();
            CGrammarParser p2 = new CGrammarParser();
            Scanner s = new Scanner(new FileReader(args[0]));
            ArrayList<String> Tokens = new ArrayList<String>();
            Tokens = p1.lexAn(args);
            boolean isParsable = p2.Parser(Tokens);
            if(Tokens.contains("ERROR")){
                System.out.println("REJECT");
            }
            else{
                if(isParsable == true){
                    System.out.println("ACCEPT");
                }else{System.out.println("REJECT");}
            }
            s.close();
        } catch (IOException e) {
            System.out.println("No file");
        }
    }

}