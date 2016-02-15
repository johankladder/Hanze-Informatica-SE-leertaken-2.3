/*
 * (C) Copyright 2005 Davide Brugali, Marco Torchiano
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307  USA
 */
package madeexercises.MultiFormatCalculator.multiformat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * The multiformat calculator
 */
public class Calculator {
    private Stack<Rational> operands = new Stack<Rational>();

    Map<String, Rational> map = new HashMap<String, Rational>();

    // The current format of the calculator
    private Format format = new FixedPointFormat();
    // The current numberbase of the calculator
    private Base base = new DecimalBase();

    public void addOperand(String newOperand) throws FormatException {
        pushToStack(format.parse(newOperand, base));
    }

    /**
     * Create variable
     *
     * @param var        ArrayList with characters that represent the variable name
     * @param newOperand String with the value of the variable
     */
    public void createVar(ArrayList<Character> var, String newOperand) {
        StringBuilder builder = new StringBuilder(var.size());
        for (char c : var) {
            builder.append(c);
        }
        try {
            map.put(builder.toString(), format.parse(newOperand, base));
        } catch (FormatException e) {
            e.printStackTrace();
        }
        System.out.println("Variable " + builder.toString() + " = " + map.get(builder.toString()).getNumerator());
    }

    /**
     * Push Rational to the stack
     *
     * @param newOperand Rational to be pushed
     */
    public void pushToStack(Rational newOperand) {
        operands.push(newOperand);
    }

    /**
     * Pop last Rational from the stack
     *
     * @return latest Rational
     */
    public Rational popFromStack() {
        if (operands.size() < 1) {
            return new Rational();
        } else {
            return operands.pop();
        }
    }

    /**
     * Delete last from stack
     */
    public void deleteFromStack() {
        if (operands.size() > 0) {
            operands.remove(0);
        }
    }

    /**
     * Get from stack by index
     *
     * @param index int with index to be returned
     * @return Rational
     */
    public Rational getFromStack(int index) {
        if (operands.size() > index) {
            return operands.get(index);
        }
        return null;
    }

    public void add() {
        Rational firstOperand = popFromStack();
        Rational secondOperand = popFromStack();

        pushToStack(secondOperand.plus(firstOperand));
    }

    public void subtract() {
        Rational firstOperand = popFromStack();
        Rational secondOperand = popFromStack();

        pushToStack(secondOperand.minus(firstOperand));
    }

    public void multiply() {
        Rational firstOperand = popFromStack();
        Rational secondOperand = popFromStack();

        pushToStack(secondOperand.mul(firstOperand));
    }

    public void divide() {
        try {
            Rational firstOperand = popFromStack();
            Rational secondOperand = popFromStack();

            pushToStack(secondOperand.div(firstOperand));
        } catch (DivideByZeroException e) {
            e.printStackTrace();
        }

    }

    public void delete() {
        deleteFromStack();
    }

    public String firstOperand() {
        if (operands.size() == 0) {
            return format.toString(new Rational(), base);
        }
        return format.toString(getFromStack(0), base);
    }

    public Rational firstOperandRational() {
        if (operands.size() == 0) {
            return new Rational();
        }
        return getFromStack(0);
    }

    public String secondOperand() {
        if (operands.size() <= 1) {
            return format.toString(new Rational(), base);
        }
        return format.toString(getFromStack(1), base);
    }

    public Rational secondOperandRational() {
        if (operands.size() <= 1) {
            return new Rational();
        }
        return getFromStack(1);
    }

    public void setBase(Base newBase) {
        base = newBase;
    }

    public Base getBase() {
        return base;
    }

    public void setFormat(Format newFormat) {
        format = newFormat;
    }

    public Format getFormat() {
        return format;
    }
}