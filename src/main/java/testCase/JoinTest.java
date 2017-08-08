package testCase;

public class JoinTest {
    public static void main(String[] args) {
        Thread t = new Thread(new RunnableImpl());
        long begin = System.currentTimeMillis();
        System.out.println("begin : "+begin);
       new ThrdTest(t).start();
        t.start();
        try {
            t.join();
            System.out.println("joinFinish");
            System.out.println("end : "+ System.currentTimeMillis() +", cost : " + (System.currentTimeMillis()-begin));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
     
        }
    }
}
class ThrdTest extends Thread {

    Thread thread;

    public ThrdTest(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        holdThreadLock();
    }

    public void holdThreadLock() {
        synchronized (thread) {
            System.out.println("getObjectLock");
            try {
                Thread.sleep(2000);

            } catch (InterruptedException ex) {
             ex.printStackTrace();
            }
            System.out.println("ReleaseObjectLock");
        }

    }
}

class RunnableImpl implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("Begin sleep");
            Thread.sleep(2000);
           System.out.println("End sleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}