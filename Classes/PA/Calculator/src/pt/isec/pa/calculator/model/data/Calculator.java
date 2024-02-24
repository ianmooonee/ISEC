package pt.isec.pa.calculator.model.data;

public class Calculator {
    private double lastNumber=0;
    private String strNumber ="0.0";
    private double tempNumber=0;
    private boolean isNewNumber=true;
    private Operator op = Operator.NONE;

    public Calculator() {    }

    public boolean addNewDigit(String digit) {
        if (digit==null || digit.length()!=1)
            return false;
        if (Character.isDigit(digit.charAt(0))) {
            if (isNewNumber)
                strNumber = digit;
            else
                strNumber += digit;
        } else if (digit.equals(".") && (isNewNumber || !strNumber.contains("."))) {
            if (isNewNumber)
                strNumber = "0";
            strNumber += ".";
        } else
            return false;
        try {
            tempNumber = Double.parseDouble(strNumber);
        } catch (Exception e) {
            reset();
            return false;
        }
        isNewNumber = false;
        return true;
    }

    public void reset() {
        lastNumber = 0;
        tempNumber = 0;
        strNumber  = "0.0";
        isNewNumber = true;
        op = Operator.NONE;
    }

    public boolean setOp(Operator newop) {
        if (newop == Operator.NONE)
            return false;
        double value = 0;
        switch (op) {
            case ADD -> value = lastNumber + tempNumber;
            case SUB -> value = lastNumber - tempNumber;
            case MUL -> value = lastNumber * tempNumber;
            case DIV -> {
                if (tempNumber==0) {
                    reset();
                    return false;
                }
                value = lastNumber / tempNumber;
            }
            default -> value = tempNumber;
        }
        lastNumber = tempNumber = value;
        strNumber = ""+value;
        isNewNumber = true;
        op = (newop == Operator.CAL ? Operator.NONE : newop);
        return true;
    }

    public String getDisplay() {
        return strNumber;
    }

    public void invertSignal() {
        tempNumber *= -1;
        strNumber = ""+tempNumber;
        isNewNumber = true;
    }

    public void setPi() {
        tempNumber = Math.PI;
        strNumber = ""+tempNumber;
        isNewNumber = true;
    }

    public void perc() {
        tempNumber = tempNumber / 100 * lastNumber;
        strNumber = "" + tempNumber;
        isNewNumber = true;
    }
}
