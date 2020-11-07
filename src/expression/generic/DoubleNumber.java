package expression.generic;


public class DoubleNumber<T> extends MyNumber<Double> {
    public DoubleNumber() {};

    @Override
    public Double zero() {
        return 0.0;
    }

    @Override
    public Double one() {
        return 1.0;
    }

    @Override
    public Double parseNumber(Integer x) {
        return (double)x;
    }

    @Override
    public Double parseNumber(String str) {
        return Double.parseDouble(str);
    }

    @Override
    public Double add(Double x, Double y) {
        return x + y;
    }

    @Override
    public Double subtract(Double x, Double y) {
        return x - y;
    }

    @Override
    public Double multiply(Double x, Double y) {
        return x * y;
    }

    @Override
    public Double divide(Double x, Double y) {
        return x / y;
    }

    @Override
    public String toString(Double x) {
        return x.toString();
    }

    @Override
    public Double count(Double x) {
        return (double)(Long.bitCount(Double.doubleToLongBits(x)));
    }

    @Override
    public Double min(Double x, Double y) {
        return Double.min(x, y);
    }

    @Override
    public  Double max(Double x, Double y) {
        return Double.max(x, y);
    }

}
