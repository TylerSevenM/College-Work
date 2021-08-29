//Tyler Merrett - N01146025

import java.util.*;

public class CGrammarParser{

    static ArrayList<String> tokens = new ArrayList<String>();
    //answer is essentially the constant "return if false" checker
    //it ensures the grammar, if correct, remains correct or fails if wrong
    static boolean answer = true;
    static int i;
    static int parenCount = 0;
    static int bracketCount = 0;
    static int braceCount = 0;

    public static boolean Parser(ArrayList<String> tokenList){
        //Count 'i' start at zero for token array
        i = 0;
        //make token array the global ArrayList so it can be freely parsed
        //by the methods with a simple count increment method
        tokens = tokenList;
        try {
            if (tokens.size() > 2) {
                A();
            }else{answer = false;}
        } catch (IndexOutOfBoundsException e) {}
        System.out.println(answer);
        System.out.println(parenCount);
        System.out.println(braceCount);
        System.out.println(bracketCount);
        if(i>=tokens.size() && answer == true && parenCount == 0 && bracketCount == 0 && braceCount == 0){
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
                i++;
                B();
            }
            else if(tokens.get(i).equals("[")){
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
                i++;
            }
            else if(tokens.get(i).equals("[")){
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
                    i++;
                }else{answer = false;}
            }
            else if(tokens.get(i).equals(";")){
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
                i++;
            }
            else if((tokens.get(i).equals("(")||tokens.get(i).equals("i")||
            tokens.get(i).equals("n"))){
                //false look ahead example
                R();
                if(tokens.get(i).equals(";")){
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
                i++;
                parenCount++;
                R();
                if(tokens.get(i).equals(")")){
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
                i++;
                parenCount++;
                R();C();
                if(tokens.get(i).equals(")")){
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
                i++;
                Z();X();V();
            }
            else if(tokens.get(i).equals("<")){
                i++;
                Z();X();V();
            }
            else if(tokens.get(i).equals(">")){
                i++;
                Z();X();V();
            }
            else if(tokens.get(i).equals(">=")){
                i++;
                Z();X();V();
            }
            else if(tokens.get(i).equals("==")){
                i++;
                Z();X();V();
            }
            else if(tokens.get(i).equals("!=")){
                i++;
                Z();X();V();
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void V(){
        if(answer == true){
            if(tokens.get(i).equals("+")){
                i++;
                Z();X();V();
            }
            else if(tokens.get(i).equals("-")){
                i++;
                Z();X();V();
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void X(){
        if(answer == true){
            if(tokens.get(i).equals("*")){
                i++;
                Z();X();
            }
            else if(tokens.get(i).equals("/")){
                i++;
                Z();X();
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void Z(){
        if(answer == true){
            if(tokens.get(i).equals("(")){
                i++;
                parenCount++;
                R();
                if(tokens.get(i).equals(")")){
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
                i++;
                parenCount++;
                R();C();
                if(tokens.get(i).equals(")")){
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

}