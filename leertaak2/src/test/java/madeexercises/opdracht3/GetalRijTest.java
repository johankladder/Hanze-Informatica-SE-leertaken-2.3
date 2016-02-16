package madeexercises.opdracht3;

import org.junit.Test;

import java.util.Map;
import java.util.Random;

public class GetalRijTest {

    @Test
    public void testTestTime() throws Exception {

        // Value needs to be less then 1 million.
        long methodAmilli = 0;
        long methodBmilli = 0;
        int testTimes = 10;
        Random r = new Random();
        for (int i = 0; i < testTimes; i++) {
            int rI = r.nextInt(1000000);
            System.out.println("Performing test if " + rI + " exists in Getalrij of 1000000");
            Map map = GetalRij.testTime(rI);
            if (map != null) {
                methodAmilli = methodAmilli + (Long) map.get("a");
                methodBmilli = methodBmilli + (Long) map.get("b");
            }
            System.out.println("Test for " + rI + " done. \n");
        }

        System.out.println("Average methodA = " + methodAmilli / testTimes);
        System.out.println("Average methodB = " + methodBmilli / testTimes);

    }
}