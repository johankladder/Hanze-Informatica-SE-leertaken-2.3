package edited.opdracht10;

import java.util.Arrays;

public class ArraySizeException extends Exception {

    public ArraySizeException(int[] firstArray, int[] secondArray) {
        super("Couldn't add two arrays for arrays: " + Arrays.toString(firstArray) +
                " & " + Arrays.toString(secondArray) + " -> Size differ");
    }

}
