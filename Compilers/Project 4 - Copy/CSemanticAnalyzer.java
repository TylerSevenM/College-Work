//Tyler Merrett - N01146025

import java.util.*;

public class CSemanticAnalyzer{

    static ArrayList<String> tokens = new ArrayList<String>();
    static ArrayList<Integer> scope = new ArrayList<Integer>(tokens.size());
    //answer is essentially the constant "return if false" checker
    //it ensures the grammar, if correct, remains correct or fails if wrong
    static boolean answer = true;
    static int i;
    static int scopeCount;
    static int scopeLevel;
    static int parenCount = 0;
    static int bracketCount = 0;
    static int braceCount = 0;

    public static boolean Semantical(ArrayList<String> tokenList){

        //Count 'i' start at zero for token array
        i = 0;
        scopeCount = 0;
        scopeLevel = 0;
        //make token array the global ArrayList so it can be freely parsed
        //by the methods with a simple count increment method
        tokens = tokenList;
        try {
            if (tokens.size() > 2) {
                A();
            }else{answer = false;}
        } catch (IndexOutOfBoundsException e) {}
        if(i>=tokens.size() && answer == true && parenCount == 0 && bracketCount == 0 && braceCount == 0){
            for(int z = 0; z<tokens.size();z++){
                System.out.print(tokens.get(z));
                System.out.print(scope.get(z));
            }
            return true;
        }else{return false;}
    }
    // I used the grammar as dictated in the book; however, I changed the names 
    // of each of the values. It's quite the looker :) . Grammar in README.txt
    public static void A(){
        if(answer == true){
            if(tokens.get(i).equals("t")){
                i++;
                scope.add(scopeLevel);
                if(tokens.get(i).equals("i")){
                    i++;
                    scope.add(scopeLevel);
                    E();
                }else{answer = false;}
            }
            else if(tokens.get(i).equals("v")){
                i++;
                scope.add(scopeLevel);
                if(tokens.get(i).equals("i")){
                    i++;
                    scope.add(scopeLevel);
                    E();
                }else{answer = false;}
            }else{answer = false;}
        }else{answer = false;}
    }
    public static void B(){
        if(answer == true){
            if(tokens.get(i).equals("t")){
                i++;
                scope.add(scopeLevel);
                if(tokens.get(i).equals("i")){
                    i++;
                    scope.add(scopeLevel);
                    E();
                }else{answer = false;}
            }else if(tokens.get(i).equals("v")){
                i++;
                scope.add(scopeLevel);
                if(tokens.get(i).equals("i")){
                    i++;
                    scope.add(scopeLevel);
                    E();
                }else{answer = false;}
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void E(){
        if(answer == true){
            if(tokens.get(i).equals(";")){
                i++;
                scope.add(scopeLevel);
                B();
            }
            else if(tokens.get(i).equals("[")){
                i++;
                scope.add(scopeLevel);
                bracketCount++;
                if(tokens.get(i).equals("n")){
                    i++;
                    scope.add(scopeLevel);
                    if(tokens.get(i).equals("]")){
                        i++;
                        scope.add(scopeLevel);
                        bracketCount--;
                        if(tokens.get(i).equals(";")){
                            i++;
                            scope.add(scopeLevel);
                            B();
                        }else{answer = false;}
                    }else{answer = false;}
                }else{answer = false;}
            }
            else if(tokens.get(i).equals("(")){
                i++;
                scope.add(scopeLevel);
                parenCount++;
                G();
                if(tokens.get(i).equals(")")){
                    i++;
                    scope.add(scopeLevel);
                    parenCount--;
                    if(tokens.get(i).equals("{")){
                        i++;
                        scopeLevel++;
                        scope.add(scopeLevel);
                        braceCount++;
                        K();L();
                        if(tokens.get(i).equals("}")){
                            i++;
                            scopeLevel--;
                            scope.add(scopeLevel);
                            braceCount--;
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
                scope.add(scopeLevel);
                if(tokens.get(i).equals("i")){
                    i++;
                    scope.add(scopeLevel);
                    I();
                    H();
                }else{answer = false;}
            }
            else if(tokens.get(i).equals("v")){
                i++;
                scope.add(scopeLevel);
                S();
            }else{answer = false;}
        }else{answer = false;}
    }
    public static void S(){
        if(answer == true){
            if(tokens.get(i).equals("i")){
                i++;
                scope.add(scopeLevel);
                I();H();
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void H(){
        if(answer == true){
            if(tokens.get(i).equals(",")){
                i++;
                scope.add(scopeLevel);
                F();
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void F(){
        if(answer == true){
            if(tokens.get(i).equals("t")){
                i++;
                scope.add(scopeLevel);
                if(tokens.get(i).equals("i")){
                    i++;
                    scope.add(scopeLevel);
                    I();H();
                }else{answer = false;}
            }
            else if(tokens.get(i).equals("v")){
                i++;
                scope.add(scopeLevel);
                if(tokens.get(i).equals("i")){
                    i++;
                    scope.add(scopeLevel);
                    I();H();
                }else{answer = false;}
            }else{answer = false;}
        }else{answer = false;}
    }
    public static void I(){
        if(answer == true){
            if(tokens.get(i).equals("[")){
                i++;
                scope.add(scopeLevel);
                bracketCount++;
                if(tokens.get(i).equals("]")){
                    i++;
                    scope.add(scopeLevel);
                    bracketCount--;
                }else{answer = false;}
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void K(){
        if(answer == true){
            if(tokens.get(i).equals("t")){
                i++;
                scope.add(scopeLevel);
                if(tokens.get(i).equals("i")){
                    i++;
                    scope.add(scopeLevel);
                    D();K();
                }else{answer = false;}
            }
            else if(tokens.get(i).equals("v")){
                i++;
                scope.add(scopeLevel);
                if(tokens.get(i).equals("i")){
                    i++;
                    scope.add(scopeLevel);
                    D();K();
                }else{answer = false;}
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void D(){
        if(answer == true){
            if(tokens.get(i).equals(";")){
                i++;
                scope.add(scopeLevel);
            }
            else if(tokens.get(i).equals("[")){
                i++;
                scope.add(scopeLevel);
                bracketCount++;
                if(tokens.get(i).equals("n")){
                    i++;
                    scope.add(scopeLevel);
                    if(tokens.get(i).equals("]")){
                        i++;
                        scope.add(scopeLevel);
                        bracketCount--;
                        if(tokens.get(i).equals(";")){
                            i++;
                            scope.add(scopeLevel);
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
                    scope.add(scopeLevel);
                }else{answer = false;}
            }
            else if(tokens.get(i).equals(";")){
                i++;
                scope.add(scopeLevel);
            }
            else if(tokens.get(i).equals("{")){
                i++;
                scopeLevel++;
                scope.add(scopeLevel);
                braceCount++;
                K();L();
                if(tokens.get(i).equals("}")){
                    i++;
                    scopeLevel--;
                    scope.add(scopeLevel);
                    braceCount--;
                }else{answer = false;}
            }
            else if(tokens.get(i).equals("f")){
                i++;
                scope.add(scopeLevel);
                if(tokens.get(i).equals("(")){
                    i++;
                    scope.add(scopeLevel);
                    parenCount++;
                    R();
                    if(tokens.get(i).equals(")")){
                        i++;
                        scope.add(scopeLevel);
                        parenCount--;
                        M();O();
                    }else{answer = false;}
                }else{answer = false;}
            }
            else if(tokens.get(i).equals("w")){
                i++;
                scope.add(scopeLevel);
                if(tokens.get(i).equals("(")){
                    i++;
                    scope.add(scopeLevel);
                    parenCount++;
                    R();
                    if(tokens.get(i).equals(")")){
                        i++;
                        scope.add(scopeLevel);
                        parenCount--;
                        M();
                    }else{answer = false;}
                }else{answer = false;}
            }
            else if(tokens.get(i).equals("r")){
                i++;
                scope.add(scopeLevel);
                Q();
            }else{answer = false;}
        }else{answer = false;}
    }
    public static void O(){
        if(answer == true){
            if(tokens.get(i).equals("e")){
                i++;
                scope.add(scopeLevel);
                M();
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void Q(){
        if(answer == true){
            if(tokens.get(i).equals(";")){
                i++;
                scope.add(scopeLevel);
            }
            else if((tokens.get(i).equals("(")||tokens.get(i).equals("i")||
            tokens.get(i).equals("n"))){
                //false look ahead example
                R();
                if(tokens.get(i).equals(";")){
                    i++;
                    scope.add(scopeLevel);
                }else{answer = false;}
            }else{answer = false;}
        }else{answer = false;}
    }
    public static void R(){
        if(answer == true){
            if(tokens.get(i).equals("i")){
                i++;
                scope.add(scopeLevel);
                N();
            }
            else if(tokens.get(i).equals("(")){
                i++;
                scope.add(scopeLevel);
                parenCount++;
                R();
                if(tokens.get(i).equals(")")){
                    i++;
                    scope.add(scopeLevel);
                    parenCount--;
                    X();V();T();
                }else{answer = false;}
            }
            else if(tokens.get(i).equals("n")){
                i++;
                scope.add(scopeLevel);
                X();V();T();
            }       
        }else{answer = false;}
    }
    public static void N(){
        if(answer == true){
            if(tokens.get(i).equals("=")){
                i++;
                scope.add(scopeLevel);
                R();
            }
            else if(tokens.get(i).equals("[")){
                i++;
                scope.add(scopeLevel);
                bracketCount++;
                R();
                if(tokens.get(i).equals("]")){
                    i++;
                    scope.add(scopeLevel);
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
                scope.add(scopeLevel);
                parenCount++;
                R();C();
                if(tokens.get(i).equals(")")){
                    i++;
                    scope.add(scopeLevel);
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
                scope.add(scopeLevel);
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
                scope.add(scopeLevel);
                Z();X();V();
            }
            else if(tokens.get(i).equals("<")){
                i++;
                scope.add(scopeLevel);
                Z();X();V();
            }
            else if(tokens.get(i).equals(">")){
                i++;
                scope.add(scopeLevel);
                Z();X();V();
            }
            else if(tokens.get(i).equals(">=")){
                i++;
                scope.add(scopeLevel);
                Z();X();V();
            }
            else if(tokens.get(i).equals("==")){
                i++;
                scope.add(scopeLevel);
                Z();X();V();
            }
            else if(tokens.get(i).equals("!=")){
                i++;
                scope.add(scopeLevel);
                Z();X();V();
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void V(){
        if(answer == true){
            if(tokens.get(i).equals("+")){
                i++;
                scope.add(scopeLevel);
                Z();X();V();
            }
            else if(tokens.get(i).equals("-")){
                i++;
                scope.add(scopeLevel);
                Z();X();V();
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void X(){
        if(answer == true){
            if(tokens.get(i).equals("*")){
                i++;
                scope.add(scopeLevel);
                Z();X();
            }
            else if(tokens.get(i).equals("/")){
                i++;
                scope.add(scopeLevel);
                Z();X();
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void Z(){
        if(answer == true){
            if(tokens.get(i).equals("(")){
                i++;
                scope.add(scopeLevel);
                parenCount++;
                R();
                if(tokens.get(i).equals(")")){
                    i++;
                    scope.add(scopeLevel);
                    parenCount--;
                }else{answer = false;}
            }
            else if(tokens.get(i).equals("i")){
                i++;
                scope.add(scopeLevel);
                J();
            }
            else if(tokens.get(i).equals("n")){
                i++;
                scope.add(scopeLevel);
            }else{answer = false;}
        }else{answer = false;}
    }
    public static void J(){
        if(answer == true){
            if(tokens.get(i).equals("[")){
                i++;
                scope.add(scopeLevel);
                bracketCount++;
                R();
                if(tokens.get(i).equals("]")){
                    i++;
                    scope.add(scopeLevel);
                    bracketCount--;
                }else{answer = false;}
            }
            else if((tokens.get(i).equals("(") && tokens.get(i+1).equals(")"))){
                i+=2;
            }
            else if(tokens.get(i).equals("(")){
                i++;
                scope.add(scopeLevel);
                parenCount++;
                R();C();
                if(tokens.get(i).equals(")")){
                    i++;
                    scope.add(scopeLevel);
                    parenCount--;
                }else{answer = false;}
            }else{/*Epsilon*/}
        }else{answer = false;}
    }
    public static void C(){
        if(answer == true){
            if(tokens.get(i).equals(",")){
                i++;
                scope.add(scopeLevel);
                R();C();
            }else{/*Epsilon*/}
        }else{answer = false;}
    }

}
