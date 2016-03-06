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

    @Test
    public void testCompression() throws IOException {
        File file = new File("src/data/TestA.dat");
        Hzip.compress(file.getPath());
        Hzip.uncompress(file.getPath() + ".huf");
    }
}
