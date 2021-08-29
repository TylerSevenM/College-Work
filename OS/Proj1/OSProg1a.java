//package Java.OS;

//Tyler Merrett - N01146025
public class OSProg1a{
    public static void main(String[] args) throws InterruptedException{
        OSProg1aproCon pc = new OSProg1aproCon();
        Thread producer = new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    pc.producer();
                }
                catch(InterruptedException e){}
            }
        });
        Thread consumer = new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    pc.consumer();
                }
                catch(InterruptedException e){}
            }
        });
        producer.start();
        consumer.start();
    }
}