package madeexercises.classifier.classifier;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kevin on 17-2-2016.
 */
public class TextFileReader {
    private static String featurePath = "OptiesText.txt";
    private String trainingsPath = "TrainingSet.txt";
    private FileReader trainingReader;
    private FeatureType yn;
    private HashMap<String, FeatureType> featureMap;
    private HashMap<Item, String> setMap;
    private String[] features;

    public TextFileReader() throws IOException {
        // Yes/No FeatureType
        yn = new FeatureType("YesNo"
                , new String[]{"1", "0"});
        // FileReaders
        try {

            InputStream in = getClass().getResourceAsStream("/" + featurePath);
            BufferedReader featureReader = new BufferedReader(new InputStreamReader(in));

            InputStream ins = getClass().getResourceAsStream("/" + trainingsPath);
            BufferedReader trainingReader = new BufferedReader(new InputStreamReader(ins));

            this.features = parseFeatures(featureReader);
            this.featureMap = new HashMap<String, FeatureType>();
            this.setMap = new HashMap<Item, String>();

            parseSet(trainingReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Get Feature names from OptiesText.txt

    }

    /**
     * Get an array with 8 Features with corresponding values from txt file
     *
     * @param values array with values from txt file
     * @return Feature array
     */
    private Feature[] getFeatures(String[] values) {
        ArrayList<Feature> temp3 = new ArrayList<Feature>();

        // create 8 Features with corresponding values from txt file
        for (int i = 0; i < 8; i++) {
            Feature f = new Feature(this.features[i], values[i + 1], yn);
            temp3.add(f);
            featureMap.put(this.features[i], yn);
        }

        // return 8 Features
        Feature[] temp4 = new Feature[temp3.size()];
        temp3.toArray(temp4);
        return temp4;
    }

    /**
     * Parse Feature to an array from txt file
     *
     * @return String array with Feature names
     * @throws IOException
     */
    public String[] parseFeatures(BufferedReader featureReader) throws IOException {
        ArrayList<String> temp = new ArrayList<String>();

        // Read OptiesText.txt
        String line;
        while ((line = featureReader.readLine()) != null) {
            temp.add(line);
        }

        // return all Strings read from file
        String[] temp2 = new String[temp.size()];
        temp.toArray(temp2);

        return temp2;
    }

    /**
     * Read training set from TrainingSet.txt and create Items
     *
     * @param setReader
     * @throws IOException
     */
    public void parseSet(BufferedReader setReader) throws IOException {
        // skip first two lines
        setReader.readLine();
        setReader.readLine();

        // read rest of file
        String line;
        while ((line = setReader.readLine()) != null) {
            String[] items = line.split(";");
            // name
            String name = items[9];
            Feature[] features = getFeatures(items);

            // create Item with name and 8 Features with corresponding values
            Item i = new Item(name, features);
            setMap.put(i, name);
        }
    }

    // getters
    public String[] getFeaturesArray() {
        return this.features;
    }

    public Map<Item, String> getTrainingsSet() {
        return this.setMap;
    }

    public Map<String, FeatureType> getFeaturesMap() {
        return this.featureMap;
    }

}
