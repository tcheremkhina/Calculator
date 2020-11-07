package expression;

import expression.generic.MyNumber;

public class CheckedNegate<T> extends CheckedSubtract<T> {
    public CheckedNegate(CommonExpression<T> right, MyNumber<T> type) {
        super(new Const<T>(type), right, type);
    }

    @Override
    public int priority() {
        return 5;
    }

}
