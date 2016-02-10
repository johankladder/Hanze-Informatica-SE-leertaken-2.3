package edited.opdracht10;

import java.util.Arrays;

public class Main {

    /**
     * Gets the sum of the values that are inside the two given arrays.
     *
     * @param array1 An array containing int values
     * @param array2 An array containing int values
     * @return Array containing sum of the two given arrays
     * @throws ArraySizeException Trowed when the given arrays aren't the same size
     */
    public int[] getSumOfArrays(int[] array1, int[] array2) throws ArraySizeException {
        if (isSameSize(array1, array2)) {
            return calculateSumArrays(array1, array2);
        } else {
            throw new ArraySizeException(array1, array2);
        }
    }

    /**
     * Method that checks whether a pair of given arrays are the same size.
     * @param array1 An array containing int's
     * @param array2 An array containing int's
     * @return Status if given arrays are the same size
     */
    public boolean isSameSize(int[] array1, int[] array2) {
        return array1.length == array2.length;
    }

    /*
    Method that loops through the two given arrays and creates a sum-result out of it.
    It adds place x from array 1 and place x from array two and places it in an array
    that is as big as the given arrays length.

    When the logic looped through all the entries from the given arrays, it will return
    a array containing the result.
     */
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
