package madeexercises.MultiFormatCalculator.ui.model;

import madeexercises.MultiFormatCalculator.multiformat.Calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johankladder on 12-2-16.
 */
public class CalculatorModel extends AbstractModel {

    private static ArrayList<String> bases = new ArrayList<>();
    private static ArrayList<String> formats = new ArrayList<>();

    // Insert valid values:
    static {
        // Bases:
        bases.add("bin");
        bases.add("oct");
        bases.add("hex");
        bases.add("dec");

        // Formats
        formats.add("rat");
        formats.add("fixed");
        formats.add("float");

    }

    private Calculator calc = new Calculator();



    // TODO: DOC
    public List getBases() {
        return bases;
    }

    public List getFormats() {
        return formats;
    }

}
