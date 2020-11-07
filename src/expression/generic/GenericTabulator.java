package expression.generic;
import expression.parser.ExpressionParser;
import expression.parser.Parser;

import java.math.BigInteger;


public class GenericTabulator implements Tabulator {
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        Object[][][] res = new Object[x2 -x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int i = 0; x1 + i <= x2; i++) {
            for (int j = 0; y1 + j <= y2; j++) {
                for (int k = 0; z1 + k <= z2; k++) {
                    switch (mode) {
                        case "i": {
                            Parser<Integer> a = new ExpressionParser<>(new IntegerNumber<Integer>());
                            res[i][j][k] = a.parse(expression).evaluate(x1 + i, y1 + j, z1 + k);
                            break;
                        }
                        case "d": {
                            Parser<Double> a = new ExpressionParser<>(new DoubleNumber<Double>());
                            res[i][j][k] = a.parse(expression).evaluate((double) (x1 + i), (double) (y1 + j), (double) (z1 + k));
                            break;
                        }
                        case "bi": {
                            Parser<BigInteger> a = new ExpressionParser<>(new BigIntegerNumber<BigInteger>());
                            res[i][j][k] = a.parse(expression).evaluate(BigInteger.valueOf(x1 + i), BigInteger.valueOf(y1 + j), BigInteger.valueOf(z1 + k));
                            break;
                        }
                        case "u": {
                            Parser<Integer> a = new ExpressionParser<>(new UncheckedNumber<Integer>());
                            res[i][j][k] = a.parse(expression).evaluate(x1 + i, y1 + j, z1 + k);
                            break;
                        }
                        case "s": {
                            Parser<Short> a = new ExpressionParser<>(new ShortNumber<Short>());
                            res[i][j][k] = a.parse(expression).evaluate((short) (x1 + i), (short) (y1 + j), (short) (z1 + k));
                            break;
                        }
                        case "l": {
                            Parser<Long> a = new ExpressionParser<>(new LongNumber<Long>());
                            res[i][j][k] = a.parse(expression).evaluate((long) (x1 + i), (long) (y1 + j), (long) (z1 + k));
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }
}
