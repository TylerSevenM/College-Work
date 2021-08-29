//Kimberly Laynes
//
//
//

public class assignment1{
    public static void main(String[] args) throws InterruptedException{
        assignment1cont operations = new assignment1cont();
        Thread producingThread = new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    operations.producerMethod();
                }
                catch(InterruptedException e){}
            }
        });
        Thread consumingThread = new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    operations.consumerMethod();
                }
                catch(InterruptedException e){}
            }
        });
        producingThread.start();
        consumingThread.start();
    }
}