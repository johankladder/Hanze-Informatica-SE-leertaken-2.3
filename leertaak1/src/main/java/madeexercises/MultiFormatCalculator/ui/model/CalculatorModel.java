package madeexercises.MultiFormatCalculator.ui.model;

import madeexercises.MultiFormatCalculator.multiformat.*;
import madeexercises.MultiFormatCalculator.ui.view.ModelView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by johankladder on 12-2-16.
 */
public class CalculatorModel extends AbstractModel {

    private static Map<String, Base> bases = new HashMap<>();
    private static Map<String, Format> formats = new HashMap<>();
    private static Map<String, Method> operators = new HashMap<>();


    private Calculator calc = new Calculator();
    private int calcCounter = 0 ;


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


    /*
    Methods:
     */

    public void addView(ModelView view) {
        if (view != null) {
            views.add(view);
        }
    }


    public void setBase(String base) {
        Base foundBase = bases.get(base);
        if (foundBase != null) {
            calc.setBase(foundBase);
        }
        updateViews();
    }

    public void setFormat(String format) {
        Format foundFormat = formats.get(format);
        if (foundFormat != null) {
            calc.setFormat(foundFormat);
        }
        updateViews();
    }

    public void setOperator(String operator) {
        Method method = operators.get(operator);
        if (method != null) {
            try {
                method.invoke(calc);
                calcCounter++;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        updateViews();
    }

    public void setOp(String op) {
        try {
            char[] values = op.toCharArray();
            String digits = calc.getBase().getDigits();

            try {
                for (char c : values) {
                    // FIXME: Not working with rational values? -> /
                    if ((digits.indexOf(c) == -1) && (c != '.')) {
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


    // TODO: DOC
    public Map<String, Base> getBases() {
        return bases;
    }

    public Map<String, Format> getFormats() {
        return formats;
    }

    public Map getOperators() {
        return operators;
    }

    public int getCalcCounter() {
        return calcCounter;
    }

    public String getValues() {
        return ("\n[" + calc.getBase().getName() + ","
                + calc.getFormat().getName() + ","
                + calc.firstOperand() + ", "
                + calc.secondOperand() + "]");
    }

    public Calculator getCalc() {
        return calc;
    }



}
