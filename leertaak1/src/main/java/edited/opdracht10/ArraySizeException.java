package edited.opdracht10;

/**
 * Created by johankladder on 9-2-16.
 */
public class ArraySizeException extends Exception {

    public ArraySizeException(int[] firstArray, int[] secondArray)
    {
        super("Couldn't add two arrays for arrays: " + firstArray + " & " + secondArray);
    }

}
