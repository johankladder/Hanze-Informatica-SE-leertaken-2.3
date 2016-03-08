package madeexercises.huffman;

import junit.framework.TestCase;
import org.junit.Test;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;


/**
 * Created by kevin on 5-3-2016.
 */
public class TestCompression extends TestCase {

    private static ArrayList<Character> normal = new ArrayList<>();
    private static ArrayList<Character> decoded = new ArrayList<>();

    @Test
    public void testCompression() throws IOException {
        File file = new File("src/data/TestA.dat");
        Hzip.compress(file.getPath());
        Hzip.uncompress(file.getPath() + ".huf");

        read("src/data/TestA.dat", true);
        read("src/data/TestA.dat.uc", false);

        for(int i = 0; i < normal.size(); i++) {
            Character normalChar = normal.get(i);
            Character decChar = decoded.get(i);

            boolean status = false;

            if(normalChar == decChar) {
                status = true;
            } else {
                status = false;
            }

            assertTrue(status);
        }

    }

    public static void read(String fileName, boolean normal) throws IOException {
        // replace this with a known encoding if possible
        Charset encoding = Charset.defaultCharset();
            File file = new File(fileName);
            handleFile(file, encoding, normal);
    }

    private static void handleFile(File file, Charset encoding, boolean normal)
            throws IOException {
        try (InputStream in = new FileInputStream(file);
             Reader reader = new InputStreamReader(in, encoding);
             // buffer for efficiency
             Reader buffer = new BufferedReader(reader)) {
            if(normal) {
                handleCharacters(buffer);
            } else {
                handleCharactersN(buffer);
            }
        }
    }

    private static void handleCharacters(Reader reader)
            throws IOException {
        int r;
        while ((r = reader.read()) != -1) {
            char ch = (char) r;
            normal.add(ch);
        }
    }

    private static void handleCharactersN(Reader reader)
            throws IOException {
        int r;
        while ((r = reader.read()) != -1) {
            char ch = (char) r;
            decoded.add(ch);
        }

    }
}
