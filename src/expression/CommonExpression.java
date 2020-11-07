package expression;

public abstract class CommonExpression<T> implements TripleExpression<T> {
    final int prime = 31;

    public int priority() {
        return 0;
    }

    public String operator() {
        return "";
    }

}
