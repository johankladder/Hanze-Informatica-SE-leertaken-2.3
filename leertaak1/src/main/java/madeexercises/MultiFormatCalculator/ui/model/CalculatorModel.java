package madeexercises.MultiFormatCalculator.ui.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import madeexercises.MultiFormatCalculator.multiformat.*;
import madeexercises.MultiFormatCalculator.ui.view.ModelView;

/**
 * Model that holds all the logic for communicating with the Calculator class.
 */
public class CalculatorModel extends AbstractModel {

    // Predefined values:
    private static Map<String, Base> bases = new HashMap<>();
    private static Map<String, Format> formats = new HashMap<>();
    private static Map<String, Method> operators = new HashMap<>();

    private Calculator calc = new Calculator();
    private int calcCounter = 0;


    // Insert predefined values:
    static {
        // Bases:
        bases.put("bin", new BinaryBase());
        bases.put("oct", new OctBase());
        bases.put("hex", new HexBase());
        bases.put("dec", new DecimalBase());

        // Formats:
        formats.put("rat", new RationalFormat());
        formats.put("fixed", new FixedPointFormat());
        formats.put("float", new FloatingPointFormat());

        // Operators:
        try {

            // ! - Can be vulnerable when method names are changing,
            // ! - but will save some code on a later stage at this moment.

            operators.put("+", Calculator.class.getMethod("add"));
            operators.put("-", Calculator.class.getMethod("subtract"));
            operators.put("/", Calculator.class.getMethod("divide"));
            operators.put("*", Calculator.class.getMethod("multiply"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }


    /*
    Methods:
     */

    /**
     * Adds a view to this model. This view will be invoked when setters of this model are called.
     *
     * @param view The view liked to be added to this model
     * @see ModelView
     */
    public void addView(ModelView view) {
        if (view != null) {
            views.add(view);
        }
    }


    /**
     * Sets a Base for the calculator. The method will check if the given Base's name is valid and will thereby
     * set it in the calculator
     *
     * @param base Name of a Base
     */
    public void setBase(String base) {
        Base foundBase = bases.get(base);
        if (foundBase != null) {
            calc.setBase(foundBase);
        }
        updateViews();
    }

    /**
     * Sets a format for the calculator. This method will check if the given format is valid and will thereby add
     * it to the Calculator.
     *
     * @param format The format's name
     */
    public void setFormat(String format) {
        Format foundFormat = formats.get(format);
        if (foundFormat != null) {
            calc.setFormat(foundFormat);
        }
        updateViews();
    }

    /**
     * Method that is invoking a method that belongs to the given name of a Operator. When a method can be found,
     * this method will perform the action on the Calculator and will increment the calculation-counter.
     *
     * @param operator Name of an operator that the user liked to be invoked.
     */
    public void setOperator(String operator) {
        Method method = operators.get(operator);
        if (method != null) {
            try {
                method.invoke(calc);
                calcCounter++;
                updateViews();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sets a given string as a new operand in the calculator. The given String object will be parsed into a
     * Char array of each digit/letter. After that, the logic will loop through all the entries the ensure that
     * the given entries fit well in the also given Base object in the Calculator class.
     *
     * @param op The operand liked to be added into the Calculator.
     * @see Calculator
     * @see Base
     */
    public void setOperand(String op) {
        try {
            char[] values = op.toCharArray();
            String digits = calc.getBase().getDigits();

            try {
                for (char c : values) {
                    if ((digits.indexOf(c) == -1)) {
                        throw new NumberBaseException();
                    }
                }
            } catch (NumberBaseException e) {
                e.printStackTrace();
            }

            calc.addOperand(op);
            updateViews();
        } catch (FormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns a Map containing the key (name of a base) and the
     * Base object itself. This map contains all the currently available
     * Base-objects that are supported by the calculator.
     *
     * @return map containing all the Base-objects
     * @see Base
     */
    public Map<String, Base> getBases() {
        return bases;
    }

    /**
     * This method returns a Map containing the key (name of a format) and the
     * Format object itself. This map contains all the currently available
     * Format-objects that are supported by the calculator.
     *
     * @return map containing all the Format-objects
     * @see Format
     */
    public Map<String, Format> getFormats() {
        return formats;
    }

    /**
     * This method returns a Map containing the key (name of a operator) and the method that
     * will invoke this action on the calculator. This map contains all the currently available
     * operators that are supported by the calculator.
     * <p>
     * They value of every entry in this Map, can be invoked as an method.
     * <p>
     * - Example:
     * <code>
     * method.invoke(calc);
     * </code>
     * <p>
     * 'calc' needs to be a already initialised object of Calculator.
     *
     * @return map containing all the Base-objects
     * @see Method
     * @see Calculator
     */

    public Map<String, Method> getOperators() {
        return operators;
    }

    /**
     * Returns a String object with all the intresting values from the calculator.
     * This object is already nicely formatted, so can be used in a GUI.
     *
     * @return formatted Calculator-values-String
     */
    public String getValues() {
        return ("\n[" + calc.getBase().getName() + ","
                + calc.getFormat().getName() + ","
                + calc.firstOperand() + ", "
                + calc.secondOperand() + "]");
    }

    /**
     * Returns the counter of how many calculations the calculator already has made, according to this model.
     *
     * @return calculation-counter
     */
    public int getCalcCounter() {
        return calcCounter;
    }

    /**
     * Returns the used Calculator object of this model. This Calculator is used to perform all the actions on that
     * where invoked by the controllers of this model.
     *
     * @return Calculator object that is used by this object
     * @see Calculator
     */
    public Calculator getCalc() {
        return calc;
    }


}
