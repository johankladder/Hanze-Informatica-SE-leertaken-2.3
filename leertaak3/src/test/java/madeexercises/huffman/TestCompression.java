package madeexercises.huffman;

import org.junit.Test;
import madeexercises.huffman.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by kevin on 5-3-2016.
 */
public class TestCompression {

    private static final String TEST_PATH = "TestA.dat";

    @Test
    public void testCompression() throws IOException {

        URL url = Thread.currentThread().getContextClassLoader().getResource(TEST_PATH);
        File file = new File(url.getPath());
        Hzip.compress(file.getPath());
        Hzip.uncompress(file.getPath() + ".huf");
    }
}
