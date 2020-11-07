package expression;

import expression.generic.MyNumber;

public class CheckedDivide<T> extends AbstractOperation<T> {
    public CheckedDivide(CommonExpression<T> left, CommonExpression<T> right, MyNumber<T> type) {
        super(left, right, type);
    }

    @Override
    public T makeOperation(T left, T right) {
        try {
            return type.divide(left, right);
        } catch (CalculateException e) {
            return null;
        }
        /*
        if (right == 0) {
            throw new DBZExpressionException("division by zero");
        }
        if (left == Integer.MIN_VALUE && right == -1) {
            throw new OverflowExpressionException("overflow");
        }
        return left / right;
        */
    }

    @Override
    public String operator() {
        return "/";
    }

    @Override
    public int priority() {
        return 2;
    }

}
