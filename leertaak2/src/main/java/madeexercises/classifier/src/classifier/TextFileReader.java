package madeexercises.classifier.src.classifier;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kevin on 17-2-2016.
 */
public class TextFileReader {
    private static final String FEATURE_PATH = "OptiesText.txt";
    private static final String SET_PATH = "TrainingSet.txt";
    private BufferedReader featureReader;
    private BufferedReader setReader;
    private FeatureType yn;
    private HashMap<String, FeatureType> featureMap;
    private HashMap<Item, String> setMap;
    private String[] features;

    public TextFileReader() throws IOException {
        // Yes/No FeatureType
        yn = new FeatureType("YesNo"
                ,new String[]{"yes","no"});
        // FileReaders
        try {
            this.featureReader = new BufferedReader(new FileReader(FEATURE_PATH));
            this.setReader = new BufferedReader(new FileReader(SET_PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Get Feature names from OptiesText.txt
        this.features = parseFeatures();
        this.featureMap = new HashMap<String, FeatureType>();
        this.setMap = new HashMap<Item, String>();
    }

    /**
     * Get an array with 8 Features with corresponding values from txt file
     *
     * @param values array with values from txt file
     * @return Feature array
     */
    private Feature[] getFeatures(String [] values) {
        ArrayList<Feature> temp3 = new ArrayList<Feature>();

        // create 8 Features with corresponding values from txt file
        for(int i = 0; i < 8; i++) {
            Feature f = new Feature(this.features[i], values[i+1], yn);
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
    public String[] parseFeatures() throws IOException {
        ArrayList<String> temp = new ArrayList<String>();

        // Read OptiesText.txt
        String line;
        while((line = featureReader.readLine()) != null) {
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
     * @throws IOException
     */
    public void parseSet() throws IOException {
        // skip first two lines
        setReader.readLine();
        setReader.readLine();

        // read rest of file
        String line;
        while((line = setReader.readLine()) != null) {
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
