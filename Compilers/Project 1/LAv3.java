//Tyler Merrett - N01146025
import java.io.*;
import java.util.*;

public class LAv3 {

    //Every character will be attached to this string to be parsed
    static String appended = "";
    //list for conditionals to check against
    static List<String> keywords = Arrays.asList("else", "if", "int", "return", "void", "while");
    static List<String> specSym = Arrays.asList("+", "-", "*", "/", "<", "<=", ">", ">=", "==", "!=","!", "=", ";", ",", "(", ")", "[", "]", "{", "}", "/*", "*/");
    static List<String> loneSym = Arrays.asList("+", "-", "*", ";", ",", "(", ")", "[", "]", "{", "}", "/", "=", "<", ">");
    static List<String> wrongSym = Arrays.asList("*", "&", "@", "#", "$", "%", "^", "_", "~", "`", "?", ".", "'", "\"", "!"); 
    public static void main(String[] args) throws IOException {
        try {
            //blocker will be able to parse through commented sections
            boolean blocker = false;
            Scanner s = new Scanner(new FileReader(args[0]));
            //while the file has lines to read it will
            while (s.hasNextLine()) {
                //this will take the lines into a string which will be broken into a character array below.
                String str = s.nextLine();
                if(!str.equals(""))
                    str+= " ";
                char[] ch = str.toCharArray();
                //will parse through each and every character on the line
                for(int i = 0; i<str.length(); i++){
                    if(i == 0)
                        //shows the input line
                        System.out.println("INPUT: " + str);
                            //both varities of comments will be handled here
                            if(ch[i] == '/' && ch[i+1] == '/'){
                                Identify();
                                break;}
                            else if(blocker == true || (ch[i] == '/' && ch[i+1] == '*')){
                                blocker = true;
                                i+=2;
                                while(blocker == true){
                                    if(i == ch.length-1){
                                        break;}
                                    i++;
                                    if(ch[i] == '*' && ch[i+1] == '/'){
                                        i+=2;
                                        blocker = false;}
                                }
                            }
                    //if the character in question ch[i] is a letter it will enter here
                    if(Character.isLetter(ch[i])){
                        if(!appended.matches("[a-zA-Z]+")){
                            Identify();}
                        appended+=ch[i];}
                    //if the character in question ch[i] is a number it will enter here
                    if(Character.isDigit(ch[i])){
                        if(!appended.matches("-?(0|[1-9]\\d*)") && appended.length()>0){
                            Identify();}
                        appended+=ch[i];}
                    //if the character in question ch[i] is a symbol it will enter here
                    //all symbols will be handled here instead of in the Identify() method
                    if(!Character.isLetterOrDigit(ch[i])){
                        //first thing I needed to check was for compound symbols as they're higher priority
                        if((appended.equals("<") || appended.equals(">") || appended.equals("=") || appended.equals("!")) && ch[i] == '='){
                            if(ch[i] == '='){
                                appended+=ch[i];
                                System.out.println(appended);
                                appended = "";}
                            else{
                                Identify();}
                        }
                        else if(appended.equals("")  && !Character.isWhitespace(ch[i])){
                            appended+=ch[i];}
                        else if(loneSym.contains(appended)){
                            Identify();
                            if(!Character.isWhitespace(ch[i])){
                                appended+= ch[i];}
                        }
                        else{
                            Identify();
                            if(!Character.isWhitespace(ch[i])){
                                appended+= ch[i];}
                        }
                }
            }
        }s.close();  
    } catch (IOException e) {}
    }
    //Identify class will be used to handle things in terms of priority, 1.KW, 2.Nums (nums>ID so variable1 isn't "correct") 
    //3. ID, 4. Lone symbols (compounds handled in main), 5. Errors
    public static void Identify(){
        if(appended.equals("")){
            return;}
        if(keywords.contains(appended)){
            System.out.println("KW: " + appended);
            appended = "";}
        else if(appended.matches("(0|[1-9]\\d*)") && appended.length()>0){
            System.out.println("INT: " + appended);
            appended = "";}  
        else if(!keywords.contains(appended) && appended.length()>0 && !appended.matches("(0|[1-9]\\d*)") && !specSym.contains(appended) && !wrongSym.contains(appended) && !appended.contains("=")){
            System.out.println("ID: " + appended);
            appended = "";}
        else if(loneSym.contains(appended) && !appended.equals("!")){
            System.out.println(appended);
            appended = "";}
        else{
            System.out.println("Error: " + appended);
            appended = "";}
    }  
}