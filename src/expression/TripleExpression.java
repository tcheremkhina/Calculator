package expression;

public interface TripleExpression<T> extends ToMiniString<T> {
    T evaluate(T x, T y, T z);
}