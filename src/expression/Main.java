package expression;

import expression.generic.DoubleNumber;
import expression.generic.GenericTabulator;
import expression.generic.IntegerNumber;
import expression.generic.Tabulator;
import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) throws Exception {
        // Example of using:
        ExpressionParser<Integer> parser = new ExpressionParser<>(new IntegerNumber<>());
        System.out.println(parser.parse("x + y * z").evaluate(2, 2, 2)); // 6

        // Example of using for double:
        ExpressionParser<Double> doubleParser = new ExpressionParser<>(new DoubleNumber<>());
        System.out.println(doubleParser.parse("x + y * z").evaluate(2.2, 2.2, 2.2)); // 7.040000000000001
    }
}
