package expression;

import expression.parser.ExpressionParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Please, write your expression: ");
        String toParse = in.nextLine();
        TripleExpression parsed = new ExpressionParser().parse(toParse);
        System.out.println("To string: " + parsed);
        System.out.println("To mini string: " + parsed.toMiniString());
        System.out.print("Please, write 3 evaluate parameters: ");
        int x = in.nextInt();
        int y = in.nextInt();
        int z = in.nextInt();
        System.out.println("Evaluate: " + parsed.evaluate(x, y, z));
    }
}