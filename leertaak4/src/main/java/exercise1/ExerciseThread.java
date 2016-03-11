package exercise1;

/**
 * Created by johankladder on 11-3-16.
 */
public class ExerciseThread extends Thread {

    private static final int PRINT_COUNTER = 2;

    private Main main;

    public ExerciseThread(Main main) {
        this.main = main;
    }


    private static Integer thisCounter = 0;

    @Override
    public void run() {
            main.counter++;
            System.out.println(main.counter);
    }


}
