package expression.parser;

import expression.*;
import expression.generic.MyNumber;

import java.util.HashMap;
import java.util.Map;

public class ExpressionParser<T> extends BaseParser implements Parser<T> {

    private final MyNumber<T> type;

    public ExpressionParser(ExpressionSource source, MyNumber<T> type) {
        super(source);
        this.type = type;
        nextChar();
    }

    public ExpressionParser(MyNumber<T> type) {
        super(new StringSource(""));
        this.type = type;
    }

    public TripleExpression<T> parse(final String str) throws Exception {
        return parse(new StringSource(str));
    }

    public TripleExpression<T> parse(final ExpressionSource source) throws Exception {
        return new ExpressionParser<T>(source, type).parse();
    }

    public TripleExpression<T> parse() throws Exception {
        setBalance(0);
        final TripleExpression<T> result = parseExpression();
        if (getBalance() != 0) {
            throw new InvalidExpressionException(makeMessage("No closing parenthesis"));
        }
        if (test('\0')) {
            return result;
        }
        throw new NoEndExpressionException(makeMessage("End of Expression expected"));
    }

    private CommonExpression<T> AddMember(TripleExpression<T> expr, AbstractOperation<T> newExpr) {
        if (expr instanceof AbstractOperation && newExpr.priority() > ((AbstractOperation<T>) expr).priority() &&
                !((AbstractOperation<T>) expr).getInBrackets()) {
            newExpr.setLeft(((AbstractOperation<T>) expr).getRight());
            ((AbstractOperation<T>) expr).setRight(AddMember(((AbstractOperation<T>) expr).getRight(), newExpr));
            return (CommonExpression<T>) expr;
        } else {
            newExpr.setLeft(((CommonExpression<T>) expr));
            return newExpr;
        }
    }

    private int balance = 0;

    private void incBalance() {
        balance++;
    }

    private void decBalance() {
        balance--;
    }

    private int getBalance() {
        return balance;
    }

    private void setBalance(int x) {
        balance = x;
    }

    private TripleExpression<T> parseExpression() throws Exception {
        skipWhitespace();
        TripleExpression<T> next = new Const<T>(type);
        boolean flag = false;
        while (!test('\0')) {
            if (test(')')) {
                decBalance();
                if (getBalance() < 0) {
                    throw new InvalidExpressionException(makeMessage("No opening parenthesis"));
                }
                if (next instanceof AbstractOperation) {
                    ((AbstractOperation<T>) next).setInBrackets(true);
                }
                break;
            }
            flag = true;
            next = nextMember();
            if (test('\0')) {
                return next;
            }
            if (test(')')) {
                decBalance();
                if (getBalance() < 0) {
                    throw new InvalidExpressionException(makeMessage("No opening parenthesis"));
                }
                if (next instanceof AbstractOperation) {
                    ((AbstractOperation<T>) next).setInBrackets(true);
                }
                return next;
            }
            boolean ok = false;
            skipWhitespace();
            while (operator()) {
                ok = true;
                if (test('i')) {
                    expect('n');
                    AbstractOperation<T> newExpr = new Min<T>(new Const<T>(type), (CommonExpression<T>) nextMember(), type);
                    next = AddMember(next, newExpr);
                } else if (test('a')) {
                    expect('x');
                    AbstractOperation<T> newExpr = new Max<T>(new Const<T>(type), (CommonExpression<T>) nextMember(), type);
                    next = AddMember(next, newExpr);
                } else if (test('+')) {
                    AbstractOperation<T> newExpr = new CheckedAdd<T>(new Const<T>(type), (CommonExpression<T>) nextMember(), type);
                    next = AddMember(next, newExpr);
                } else if (test('-')) {
                    AbstractOperation<T> newExpr = new CheckedSubtract<T>(new Const<T>(type), (CommonExpression<T>) nextMember(), type);
                    next = AddMember(next, newExpr);
                } else if (test('*')) {
                    AbstractOperation<T> newExpr = new CheckedMultiply<T>(new Const<T>(type), (CommonExpression<T>) nextMember(), type);
                    next = AddMember(next, newExpr);
                } else if (test('/')) {
                    AbstractOperation<T> newExpr;
                    newExpr = new CheckedDivide<T>(new Const<T>(type), (CommonExpression<T>) nextMember(), type);
                    next = AddMember(next, newExpr);
                }
            }
            if (!ok) {
                throw new NoOperatorExpressionException(makeMessage("Operator expected"));
            }
        }
        if (!flag) {
            throw new NoNextMemberExpressionException(makeMessage("Next Member expected"));
        }
        return next;
    }

    private Map<Character, String> vars = new HashMap<>();

    private TripleExpression<T> nextMember() throws Exception {
        skipWhitespace();
        StringBuilder sb = new StringBuilder("");
        while (between('a', 'z')) {
            sb.append(ch);
            nextChar();
        }
        if (sb.toString().equals("count")) {
            return new Count<T>((CommonExpression<T>) nextMember(), type);
        }
        if (sb.length() == 1 && (sb.toString().equals("x") || sb.toString().equals("y") ||
                sb.toString().equals("z")) || sb.length() >= 2) {
            if (sb.toString().equals("x")) {
                vars.put('x', "x");
            } else if (sb.toString().equals("y")) {
                vars.put('y', "y");
            } else if (sb.toString().equals("z")) {
                vars.put('z', "z");
            } else if (vars.containsKey('x') && vars.get('x').equals(sb.toString())) {
                sb = new StringBuilder("x");
            } else if (vars.containsKey('y') && vars.get('y').equals(sb.toString())) {
                sb = new StringBuilder("y");
            } else if (vars.containsKey('z') && vars.get('z').equals(sb.toString())) {
                sb = new StringBuilder("z");
            } else if (!vars.containsKey('x')) {
                vars.put('x', sb.toString());
                sb = new StringBuilder("x");
            } else if (!vars.containsKey('y')) {
                vars.put('y', sb.toString());
                sb = new StringBuilder("y");
            } else if (!vars.containsKey('z')) {
                vars.put('z', sb.toString());
                sb = new StringBuilder("z");
            } else {
                throw new InvalidExpressionException("More than 3 variables");
            }
            return new Variable<T>(sb.toString(), type);
        } else if (test('-')) {
            sb.append('-');
            AbstractOperation<T> tmp;
            if (between('0', '9')) {
                copyInteger(sb);
                tmp = new CheckedAdd<T>(new Const<T>(type), new Const<T>(type.parseNumber(sb.toString()), type), type);
            } else {
                tmp = new CheckedSubtract<T>(new Const<T>(type), (CommonExpression<T>) nextMember(), type);
                tmp.setInBrackets(true);
            }
            return tmp;
        } else if (between('0', '9')) {
            copyInteger(sb);
            return new Const<T>(type.parseNumber(sb.toString()), type);
        } else if (test('(')) {
            incBalance();
            return parseExpression();
        }
        throw new InvalidExpressionException(makeMessage("No argument"));
    }

    private boolean operator() throws Exception {
        skipWhitespace();
        if (between('+', '+') ||
                between('-', '-') ||
                between('*', '*') ||
                between('/', '/')) {
            return true;
        }
        if (test('m')) {
            return true;
        }
        return false;
    }

    private void copyDigits(final StringBuilder sb) {
        skipWhitespace();
        while (between('0', '9')) {
            sb.append(ch);
            nextChar();
        }
    }

    private void copyInteger(final StringBuilder sb) throws Exception {
        if (test('-')) {
            sb.append('-');
        }
        if (test('0')) {
            sb.append('0');
        } else if (between('1', '9')) {
            copyDigits(sb);
        } else {
            throw new InvalidExpressionException(makeMessage("Invalid number"));
        }
        if (sb.charAt(0) == '-') {
            if ((sb.length() > 11) || (sb.length() == 11 && sb.toString().compareTo("-2147483648") > 0)) {
                throw new InvalidExpressionException(makeMessage("Constant overflow"));
            }
        } else if (sb.length() > 10 || (sb.length() == 10 && sb.toString().compareTo("2147483647") > 0)) {
            throw new InvalidExpressionException(makeMessage("Constant overflow"));
        }
    }

    private void skipWhitespace() {
        while (test(' ') || test('\r') || test('\n') || test('\t')) {
            // skip
        }
    }

    private String makeMessage(String str) {
        return str + ", pos = " + getPos();
    }
}
