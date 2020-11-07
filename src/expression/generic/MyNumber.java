package expression.generic;

import expression.CalculateException;

public abstract class MyNumber<T> {

    public abstract T zero();

    public abstract T one();

    public abstract T parseNumber(String str);

    public abstract T parseNumber(Integer x);

    public abstract T add(T x, T y) throws CalculateException;

    public abstract T subtract(T x, T y) throws CalculateException;

    public abstract T multiply(T x, T y) throws CalculateException;

    public abstract T divide(T x, T y) throws CalculateException;

    public abstract T count(T x);

    public abstract String toString(T x);

    public abstract T min(T x, T y);

    public abstract T max(T x, T y);

}
