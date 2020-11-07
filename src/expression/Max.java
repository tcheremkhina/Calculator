package expression;

import expression.generic.MyNumber;

public class Max<T> extends AbstractOperation<T> {
    public Max(CommonExpression<T> left, CommonExpression<T> right, MyNumber<T> type) {
        super(left, right, type);
    }

    @Override
    public T makeOperation(T left, T right) {
        return type.max(left, right);
    }

    @Override
    public int priority() {
        return 0;
    }

}