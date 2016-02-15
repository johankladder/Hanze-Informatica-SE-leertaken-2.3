package madeexercises.opdracht10;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testIsSameSize() throws Exception {
        Main main = new Main();
        int[] arrayTest1 = {1,2,3};
        int[] arrayTest2 = {1,2,3};
        assertTrue(main.isSameSize(arrayTest1, arrayTest2));

        int[] arrayTest3 = {1,2,3,5};
        int[] arrayTest4 = {1,2,3};
        assertFalse(main.isSameSize(arrayTest3, arrayTest4));
    }

    @Test
    public void testGetSumOfArrays() throws Exception {
        Main main = new Main();
        int[] arrayTest1 = {1,2,3};
        int[] arrayTest2 = {1,2,3};
        int[] answer = {2,4,6};
        assertArrayEquals(answer, main.getSumOfArrays(arrayTest1, arrayTest2));
    }
}