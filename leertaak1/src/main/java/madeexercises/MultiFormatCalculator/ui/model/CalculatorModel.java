package madeexercises.MultiFormatCalculator.ui.model;

import madeexercises.MultiFormatCalculator.multiformat.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by johankladder on 12-2-16.
 */
public class CalculatorModel extends AbstractModel {

    private static Map<String, Base> bases = new HashMap<>();
    private static Map<String, Format> formats = new HashMap<>();
    private static Map<String, Method> operators = new HashMap<>();


    // Insert valid values:
    static {
        // Bases:
        bases.put("bin", new BinaryBase());
        bases.put("oct", new OctBase());
        bases.put("hex", new HexBase());
        bases.put("dec", new DecimalBase());

        // Formats
        formats.put("rat", new RationalFormat());
        formats.put("fixed", new FixedPointFormat());
        formats.put("float", new FloatingPointFormat());

        // Operatoren:
        try {
            operators.put("+", Calculator.class.getMethod("add"));
            operators.put("-", Calculator.class.getMethod("subtract"));
            operators.put("/", Calculator.class.getMethod("divide"));
            operators.put("*", Calculator.class.getMethod("multiply"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }

    private Calculator calc = new Calculator();

    public void setBase(String base) {
        Base foundBase = bases.get(base);
        if (foundBase != null) {
            calc.setBase(foundBase);
        }
    }

    public void setFormat(String format) {
        Format foundFormat = formats.get(format);
        if (foundFormat != null) {
            calc.setFormat(foundFormat);
        }
    }

    public void setOperator(String operator) {
        Method method = operators.get(operator);
        if (method != null) {
            try {
                method.invoke(calc);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public void setOp(String op) {
        try {
            char[] values = op.toCharArray();
            String digits = calc.getBase().getDigits();

            try {
                for (char c : values) {
                    if ((digits.indexOf(c) == -1) && (c != '.')) {
                        throw new NumberBaseException();
                    }
                }
            } catch (NumberBaseException e) {
                e.printStackTrace();
            }

            calc.addOperand(op);
        } catch (FormatException e) {
            e.printStackTrace();
        }
    }


    // TODO: DOC
    public Map getBases() {
        return bases;
    }

    public Map getFormats() {
        return formats;
    }

    public Map getOperators() {
        return operators;
    }


}
