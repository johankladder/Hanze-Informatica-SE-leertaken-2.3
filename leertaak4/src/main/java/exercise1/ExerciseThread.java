package exercise1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

/**
 * Created by johankladder on 11-3-16.
 */
public class ExerciseThread extends Thread {

    private static final int PRINT_COUNTER = 2;
    private static Semaphore sem = new Semaphore(1);

    private int value;

    private int nextValue;

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
            sem.acquire();
            System.out.print(value);
            System.out.print(value + "\n");
            sem.release();
    }


}
