//Tyler Merrett - N01146025

import java.util.*;

public class CGrammarParser{

    static ArrayList<String> tokens = new ArrayList<String>();
    static ArrayList<String> tokenID = new ArrayList<String>();
    static ArrayList<String> pr = new ArrayList<String>();
    static ArrayList<String> test = new ArrayList<String>();
    static ArrayList<String> testBUP = new ArrayList<String>();
    //answer is essentially the constant "return if false" checker
    //it ensures the grammar, if correct, remains correct or fails if wrong
    static boolean answer = true;
    static boolean flagger = false;
    static int i;
    static int parenCount = 0;
    static int bracketCount = 0;
    static int braceCount = 0;
    //track _t# starting at zero
    static int count = -1;
    static String storageA = "";
    static String storageB = "";
    static String storageC = "";
    static String storageD = "";
    static int backpatchCounter = 0;
    static int lineJumpCounter = 0;

    public static boolean Parser(ArrayList<String> tokenList, ArrayList<String> tokenListWithID){
        //Count 'i' start at zero for token array
        i = 0;
        //make token array the global ArrayList so it can be freely parsed
        //by the methods with a simple count increment method
        tokens = tokenList;
        tokenID = tokenListWithID;
        try {
            if (tokens.size() > 2) {
                A();
            }else{answer = false;}
        } catch (IndexOutOfBoundsException e) {}
        if(i>=tokens.size() && answer == true && parenCount == 0 && bracketCount == 0 && braceCount == 0){
            //System.out.println(tokenListWithID);
            projectPrinter();
            return true;
        }else{return false;}
    }
    // I used the grammar as dictated in the book; however, I changed the names 
    // of each of the values. It's quite the looker :) . Grammar in README.txt

    public static void A(){
        if(answer == true){
            if(tokens.get(i).equals("t")){
                i++;
                if(tokens.get(i).equals("i")){
                    i++;
                    E();
                }else{answer = false;}
            }
            else if(tokens.get(i).equals("v")){
                i++;
                if(tokens.get(i).equals("i")){
                    i++;
                    E();
                }else{answer = false;}
            }else{answer = false;}
        }else{answer = false;}
    }
    public static void B(){
        if(answer == true){
            if(tokens.get(i).equals("t")){
                i++;
                if(tokens.get(i).equals("i")){
                    i++;
                    E();
                }else{answer = false;}
            }else if(tokens.get(i).equals("v")){
                i++;
                if(tokens.get(i).equals("i")){
                    i++;
                    E();
                }else{answer = false;}
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void E(){
        if(answer == true){
            if(tokens.get(i).equals(";")){
                i-=2;
                if(tokens.get(i) == "i"){
                    pr.add("alloc");
                    pr.add("4");
                    pr.add("");
                    pr.add(tokenID.get(i));}
                else{answer = false;}
                i+=2;
                i++;
                B();
            }
            else if(tokens.get(i).equals("[")){
                i-=2;
                pr.add("alloc");
                pr.add(String.valueOf(Integer.parseInt(tokenID.get(i+3))*4));
                i++;
                pr.add("");
                pr.add(tokenID.get(i));
                i++;
                i++;
                bracketCount++;
                if(tokens.get(i).equals("n")){
                    i++;
                    if(tokens.get(i).equals("]")){
                        i++;
                        bracketCount--;
                        if(tokens.get(i).equals(";")){
                            i++;
                            B();
                        }else{answer = false;}
                    }else{answer = false;}
                }else{answer = false;}
            }
            else if(tokens.get(i).equals("(")){
                i--;
                pr.add("func ");
                pr.add(tokenID.get(i));
                if(tokens.get(i--) == "i"){
                    pr.add("int"); pr.add("1");}
                else{pr.add("void"); pr.add("0");}
                i++;
                i++;
                i++;
                parenCount++;
                G();
                if(tokens.get(i).equals(")")){
                    i++;
                    parenCount--;
                    if(tokens.get(i).equals("{")){
                        i++;
                        braceCount++;
                        K();L();
                        if(tokens.get(i).equals("}")){
                            braceCount--;
                            i++;
                            B();
                        }else{answer = false;}
                    }else{answer = false;}
                }else{answer = false;}
            }else{answer = false;}
        }else{answer = false;}
    }
    public static void G(){
        if(answer == true){
            if(tokens.get(i).equals("t")){
                i++;
                if(tokens.get(i).equals("i")){
                    pr.add("param");
                    pr.add("");
                    pr.add("");
                    pr.add(tokenID.get(i));
                    pr.add("alloc");
                    pr.add("4");
                    pr.add("");
                    pr.add(tokenID.get(i));
                    i++;
                    I();
                    H();
                }else{answer = false;}
            }
            else if(tokens.get(i).equals("v")){
                i++;
                S();
            }else{answer = false;}
        }else{answer = false;}
    }
    public static void S(){
        if(answer == true){
            if(tokens.get(i).equals("i")){
                i++;
                I();H();
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void H(){
        if(answer == true){
            if(tokens.get(i).equals(",")){
                i++;
                F();
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void F(){
        if(answer == true){
            if(tokens.get(i).equals("t")){
                i++;
                if(tokens.get(i).equals("i")){
                    i++;
                    I();H();
                }else{answer = false;}
            }
            else if(tokens.get(i).equals("v")){
                i++;
                if(tokens.get(i).equals("i")){
                    i++;
                    I();H();
                }else{answer = false;}
            }else{answer = false;}
        }else{answer = false;}
    }
    public static void I(){
        if(answer == true){
            if(tokens.get(i).equals("[")){
                i-=2;
                pr.add("alloc");
                pr.add(String.valueOf(Integer.parseInt(tokenID.get(i+3))*4));
                i++;
                pr.add("");
                pr.add(tokenID.get(i));
                i++;
                i++;
                bracketCount++;
                if(tokens.get(i).equals("]")){
                    i++;
                    bracketCount--;
                }else{answer = false;}
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void K(){
        if(answer == true){
            if(tokens.get(i).equals("t")){
                i++;
                if(tokens.get(i).equals("i")){
                    i++;
                    D();K();
                }else{answer = false;}
            }
            else if(tokens.get(i).equals("v")){
                i++;
                if(tokens.get(i).equals("i")){
                    i++;
                    D();K();
                }else{answer = false;}
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void D(){
        if(answer == true){
            if(tokens.get(i).equals(";")){
                i-=2;
                    pr.add("alloc");
                    pr.add("4");
                    i++;
                    pr.add("");
                    pr.add(tokenID.get(i));
                i++;
                i++;
            }
            else if(tokens.get(i).equals("[")){
                i-=2;
                pr.add("alloc");
                pr.add(String.valueOf(Integer.parseInt(tokenID.get(i+3))*4));
                i++;
                pr.add("");
                pr.add(tokenID.get(i));
                i++;
                i++;
                bracketCount++;
                if(tokens.get(i).equals("n")){
                    i++;
                    if(tokens.get(i).equals("]")){
                        i++;
                        bracketCount--;
                        if(tokens.get(i).equals(";")){
                            i++;
                        }else{answer = false;}
                    }else{answer = false;}
                }else{answer = false;}
            }else{answer = false;}
        }else{answer = false;}
    }
    public static void L(){
        if(answer == true){
            if((tokens.get(i).equals(";")||tokens.get(i).equals("{")||
            tokens.get(i).equals("f")||tokens.get(i).equals("w")||
            tokens.get(i).equals("r")||tokens.get(i).equals("i")||
            tokens.get(i).equals("(")||tokens.get(i).equals("n"))){
                //large false look ahead example
                M();
                L();
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void M(){
        if(answer == true){
            if((tokens.get(i).equals("(")||tokens.get(i).equals("i")||
            tokens.get(i).equals("n"))){
                //false look ahead example 
                R();
                if(tokens.get(i).equals(";")){
                    //Testing
                    if(test.get(test.size()-1).equals("*") || test.get(test.size()-1).equals("/") || 
                    test.get(test.size()-1).equals("+") || test.get(test.size()-1).equals("-")){
                        test.add(tokenID.get(i-1));
                    }
                    operatorHandler();









                    //Testing end
                    i++;
                }else{answer = false;}
            }
            else if(tokens.get(i).equals(";")){
                //Testing
                if(test.get(test.size()-1).equals("*") || test.get(test.size()-1).equals("/") || 
                    test.get(test.size()-1).equals("+") || test.get(test.size()-1).equals("-")){
                        test.add(tokenID.get(i-1));
                    }
                operatorHandler();









                //Testing end
                i++;
            }
            else if(tokens.get(i).equals("{")){
                i++;
                braceCount++;
                K();L();
                if(tokens.get(i).equals("}")){
                    braceCount--;
                    i++;
                }else{answer = false;}
            }
            else if(tokens.get(i).equals("f")){
                i++;
                if(tokens.get(i).equals("(")){
                    i++;
                    parenCount++;
                    R();
                    if(tokens.get(i).equals(")")){
                        i++;
                        parenCount--;
                        M();O();
                    }else{answer = false;}
                }else{answer = false;}
            }
            else if(tokens.get(i).equals("w")){
                i++;
                if(tokens.get(i).equals("(")){
                    i++;
                    parenCount++;
                    R();
                    if(tokens.get(i).equals(")")){
                        i++;
                        parenCount--;
                        M();
                    }else{answer = false;}
                }else{answer = false;}
            }
            else if(tokens.get(i).equals("r")){
                i++;
                Q();
            }else{answer = false;}
        }else{answer = false;}
    }
    public static void O(){
        if(answer == true){
            if(tokens.get(i).equals("e")){
                i++;
                M();
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void Q(){
        if(answer == true){
            if(tokens.get(i).equals(";")){
                //Testing
                if(test.get(test.size()-1).equals("*") || test.get(test.size()-1).equals("/") || 
                    test.get(test.size()-1).equals("+") || test.get(test.size()-1).equals("-")){
                        test.add(tokenID.get(i-1));
                    }
                operatorHandler();










                //Testing end
                i++;
            }
            else if((tokens.get(i).equals("(")||tokens.get(i).equals("i")||
            tokens.get(i).equals("n"))){
                //false look ahead example
                R();
                if(tokens.get(i).equals(";")){
                    //Testing
                    if(test.get(test.size()-1).equals("*") || test.get(test.size()-1).equals("/") || 
                    test.get(test.size()-1).equals("+") || test.get(test.size()-1).equals("-")){
                        test.add(tokenID.get(i-1));
                    }
                    operatorHandler();










                    //Testing end
                    i++;
                }else{answer = false;}
            }else{answer = false;}
        }else{answer = false;}
    }
    public static void R(){
        if(answer == true){
            if(tokens.get(i).equals("i")){
                i++;
                N();
            }
            else if(tokens.get(i).equals("(")){
                test.add(tokenID.get(i));
                i++;
                parenCount++;
                R();
                if(tokens.get(i).equals(")")){
                    test.add(tokenID.get(i-1));
                    test.add(tokenID.get(i));
                    i++;
                    parenCount--;
                    X();V();T();
                }else{answer = false;}
            }
            else if(tokens.get(i).equals("n")){
                i++;
                X();V();T();
            }       
        }else{answer = false;}
    }
    public static void N(){
        if(answer == true){
            if(tokens.get(i).equals("=")){
                storageB = tokenID.get(i-1);
                i++;
                R();
            }
            else if(tokens.get(i).equals("[")){
                i++;
                bracketCount++;
                R();
                if(tokens.get(i).equals("]")){
                    i++;
                    bracketCount--;
                    P();
                }else{answer = false;}
            }
            else if((tokens.get(i).equals("(") && tokens.get(i+1).equals(")"))){
                i+=2;
                X();V();T();
            }
            else if(tokens.get(i).equals("(")){
                test.add(tokenID.get(i));
                i++;
                parenCount++;
                R();C();
                if(tokens.get(i).equals(")")){
                    test.add(tokenID.get(i-1));
                    test.add(tokenID.get(i));
                    i++;
                    parenCount--;
                    X();V();T();
                }else{answer = false;}
            }else{X();V();T();}
        }else{answer = false;}
    }
    public static void P(){
        if(answer == true){
            if(tokens.get(i).equals("=")){
                storageB = tokenID.get(i-1);
                i++;
                R();
            }else{
                X();V();T();
            }
        }else{answer = false;}
    }
    public static void T(){
        if(answer == true){
            if(tokens.get(i).equals("<=")){
                //Testing
                test.add(tokenID.get(i-1));
                test.add(tokenID.get(i));
                try{
                    //System.out.println("inplace" + pr.get(pr.size()-1));
                    if(pr.get(pr.size()-1).contains("_")){
                        storageC = pr.get(pr.size()-1);
                    }
                    else{
                        storageC = tokenID.get(i-1);
                    }
                }catch(Exception e){}
                

                //Testing end
                i++;
                Z();X();V();

                try{
                    if(pr.get(pr.size()-1).contains("_")){
                        storageD = pr.get(pr.size()-1);
                    }
                    else{
                        storageD = tokenID.get(i-1);
                    }
                }catch(Exception e){}
                pr.add("comp ");
                pr.add(storageC);
                pr.add(storageD);
                pr.add("bktk" + String.valueOf(backpatchCounter));
                pr.add("BRGT ");
                pr.add("bktk" + String.valueOf(backpatchCounter));
                pr.add("");
                pr.add("jump" + String.valueOf(lineJumpCounter));
                backpatchCounter++;
                lineJumpCounter++;
            }
            else if(tokens.get(i).equals("<")){
                //Testing
                test.add(tokenID.get(i-1));
                test.add(tokenID.get(i));
                try{
                    if(pr.get(pr.size()-1).contains("_")){
                        storageC = pr.get(pr.size()-1);
                    }
                    else{
                        storageC = tokenID.get(i-1);
                    }
                }catch(Exception e){}
                

                //Testing end
                i++;
                Z();X();V();

                try{
                    if(pr.get(pr.size()-1).contains("_")){
                        storageD = pr.get(pr.size()-1);
                    }
                    else{
                        storageD = tokenID.get(i-1);
                    }
                }catch(Exception e){}
                pr.add("comp ");
                pr.add(storageC);
                pr.add(storageD);
                pr.add("bktk" + String.valueOf(backpatchCounter));
                pr.add("BRGEQ");
                pr.add("bktk" + String.valueOf(backpatchCounter));
                pr.add("");
                pr.add("jump" + String.valueOf(lineJumpCounter));
                backpatchCounter++;
                lineJumpCounter++;
            }
            else if(tokens.get(i).equals(">")){
                //Testing
                test.add(tokenID.get(i-1));
                test.add(tokenID.get(i));
                try{
                    if(pr.get(pr.size()-1).contains("_")){
                        storageC = pr.get(pr.size()-1);
                    }
                    else{
                        storageC = tokenID.get(i-1);
                    }
                }catch(Exception e){}
                

                //Testing end
                i++;
                Z();X();V();

                try{
                    if(pr.get(pr.size()-1).contains("_")){
                        storageD = pr.get(pr.size()-1);
                    }
                    else{
                        storageD = tokenID.get(i-1);
                    }
                }catch(Exception e){}
                pr.add("comp ");
                pr.add(storageC);
                pr.add(storageD);
                pr.add("bktk" + String.valueOf(backpatchCounter));
                pr.add("BRLEQ");
                pr.add("bktk" + String.valueOf(backpatchCounter));
                pr.add("");
                pr.add("jump" + String.valueOf(lineJumpCounter));
                backpatchCounter++;
                lineJumpCounter++;
            }
            else if(tokens.get(i).equals(">=")){
                //Testing
                test.add(tokenID.get(i-1));
                test.add(tokenID.get(i));
                try{
                    if(pr.get(pr.size()-1).contains("_")){
                        storageC = pr.get(pr.size()-1);
                    }
                    else{
                        storageC = tokenID.get(i-1);
                    }
                }catch(Exception e){}
                

                //Testing end
                i++;
                Z();X();V();

                try{
                    if(pr.get(pr.size()-1).contains("_")){
                        storageD = pr.get(pr.size()-1);
                    }
                    else{
                        storageD = tokenID.get(i-1);
                    }
                }catch(Exception e){}
                pr.add("comp ");
                pr.add(storageC);
                pr.add(storageD);
                pr.add("bktk" + String.valueOf(backpatchCounter));
                pr.add("BRLT ");
                pr.add("bktk" + String.valueOf(backpatchCounter));
                pr.add("");
                pr.add("jump" + String.valueOf(lineJumpCounter));
                backpatchCounter++;
                lineJumpCounter++;
            }
            else if(tokens.get(i).equals("==")){
                //Testing
                test.add(tokenID.get(i-1));
                test.add(tokenID.get(i));
                try{
                    if(pr.get(pr.size()-1).contains("_")){
                        storageC = pr.get(pr.size()-1);
                    }
                    else{
                        storageC = tokenID.get(i-1);
                    }
                }catch(Exception e){}
                

                //Testing end
                i++;
                Z();X();V();

                try{
                    if(pr.get(pr.size()-1).contains("_")){
                        storageD = pr.get(pr.size()-1);
                    }
                    else{
                        storageD = tokenID.get(i-1);
                    }
                }catch(Exception e){}
                pr.add("comp ");
                pr.add(storageC);
                pr.add(storageD);
                pr.add("bktk" + String.valueOf(backpatchCounter));
                pr.add("BREQ ");
                pr.add("bktk" + String.valueOf(backpatchCounter));
                pr.add("");
                pr.add("jump" + String.valueOf(lineJumpCounter));
                backpatchCounter++;
                lineJumpCounter++;
            }
            else if(tokens.get(i).equals("!=")){
                //Testing
                test.add(tokenID.get(i-1));
                test.add(tokenID.get(i));
                try{
                    if(pr.get(pr.size()-1).contains("_")){
                        storageC = pr.get(pr.size()-1);
                    }
                    else{
                        storageC = tokenID.get(i-1);
                    }
                }catch(Exception e){}
                

                //Testing end
                i++;
                Z();X();V();

                try{
                    if(pr.get(pr.size()-1).contains("_")){
                        storageD = pr.get(pr.size()-1);
                    }
                    else{
                        storageD = tokenID.get(i-1);
                    }
                }catch(Exception e){}
                pr.add("comp ");
                pr.add(storageC);
                pr.add(storageD);
                pr.add("bktk" + String.valueOf(backpatchCounter));
                pr.add("BRNEQ");
                pr.add("bktk" + String.valueOf(backpatchCounter));
                pr.add("");
                pr.add("jump" + String.valueOf(lineJumpCounter));
                backpatchCounter++;
                lineJumpCounter++;
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void V(){
        if(answer == true){
            if(tokens.get(i).equals("+")){
                //Testing
                try{
                    if(!tokens.get(i-1).equals(")")){
                        test.add(tokenID.get(i-1));
                    }
                    test.add(tokenID.get(i));
                }catch(Exception e){}


                //Testing end
                i++;
                Z();X();V();
            }
            else if(tokens.get(i).equals("-")){
                //Testing
                try{
                    if(!tokens.get(i-1).equals(")")){
                        test.add(tokenID.get(i-1));
                    }
                    test.add(tokenID.get(i));
                }catch(Exception e){}


                //Testing end
                i++;
                Z();X();V();
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void X(){
        if(answer == true){
            if(tokens.get(i).equals("*")){
                //Testing
                try{
                    if(!tokens.get(i-1).equals(")")){
                        test.add(tokenID.get(i-1));
                    }
                    test.add(tokenID.get(i));
                }catch(Exception e){}
                


                //Testing end
                i++;
                Z();X();
            }
            else if(tokens.get(i).equals("/")){
                //Testing
                try{
                    if(!tokens.get(i-1).equals(")")){
                        test.add(tokenID.get(i-1));
                    }
                    test.add(tokenID.get(i));
                }catch(Exception e){}
                

                //Testing end
                i++;
                Z();X();
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void Z(){
        if(answer == true){
            if(tokens.get(i).equals("(")){
                test.add(tokenID.get(i));
                i++;
                parenCount++;
                R();
                if(tokens.get(i).equals(")")){
                    test.add(tokenID.get(i-1));
                    test.add(tokenID.get(i));
                    i++;
                    parenCount--;
                }else{answer = false;}
            }
            else if(tokens.get(i).equals("i")){
                i++;
                J();
            }
            else if(tokens.get(i).equals("n")){
                i++;
            }else{answer = false;}
        }else{answer = false;}
    }
    public static void J(){
        if(answer == true){
            if(tokens.get(i).equals("[")){
                i++;
                bracketCount++;
                R();
                if(tokens.get(i).equals("]")){
                    i++;
                    bracketCount--;
                }else{answer = false;}
            }
            else if((tokens.get(i).equals("(") && tokens.get(i+1).equals(")"))){
                i+=2;
            }
            else if(tokens.get(i).equals("(")){
                test.add(tokenID.get(i));
                i++;
                parenCount++;
                R();C();
                if(tokens.get(i).equals(")")){
                    test.add(tokenID.get(i-1));
                    test.add(tokenID.get(i));
                    i++;
                    parenCount--;
                }else{answer = false;}
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void C(){
        if(answer == true){
            if(tokens.get(i).equals(",")){
                i++;
                R();C();
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void projectPrinter(){
        boolean printAssistFlag = true;
        for(int j = 0; j<pr.size(); j++){
            if(printAssistFlag == true){
                System.out.println("\n");
                printAssistFlag = false;
            }
            if(j%4 == 0){
                System.out.println("\n");
                if(j/4<10){
                    System.out.print("0" + j/4 + " ");
                }
                else{System.out.print(j/4 + " ");}
            }
            System.out.print(pr.get(j) + "\t");
        }
        //System.out.println();
        //System.out.println();
        //System.out.println(test);
    }
    //whenever I hit a symbol I want to check prior value - just add that to the thing

    public static void operatorHandler(){
        //move this
        testBUP = test;
        ArrayList<String> recurSend = new ArrayList<String>();
        int dynamicTestSize = test.size();
        int start = 0;
        int end = 0;
        int cycles = 0;
        //System.out.println(test);
        try {
            for(int x = 0; x<testBUP.size(); x++){
                if(test.get(x).equals("(")){
                    cycles++;
                }
            }
            for(int z = 0; z<=cycles; z++){
                if(test.contains("(")){
                    for(int k = test.size()-1; k>-1; k--){
                        if(test.get(k).equals("(")){
                            start = k;
                            //System.out.pricdntln(k);
                            test.remove(k);
                            break;
                        }
                    }
                    for(int k = start; k<test.size(); k++){
                        if(test.get(k).equals(")")){
                            end = k;
                            //System.out.println(k);
                            //System.out.println("Removing: " + test.get(k));
                            test.remove(k);
                            break;
                        }
                    }
        
                    for(int k = start; k<end; k++){
                        recurSend.add(test.get(k));
                    }
                    for(int w = start; w<end; w++){
                        test.remove(start);
                        
                    }
                    expHandPrint(recurSend, start);
                    //System.out.println(test);
                }
                else{
                    recurSend = test;
                    expHandPrint(recurSend, start);
                }
                
            }
            //System.out.println("fail"  +test);
            if(test.size()>0){
                for(int a = 0; a<test.size(); a++){
                    test.remove(a);
                }
            }
            if(!storageB.equals("")){
                storageA = pr.get(pr.size()-1);
                pr.add("assig");
                pr.add(storageA);
                pr.add("");
                pr.add(storageB);
                storageA = "";
                storageB = "";
            }
            
        } catch (Exception e) {}
        
        
    }

    public static void expHandPrint(ArrayList<String> recursive, int insert){
        int dynamicTestSize = recursive.size();
        for(int k = 0; k<dynamicTestSize; k++){
            if(recursive.get(k).equals("*")){
                pr.add("mult ");
                pr.add(recursive.get(k-1));
                pr.add(recursive.get(k+1));
                count++;
                pr.add("_t" + count);
                k-=1;
                recursive.remove(k+2);
                recursive.remove(k+1);
                recursive.remove(k);
                dynamicTestSize-=2;
                recursive.add(k, "_t" + String.valueOf(count));
                //System.out.println(recursive);
            }
            if(recursive.get(k).equals("/")){
                pr.add("divis");
                pr.add(recursive.get(k-1));
                pr.add(recursive.get(k+1));
                count++;
                pr.add("_t" + count);
                k-=1;
                recursive.remove(k+2);
                recursive.remove(k+1);
                recursive.remove(k);
                dynamicTestSize-=2;
                recursive.add(k, "_t" + String.valueOf(count));
                //System.out.println(recursive);
            }

        }

        for(int k = 0; k<dynamicTestSize; k++){
            if(recursive.get(k).equals("+")){
                pr.add("addit");
                pr.add(recursive.get(k-1));
                pr.add(recursive.get(k+1));
                count++;
                pr.add("_t" + count);
                k-=1;
                recursive.remove(k+2);
                recursive.remove(k+1);
                recursive.remove(k);
                dynamicTestSize-=2;
                recursive.add(k, "_t" + String.valueOf(count));
                //System.out.println(recursive);
            }
            if(recursive.get(k).equals("-")){
                pr.add("subtr");
                pr.add(recursive.get(k-1));
                pr.add(recursive.get(k+1));
                count++;
                pr.add("_t" + count);
                k-=1;
                recursive.remove(k+2);
                recursive.remove(k+1);
                recursive.remove(k);
                dynamicTestSize-=2;
                recursive.add(k, "_t" + String.valueOf(count));
                //System.out.println(recursive);
            }

        }
        if(recursive.size()>0){            
            test.add(insert, recursive.get(0));
            recursive.remove(0);
            //operatorHandler();
        }
        /*
        if(test.size()>0){
            for(int z = 0; z<test.size(); z++){
                test.remove(z);
            }
        }*/
        
       
    }

}