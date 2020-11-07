package expression.generic;

import expression.CalculateException;
import expression.DBZExpressionException;

public class LongNumber<T> extends MyNumber<Long> {

    public LongNumber() {};

    @Override
    public Long zero() {
        return 0L;
    }

    @Override
    public Long one() {
        return 1L;
    }

    @Override
    public Long parseNumber(String str) {
        return Long.parseLong(str);
    }

    @Override
    public Long parseNumber(Integer x) {
        return Long.valueOf(x);
    }

    @Override
    public Long add(Long left, Long right) {
        return left + right;
    }

    @Override
    public Long subtract(Long x, Long y) {
        return x - y;
    }

    @Override
    public Long multiply(Long x, Long y) {
        return x * y;
    }

    @Override
    public Long divide(Long x, Long y) throws CalculateException {
        if (y == 0) {
            throw new DBZExpressionException("division by zero");
        }
        return x / y;
    }

    @Override
    public Long count(Long x) {
        return (long)Long.bitCount(x);
    }

    @Override
    public String toString(Long x) {
        return x.toString();
    }

    @Override
    public Long min(Long x, Long y) {
        return Long.min(x, y);
    }

    @Override
    public Long max(Long x, Long y) {
        return Long.max(x, y);
    }

}
