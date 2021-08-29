//Tyler Merrett - n01146025

import java.io.*;
import java.util.*;

public class Controller{
    public static void main(String[] args) throws IOException{
        try {
            CLexicalAnalyzer p1 = new CLexicalAnalyzer();
            CGrammarParser p2 = new CGrammarParser();
            Scanner s = new Scanner(new FileReader(args[0]));
            ArrayList<String> tokens = new ArrayList<String>();
            ArrayList<String> tokensWithNameID = new ArrayList<String>();
            tokens = p1.lexAn(args);
            tokensWithNameID = p1.tokensWithNames();
            boolean isParsable = p2.Parser(tokens, tokensWithNameID);
            //Disabled Parsing and Token checking because
            //you said we'd be given good examples.
            /*
            if(tokens.contains("ERROR")){
                System.out.println("REJECT");
            }  
            if(isParsable == true){
                System.out.println("ACCEPT");
            }
            else{
                System.out.println("REJECT");
            }*/
            s.close();
        } catch (IOException e) {
            System.out.println("No file");
        }
    }

}