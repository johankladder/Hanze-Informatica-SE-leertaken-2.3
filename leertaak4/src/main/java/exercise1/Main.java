package exercise1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johankladder on 11-3-16.
 */
public class Main {

    private static final int NUMBER_OF_THREADS = 4;

    public int counter = 0;

    public static void main(String[] args) {
        Main main = new Main();
        main.createThreads();
    }

    public void createThreads() {
        List<Thread> threads = new ArrayList<Thread>();

        // Add number of thread to the list:
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            int nextValue = i + 2;
            if(nextValue > 4) {
                nextValue = 4;
            }
            threads.add(new ExerciseThread(i+1, nextValue));
        }

        // Run them all:
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
        }

    }
}
