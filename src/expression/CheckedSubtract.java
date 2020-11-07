package expression;


import expression.generic.MyNumber;

public class CheckedSubtract<T> extends AbstractOperation<T> {
    public CheckedSubtract(CommonExpression<T> left, CommonExpression<T> right, MyNumber<T> type) {
        super(left, right, type);
    }
    
    @Override
    public T makeOperation(T left, T right) {
        try {
            return type.subtract(left, right);
        } catch (CalculateException e) {
            return null;
        }
        /*
        if (left < 0 && right > 0 && Integer.MIN_VALUE + right > left) {
            throw new OverflowExpressionException("overflow");
        }
        if (left >= 0 && right < 0 && Integer.MAX_VALUE + right < left) {
            throw new OverflowExpressionException("overflow" + " " + left + " " + right);
        }
        return left - right;
         */
    }

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public String operator() {
        return "-";
    }

}
