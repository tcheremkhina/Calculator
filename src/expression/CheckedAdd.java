package expression;

import expression.generic.MyNumber;

public class CheckedAdd<T> extends AbstractOperation<T> {

    public CheckedAdd(CommonExpression<T> left, CommonExpression<T> right, MyNumber<T> type) {
        super(left, right, type);
    }

    @Override
    public T makeOperation(T left, T right) {
        try {
            return type.add(left, right);
        } catch (CalculateException e) {
            return null;
        }
    }

    @Override
    public String operator() {
        return "+";
    }

    @Override
    public int priority() {
        return 1;
    }

}
