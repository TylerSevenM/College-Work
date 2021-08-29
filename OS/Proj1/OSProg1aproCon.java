//package Java.OS;

//Tyler Merrett - N01146025

import java.util.*;

public class OSProg1aproCon extends Thread{
    Stack<Integer> st = new Stack<Integer>();

    public void producer() throws InterruptedException{
        try {
            while(true){
                synchronized(this){
                    sleep(100);
                    if(st.size()<5){
                        notify();
                        st.push(1);
                        System.out.println(st);
                    }
                    else{
                        System.out.println("Producer waiting.");
                        wait();
                    }
                }
            } 
        }
        catch (Exception e) {}
    }
    public void consumer() throws InterruptedException{ 
        try {
            while(true){
                synchronized(this){
                    sleep(100);
                    if(st.empty() == false){
                        notify();
                        System.out.println(st);
                        st.pop();
                        
                    }
                    else{
                        System.out.println("Consumer waiting.");
                        wait();
                    }
                }
            }
        } 
        catch (Exception e) {}
    }
    

}