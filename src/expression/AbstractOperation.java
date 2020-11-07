package expression;

import expression.generic.MyNumber;

public abstract class AbstractOperation<T> extends CommonExpression<T> {
    protected CommonExpression<T> left, right;
    private boolean inBrackets = false;
    protected final MyNumber<T> type;

    public boolean getInBrackets() {
        return inBrackets;
    }

    public void setInBrackets(boolean inBrackets) {
        this.inBrackets = inBrackets;
    }

    public CommonExpression<T> getLeft() {
        return left;
    }

    public CommonExpression<T> getRight() {
        return right;
    }

    public void setLeft(CommonExpression<T> left) {
        this.left = left;
    }

    public void setRight(CommonExpression<T> right) {
        this.right = right;
    }

    public AbstractOperation(CommonExpression<T> left, CommonExpression<T> right, MyNumber<T> type) {
        this.left = left;
        this.right = right;
        this.type = type;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = this.prime * result + left.hashCode();
        result = result * this.prime + right.hashCode();
        result = result * this.prime + operator().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Expression other = (Expression) obj;
        return this.toString().equals(other.toString());
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("(");
        str.append(left.toString());
        str.append(" ");
        str.append(this.operator());
        str.append(" ");
        str.append(right.toString());
        str.append(")");
        return str.toString();
    }

    @Override
    public String toMiniString() {
        StringBuilder str = new StringBuilder();
        if (left.priority() < this.priority()) {
            str.append("(");
        }
        str.append(left.toMiniString());
        if (left.priority() < this.priority()) {
            str.append(")");
        }
        str.append(" ");
        str.append(this.operator());
        str.append(" ");
        boolean flag = (right.priority() < this.priority() || (this.operator().equals("-") && right.priority() == 1) ||
                (this.operator().equals("/") && right.priority() == 2) || (this.operator().equals("*") && right.operator().equals("/")));
        if (flag) {
            str.append("(");
        }
        str.append(right.toMiniString());
        if (flag) {
            str.append(")");
        }
        return str.toString();
    }

    public abstract T makeOperation(T left, T right);

    /*
    @Override
    public int evaluate(int x) throws CalculateException {
        return this.makeOperation(left.evaluate(x), right.evaluate(x));
    }
    */

    @Override
    public T evaluate(T x, T y, T z) {
        return makeOperation(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }
}
