package edited.opdracht10;

import java.util.Arrays;

public class Main {

    public int[] getSumOfArrays(int[] array1, int[] array2) throws ArraySizeException {

        if (array1.length == array2.length) {
            return calculateSumArrays(array1, array2);
        } else {
            throw new ArraySizeException(array1, array2);
        }
    }

    private int[] calculateSumArrays(int[] array1, int[] array2) {
        int[] sumArray = new int[array1.length];
        for (int i = 0; i < array1.length; i++) {
            Integer sum = array1[i] + array2[i];
            sumArray[i] = sum;
        }

        return sumArray;
    }

    public static void main(String[] args) throws ArraySizeException {
        Main main = new Main();
        int[] array1 = {1, 2, 3};
        int[] array2 = {4, 5, 6,};
        int[] arrayResult1 = main.getSumOfArrays(array1, array2);
        System.out.println(Arrays.toString(arrayResult1));
    }

}
