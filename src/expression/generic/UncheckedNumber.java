package expression.generic;

import expression.CalculateException;
import expression.DBZExpressionException;

public class UncheckedNumber<T> extends MyNumber<Integer> {

    public UncheckedNumber() {};

    @Override
    public Integer one() {
        return 1;
    }

    @Override
    public Integer min(Integer x, Integer y) {
        return Integer.min(x, y);
    }

    @Override
    public Integer max(Integer x, Integer y) {
        return Integer.max(x, y);
    }

    @Override
    public Integer count(Integer x) {
        return Integer.bitCount(x);
    }

    @Override
    public Integer zero() {
        return 0;
    }

    @Override
    public Integer parseNumber(String str) {
        return Integer.parseInt(str);
    }

    @Override
    public Integer add(Integer left, Integer right) {
        return left + right;
    }

    @Override
    public Integer subtract(Integer x, Integer y) {
        return x - y;
    }

    @Override
    public Integer multiply(Integer x, Integer y) {
        return x * y;
    }

    @Override
    public Integer divide(Integer x, Integer y) throws CalculateException {
        if (y == 0) {
            throw new DBZExpressionException("division by zero");
        }
        return x / y;
    }

    @Override
    public String toString(Integer x) {
        return x.toString();
    }

    @Override
    public Integer parseNumber(Integer x) {
        return x;
    }

}
