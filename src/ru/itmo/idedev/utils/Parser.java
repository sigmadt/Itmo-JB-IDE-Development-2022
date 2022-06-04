package ru.itmo.idedev.utils;


import java.util.Map;
import java.util.Stack;

public class Parser {
    private static final Map<Character, Integer> priorities =
            Map.of('(', 0,
                    '+', 1,
                    '-', 1,
                    '/', 2,
                    '*', 2);

    private static final Character LEFT_PAR = '(';
    private static final Character RIGHT_PAR = ')';


    private static boolean checkOperationStack(Stack<Character> opers, boolean left) {
        return left ?
                opers.size() > 0 && opers.peek() == LEFT_PAR :
                opers.size() > 0 && opers.peek() != LEFT_PAR;
    }

    private static boolean checkPriority(Character givenSymb, Character currSymb) {
        return priorities.get(givenSymb) <= priorities.get(currSymb);

    }

    private static Expression constructBinaryExpression(Stack<Character> opers, Stack<Expression> exprs) {
        if (opers.size() < 1 || exprs.size() < 2) {
            throw new IllegalArgumentException("Binary expression can not be constructed");
        }

        var right = exprs.pop();
        var operator = opers.pop();
        var left = exprs.pop();

        return new BinaryExpression(left, right, operator.toString());


    }



    public static Expression run(String givenString) {
        var currOperators = new Stack<Character>();
        var currExpressions = new Stack<Expression>();

        for (var symb : givenString.toCharArray()) {
            if (Character.isDigit(symb)) {
                currExpressions.push(new Literal(String.valueOf(symb)));
            } else if (Character.isLetter(symb)) {
                currExpressions.push(new Variable(String.valueOf(symb)));
            } else if (symb == LEFT_PAR) {
                currOperators.push(symb);
            } else if (symb == RIGHT_PAR) {
                while (checkOperationStack(currOperators, false)) {
                    currExpressions.push(constructBinaryExpression(currOperators, currExpressions));
                }

                if (!checkOperationStack(currOperators, true)) {
                    throw new IllegalArgumentException("Given strung can not be parsed :(");
                }

                currOperators.pop();
                currExpressions.push(new ParenthesisExpression(currExpressions.pop()));

            }

            else if (priorities.containsKey(symb)) {
                var currPriority = priorities.get(symb);

                while (!currOperators.isEmpty() && checkPriority(symb, currOperators.peek())) {
                    currExpressions.push(constructBinaryExpression(currOperators, currExpressions));
                }

                currOperators.push(symb);

            }
            else {
                throw new IllegalArgumentException("Given strung can not be parsed :(");
            }
        }

        while (!currOperators.isEmpty()) {
            currExpressions.push(constructBinaryExpression(currOperators, currExpressions));
        }

        if (currExpressions.size() != 1) {
            throw new IllegalArgumentException();
        }

        return currExpressions.pop();
    }
}


