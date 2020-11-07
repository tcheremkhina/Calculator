package expression;

import expression.generic.IntegerNumber;
import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) throws Exception {
        // Example of using:
        ExpressionParser<Integer> parser = new ExpressionParser<>(new IntegerNumber<>());
        System.out.println(parser.parse("x + y * z").evaluate(2, 2, 2)); // 6
    }
}
