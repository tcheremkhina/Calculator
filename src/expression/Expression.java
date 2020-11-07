package expression;

public interface Expression extends ToMiniString {
    int evaluate(int val) throws CalculateException;
}
