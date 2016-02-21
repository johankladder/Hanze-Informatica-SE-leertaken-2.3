package madeexercises.opdracht18.classifier;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kevin on 17-2-2016.
 */
public class TextFileReader {
    private static final String FEATURE_PATH = "OptiesText.txt";
    private static final String TRAININGS_PATH = "TrainingSet.txt";
    private static FeatureType yn = new FeatureType("YesNo", new String[]{"1", "0"});

    private HashMap<String, FeatureType> featureMap = new HashMap<String, FeatureType>();
    private HashMap<Item, String> setMap = new HashMap<Item, String>();
    private String[] features;

    public TextFileReader() throws IOException {

        BufferedReader featureReader = createReader(FEATURE_PATH);
        BufferedReader trainingReader = createReader(TRAININGS_PATH);

        parseFeatures(featureReader);
        parseSet(trainingReader);
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
    public void parseFeatures(BufferedReader featureReader) throws IOException {
        ArrayList<String> temp = new ArrayList<String>();

        // Read OptiesText.txt
        String line;
        while ((line = featureReader.readLine()) != null) {
            temp.add(line);
        }

        // return all Strings read from file
        String[] temp2 = new String[temp.size()];
        temp.toArray(temp2);

        features = temp2;
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
    public BufferedReader createReader(String path) {
        InputStream in = getClass().getResourceAsStream("/" + path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return reader;
    }

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
