package expression.generic;

import expression.CalculateException;
import expression.DBZExpressionException;
import expression.OverflowExpressionException;

public class IntegerNumber<T> extends MyNumber<Integer> {

    public IntegerNumber() {};

    @Override
    public Integer zero() {
        return 0;
    }

    @Override
    public Integer one() {
        return 1;
    }

    @Override
    public Integer parseNumber(Integer x) {
        return x;
    }

    @Override
    public Integer count(Integer x) {
        if (x == null) {
            return null;
        }
        return Integer.bitCount(x);
    }

    @Override
    public Integer parseNumber(String str) {
        return Integer.parseInt(str);
    }

    @Override
    public Integer add(Integer left, Integer right) throws CalculateException {
        if (left == null || right == null) {
            return null;
        }
        if (left > 0 && right > 0 && Integer.MAX_VALUE - left < right) {
            throw new OverflowExpressionException("overflow");
        }
        if (left < 0 && right < 0 && Integer.MIN_VALUE - left > right) {
            throw new OverflowExpressionException("overflow");
        }
        return left + right;
    }

    @Override
    public Integer subtract(Integer x, Integer y) throws CalculateException {
        if (x == null || y == null) {
            return null;
        }
        if (x < 0 && y > 0 && Integer.MIN_VALUE + y > x) {
            throw new OverflowExpressionException("overflow");
        }
        if (x >= 0 && y < 0 && Integer.MAX_VALUE + y < x) {
            throw new OverflowExpressionException("overflow" + " " + x + " " + y);
        }
        return x - y;
    }

    private Integer getAbs(Integer a) {
        if (a < 0) {
            return -a;
        }
        return a;
    }

    @Override
    public Integer multiply(Integer x, Integer y) throws CalculateException {
        if (x == null || y == null) {
            return null;
        }
        if (x == 0 || y == 0) {
            return 0;
        }
        if (x == Integer.MIN_VALUE) {
            if (y != 1) {
                throw new OverflowExpressionException("overflow");
            }
            return x;
        }
        if (y == Integer.MIN_VALUE || (x != Integer.MIN_VALUE && getAbs(x) < getAbs(y))) {
            Integer tmp = x;
            x = y;
            y = tmp;
        }
        if (((x > 0) == (y > 0) && Integer.MAX_VALUE / getAbs(x) < getAbs(y))) {
            throw new OverflowExpressionException("overflow");
        }
        if ((x > 0) != (y > 0) && !(Integer.MAX_VALUE / getAbs(x) >= getAbs(y) ||
                Integer.MIN_VALUE / getAbs(x) == -getAbs(y) && y * x == Integer.MIN_VALUE)) {
            throw new OverflowExpressionException("overflow");
        }
        return x * y;
    }

    @Override
    public Integer divide(Integer x, Integer y) throws CalculateException{
        if (x == null || y == null) {
            return null;
        }
        if (y == 0) {
            throw new DBZExpressionException("division by zero");
        }
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowExpressionException("overflow");
        }
        return x / y;
    }

    @Override
    public String toString(Integer x) {
        return x.toString();
    }

    @Override
    public Integer min(Integer x, Integer y) {
        if (x == null || y == null) {
            return null;
        }
        return Integer.min(x, y);
    }

    @Override
    public Integer max(Integer x, Integer y) {
        if (x == null || y == null) {
            return null;
        }
        return Integer.max(x, y);
    }

}
