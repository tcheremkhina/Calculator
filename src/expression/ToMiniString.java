package expression;

public interface ToMiniString<T> {
    default String toMiniString() {
        return toString();
    }
}
