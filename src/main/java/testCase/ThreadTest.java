package testCase;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by danielwood on 13/06/2017.
 */
public class ThreadTest {
    public static void main(String[] args) {
        testShared();
    }

    public static void testShared(){
        SharedObj sharedObj = new SharedObj();
        ThrdShared shared1 = new ThrdShared(sharedObj);
        shared1.setName("shared1");

        ThrdShared shared2 = new ThrdShared(sharedObj);
        shared2.setName("shared2");

        ThrdShared shared3 = new ThrdShared(sharedObj);
        shared3.setName("shared3");

        ThrdShared shared4 = new ThrdShared(sharedObj);
        shared4.setName("shared4");

        try {
            shared1.start();

            shared2.start();

            shared3.start();

            shared4.start();

            shared4.join();
            shared3.join();
            shared2.join();
            shared1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.currentThread().sleep(1000);
            System.out.println(sharedObj.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testFuture(){
        int count = 10;
        Object lock = new Object();
        Thrd thrd1 = new Thrd(lock, count);

        try {
            FutureTask<Integer> futureTask1 = new FutureTask<Integer>(thrd1);
            Thread thread1 = new Thread(futureTask1);
            thread1.start();
            count = futureTask1.get();

            FutureTask<Integer> futureTask2 = new FutureTask<Integer>(new Thrd(lock, count));
            Thread thread2 = new Thread(futureTask2);
            thread2.start();
            count = futureTask2.get();

            FutureTask<Integer> futureTask3 = new FutureTask<Integer>(new Thrd(lock, count));
            Thread thread3 = new Thread(futureTask3);
            thread3.start();
            count = futureTask3.get();

            FutureTask<Integer> futureTask4 = new FutureTask<Integer>(new Thrd(lock, count));
            Thread thread4 = new Thread(futureTask4);
            thread4.start();
            count = futureTask4.get();
            System.out.println("count : "+count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
class Thrd implements Callable<Integer>{
    private Object lock = new Object();
    private int count = 0;
    public Thrd(Object lock, int count){
        this.lock = lock;
        this.count = count;
    }

    public Integer call(){
        return count();
    }

    private int count(){
//        synchronized (lock){
            System.out.println("count before : "+count);
            for(int i=0 ; i<2000 ; i++){
                count++;
            }
            System.out.println("count after : "+count);
//        }
        return count;
    }
}
class SharedObj {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
class ThrdShared extends Thread{
    private SharedObj sharedObj;
    public ThrdShared(SharedObj sharedObj){
        this.sharedObj = sharedObj;
    }

    public void run(){
        synchronized (sharedObj) {
            int count = sharedObj.getCount();
            System.out.println("before---name : " + Thread.currentThread().getName() + ", count : " + count);
            for (int i = 0; i < 1000; i++) {
                count++;
            }
            sharedObj.setCount(count);
            System.out.println("after----name : " + Thread.currentThread().getName() + ", count : " + count);
        }
    }
}
