package expression;

import expression.generic.GenericTabulator;
import expression.generic.IntegerNumber;
import expression.generic.Tabulator;
import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) throws Exception {
        // Example of using:
        ExpressionParser<Integer> parser = new ExpressionParser<>(new IntegerNumber<>());
        System.out.println(parser.parse("x + y * z").evaluate(2, 2, 2)); // 6

        //Example of using tabulator:
        Tabulator tabulator = new GenericTabulator();
        int x1 = 1, y1 = 1, z1 = 1, x2 = 2, y2 = 2, z2 = 2;
        Object[][][] arr = tabulator.tabulate("l", "x + y * z", x1, x2, y1, y2, z1, z2);
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                System.out.print("[");
                for (int z = z1; z <= z2; z++) {
                    System.out.print(arr[x - x1][y - y1][z - z1] + " ");
                }
                System.out.println("]");
            }
            System.out.println();
        }
        // Result:

        // [2 3 ]
        // [3 5 ]

        // [3 4 ]
        // [4 6 ]
    }
}
