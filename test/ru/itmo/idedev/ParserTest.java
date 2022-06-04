package ru.itmo.idedev;


import org.junit.jupiter.api.Test;

import ru.itmo.idedev.utils.Parser;
import ru.itmo.idedev.utils.VisitorImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {

    @Test
    void checkPriorityTest() {
        var visitor = new VisitorImpl();

        var str = "x/y+z*y-y*y+x/z";
        var correctStr =
                "BinaryExpr[" +
                    "BinaryExpr[" +
                        "BinaryExpr[" +
                        "BinaryExpr[Variable{x}/Variable{y}]+" +
                        "BinaryExpr[Variable{z}*Variable{y}]]-" +
                        "BinaryExpr[Variable{y}*Variable{y}]]+" +
                        "BinaryExpr[Variable{x}/Variable{z}]]";

        Parser.run(str).receive(visitor);

        assertEquals(correctStr, visitor.getResult());
    }

    @Test
    void checkParenthesisTest() {
        var visitor = new VisitorImpl();

        var str = "(a+b)*(c-d)";

        var correctStr =
                "BinaryExpr[" +
                "Parenthesis(BinaryExpr[Variable{a}+Variable{b}])*" +
                "Parenthesis(BinaryExpr[Variable{c}-Variable{d}])]";

        Parser.run(str).receive(visitor);
        assertEquals(correctStr, visitor.getResult());

    }

    @Test
    void checkComplicatedParenthesisTest() {
        var visitor = new VisitorImpl();

        var str = "((a+(x/y)-z*v+z/v*(d+d)))";

        var correctStr =
            "Parenthesis(" +
                    "Parenthesis(" +
                    "BinaryExpr[" +
                    "BinaryExpr[" +
                    "BinaryExpr[Variable{a}+" +
                    "Parenthesis(" +
                    "BinaryExpr[" +
                    "Variable{x}/Variable{y}])]-" +
                    "BinaryExpr[Variable{z}*Variable{v}]]" +
                    "+BinaryExpr[" +
                    "BinaryExpr[" +
                    "Variable{z}/Variable{v}]*" +
                    "Parenthesis(" +
                    "BinaryExpr[Variable{d}" +
                    "+Variable{d}])]]))";

        Parser.run(str).receive(visitor);
        assertEquals(correctStr, visitor.getResult());
    }

}