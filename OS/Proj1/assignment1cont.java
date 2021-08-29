//Kimberly Laynes
//
//
//
import java.util.*;

public class assignment1cont extends Thread{
    Stack<Integer> buffer = new Stack<Integer>();
    public void producerMethod() throws InterruptedException{
        try {
            while(true){
                synchronized(this){
                    sleep(50);
                    if(buffer.size()<7){
                        notify();
                        buffer.push(0);
                        System.out.println(buffer);
                        
                    }
                    else{
                        System.out.println("Producer waiting.");
                        wait();
                    }
                }
            } 
        }
        catch (Exception e) {
            //e.printStackTrace();
        }
    }
    public void consumerMethod() throws InterruptedException{ 
        try {
            while(true){
                synchronized(this){
                    sleep(50);
                    if(buffer.empty() == false){
                        notify();   
                        System.out.println(buffer);
                        buffer.pop();
                    }
                    else{
                        System.out.println("Consumer waiting.");
                        wait();
                    }
                }
            }
        } 
        catch (Exception e) {
            //e.printStackTrace();
        }
    }
}