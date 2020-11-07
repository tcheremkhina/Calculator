package expression;

import expression.generic.MyNumber;

public class Const<T> extends CommonExpression<T> {
    private T val;
    MyNumber<T> type;

    public Const(MyNumber<T> type) {
        this.val = type.zero();
        this.type = type;
    }

    public Const(T val, MyNumber<T> type) {
        this.val = val;
        this.type = type;
    }

    /*
    @Override
    public int evaluate(int x) {
        return val.intValue();
    }
    */

    @Override
    public T evaluate(T x, T y, T z) {
        return val;
    }

    @Override
    public String toString() {
        return this.toString();
    }

    @Override
    public String toMiniString() {
        return this.toString();
    }

    @Override
    public int hashCode() {
        return val.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Const other = (Const) obj;
        return val.equals(other.val);
    }

    @Override
    public int priority() {
        return 3;
    }

}
