package expression.parser;

import expression.ExpressionException;

public class BaseParser {
    private final ExpressionSource source;
    protected char ch;
    protected int pos;

    protected int getPos() {
        return pos;
    }

    protected BaseParser(final ExpressionSource source) {
        this.source = source;
    }

    protected BaseParser(final String str) {
        this.source = new StringSource(str.toLowerCase());
    }

    protected void nextChar() {
        ch = source.hasNext() ? source.next() : '\0';
        pos++;
    }

    protected boolean test(char expected) {
        if (ch == expected) {
            nextChar();
            return true;
        }
        return false;
    }

    protected void expect(final char c) throws ExpressionException {
        if (ch != c) {
            throw error("Expected '" + c + "', found '" + ch );
        }
        nextChar();
    }

    protected void expect(final String value) throws ExpressionException {
        for (char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected ExpressionException error(final String message) {
        return source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }
}
