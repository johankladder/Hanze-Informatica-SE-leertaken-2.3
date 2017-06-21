package exercise1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by johankladder on 11-3-16.
 */
public class ExerciseThread extends Thread {


    private int value;

    private static ReentrantLock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();


    private static int nextValue;

    public ExerciseThread(int value, int nextValue) {

        this.value = value;
        this.nextValue = nextValue;

    }


    @Override
    public void run() {
        try {
            print();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void print() throws InterruptedException {
        lock.lock();
        try {
            while(value < nextValue) {
                condition.await();
            }
            println();
        } finally {
            lock.unlock();
        }
    }

    private void println() {
        System.out.print(value);
        System.out.print(value + "\n");
        nextValue = value - 1;
        condition.signalAll();
    }


}
