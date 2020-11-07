package expression;

import expression.generic.MyNumber;

public class CheckedMultiply<T> extends AbstractOperation<T> {
    public CheckedMultiply(CommonExpression<T> left, CommonExpression<T> right, MyNumber<T> type) {
        super(left, right, type);
    }

    @Override
    public T makeOperation(T left, T right) {
        try {
            return type.multiply(left, right);
        } catch (CalculateException e) {
            return null;
        }
        /*
        if (left == 0 || right == 0) {
            return 0;
        }
        if (right == Integer.MIN_VALUE || (left != Integer.MIN_VALUE && Abs.getAbs(left) < Abs.getAbs(right))) {
            int tmp = left;
            left = right;
            right = tmp;
        }
        if (left == Integer.MIN_VALUE) {
            if (right != 1) {
                throw new OverflowExpressionException("overflow");
            }
            return left * right;
        }
        if (((left > 0) == (right > 0) && Integer.MAX_VALUE / Abs.getAbs(left) < Abs.getAbs(right))) {
            throw new OverflowExpressionException("overflow");
        }
        if ((left > 0) != (right > 0) && !(Integer.MAX_VALUE / Abs.getAbs(left) >= Abs.getAbs(right) ||
                Integer.MIN_VALUE / Abs.getAbs(left) == -Abs.getAbs(right) && right * left == Integer.MIN_VALUE)) {
            throw new OverflowExpressionException("overflow");
        }
        return left * right;
        }
         */
    }

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public String operator() {
        return "*";
    }
}
