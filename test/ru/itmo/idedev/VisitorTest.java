package ru.itmo.idedev;


import org.junit.jupiter.api.Test;

import ru.itmo.idedev.utils.Parser;
import ru.itmo.idedev.utils.VisitorImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class VisitorTest {

    @Test
    void simpleTestAdd() {
        var visitor = new VisitorImpl();

        Parser.run("2+4").receive(visitor);
        assertEquals("BinaryExpr[Literal{2}+Literal{4}]", visitor.getResult());

    }

    @Test
    void simpleTestSub() {
        var visitor = new VisitorImpl();
        Parser.run("3-9");


        Parser.run("3-9").receive(visitor);
        assertEquals("BinaryExpr[Literal{3}-Literal{9}]", visitor.getResult());

    }

    @Test
    void simpleTestDivVar() {
        var visitor = new VisitorImpl();

        Parser.run("7/x").receive(visitor);
        assertEquals("BinaryExpr[Literal{7}/Variable{x}]", visitor.getResult());

    }
}