package exercise1.exercise294;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kevin on 14-3-2016.
 */
public class Main {
    private static Sum s = new Sum();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        // Create and launch 1000 threads
        for (int i = 0; i < 1000; i++) {
            executor.execute(new IncrementSumTask());
        }

        executor.shutdown();

        // Wait until all tasks are finished
        while (!executor.isTerminated()) {
        }

        System.out.println("What is the sum? " + s.getSum());
    }

    private static class IncrementSumTask implements Runnable {
        public void run() {
            s.add(1);
        }
    }

    // An inner class for sum
    private static class Sum {
        private int sum = 0;

        public int getSum() {
            return sum;
        }

        // with or without synchronized
        public synchronized void add(int amount) {
            sum = sum + amount;
        }
    }
}
