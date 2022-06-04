package ru.itmo.idedev.utils;


public interface Visitor {
    void run(Literal literal);
    void run(Variable variable);
    void run(BinaryExpression binaryExpression);
    void run(ParenthesisExpression parenthesisExpression);
}


