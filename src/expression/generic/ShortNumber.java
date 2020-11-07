package expression.generic;

import expression.CalculateException;
import expression.DBZExpressionException;

public class ShortNumber<T> extends MyNumber<Short> {

    public ShortNumber() {};

    @Override
    public Short one() {
        return 1;
    }

    @Override
    public Short min(Short x, Short y) {
        return (short)Integer.min(x, y);
    }

    @Override
    public Short max(Short x, Short y) {
        return (short)Integer.max(x, y);
    }

    @Override
    public Short zero() {
        return 0;
    }

    @Override
    public Short count(Short x) {
        if (x >= 0) {
            return (short)Integer.bitCount(x);
        }
        return (short)(Integer.bitCount(x) - Short.SIZE);
    }

    @Override
    public Short parseNumber(String str) {
        return Short.parseShort(str);
    }

    @Override
    public Short add(Short left, Short right) {
        return (short)(left + right);
    }

    @Override
    public Short subtract(Short x, Short y) {
        return (short)(x - y);
    }

    @Override
    public Short multiply(Short x, Short y) {
        return (short)(x * y);
    }

    @Override
    public Short divide(Short x, Short y) throws CalculateException {
        if (y == 0) {
            throw new DBZExpressionException("division by zero");
        }
        return (short)(x / y);
    }

    @Override
    public String toString(Short x) {
        return x.toString();
    }

    @Override
    public Short parseNumber(Integer x) {
        return x.shortValue();
    }

}
