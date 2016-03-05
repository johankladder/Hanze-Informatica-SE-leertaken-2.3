package madeexercises.huffman;

import org.junit.Test;
import madeexercises.huffman.*;

import java.io.IOException;

/**
 * Created by kevin on 5-3-2016.
 */
public class TestCompression {

    private static final String FEATURE_PATH = "OptiesText.txt";

    @Test
    public void testCompression() throws IOException {
        Hzip.compress("C:\\Users\\kevin\\Documents\\GitHub\\leertaken2.3\\leertaak3\\src\\main\\resources\\" + FEATURE_PATH);
        Hzip.uncompress("C:\\Users\\kevin\\Documents\\GitHub\\leertaken2.3\\leertaak3\\src\\main\\resources\\" + FEATURE_PATH + ".huf");
    }
}
