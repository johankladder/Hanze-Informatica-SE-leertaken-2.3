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
        long methodCmilli = 0;
        long methodDmilli = 0;

        int testTimes = 10;
        int counter = 0;
        Random r = new Random();
        System.out.println("Performing speed-test, average of " + testTimes + " tries\n");
        for (int i = 0; i < testTimes; i++) {
            int rI = r.nextInt(1000000);
            Map map = GetalRij.testTime(rI);
            if (map != null) {
                methodAmilli = methodAmilli + (Long) map.get("a");
                methodBmilli = methodBmilli + (Long) map.get("b");
                methodCmilli = methodCmilli + (Long) map.get("c");
                methodDmilli = methodDmilli + (Long) map.get("d");
            }
            counter++;
            System.out.println("Test for random int " + rI + " done. " + (testTimes - counter) + " tests to go!");
        }
        System.out.println("\n");
        System.out.println("Average methodA = " + methodAmilli / testTimes);
        System.out.println("Average methodB = " + methodBmilli / testTimes);
        System.out.println("Average methodC = " + methodCmilli / testTimes);
        System.out.println("Average methodD = " + methodDmilli / testTimes);

    }
}