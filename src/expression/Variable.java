package expression;

import expression.generic.MyNumber;

public class Variable<T> extends CommonExpression<T> {
    private String var;
    private MyNumber<T> type;

    public Variable(String var, MyNumber<T> type) {
        this.var = var;
        this.type = type;
    }

    /*
    @Override
    public int evaluate(int x) {
        return x;
    }
    */

    @Override
    public T evaluate(T x, T y, T z) {
        if (var.equals("x")) {
            return x;
        } else if (var.equals("y")) {
            return y;
        } else {
            return z;
        }
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public String toMiniString() {
        return this.toString();
    }

    @Override
    public int hashCode() {
        return var.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Variable other = (Variable) obj;
        return var.equals(other.var);
    }

    @Override
    public int priority() {
        return 3;
    }

}
