package expression;

import expression.generic.MyNumber;

public class Min<T> extends AbstractOperation<T> {
    public Min(CommonExpression<T> left, CommonExpression<T> right, MyNumber<T> type) {
        super(left, right, type);
    }

    @Override
    public T makeOperation(T left, T right) {
        return type.min(left, right);
    }

    @Override
    public int priority() {
        return 0;
    }

}
