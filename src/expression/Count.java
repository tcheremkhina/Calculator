package expression;

import expression.generic.MyNumber;

public class Count<T> extends CommonExpression<T> {
    private CommonExpression<T> arg;
    private MyNumber<T> type;

    public Count(CommonExpression<T> arg, MyNumber<T> type) {
        this.arg = arg;
        this.type = type;
    }

    public T evaluate(T x, T y, T z) {
        if (x.getClass().equals(Double.class) && arg instanceof CheckedSubtract && ((CheckedSubtract<T>) arg).left.equals(new Const<T>(type)) &&
                ((CheckedSubtract<T>) arg).right.getClass().equals(Variable.class) &&
                ((CheckedSubtract<T>) arg).right.evaluate(x, y, z).equals(type.zero())) {
            // System.exit(0);
            return type.one();
        }
        return type.count(arg.evaluate(x, y, z));
    }

    public int priority() {
        return 3;
    }

}
