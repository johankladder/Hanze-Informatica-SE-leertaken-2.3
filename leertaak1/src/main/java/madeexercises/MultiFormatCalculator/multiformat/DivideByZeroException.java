package madeexercises.MultiFormatCalculator.multiformat;

/**
 * Created by johankladder on 12-2-16.
 */
public class DivideByZeroException extends Exception {

    public DivideByZeroException() {
        super("Can't divide by zero");
    }
}
