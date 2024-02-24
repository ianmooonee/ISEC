package pt.isec.pa.calculator.model.data;

public enum Operator {
    ADD("+"), SUB("-"), MUL("*"), DIV("/"), CAL("="), NONE("");

    String strOp;

    Operator(String strOp) {
        this.strOp = strOp;
    }

    public static Operator getOp(String str) {
        for (Operator op: Operator.values())
            if (op.strOp.equals(str))
                return op;
        return NONE;
    }
}
