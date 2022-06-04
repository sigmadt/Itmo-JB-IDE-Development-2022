package ru.itmo.idedev.utils;


public class BinaryExpression implements Expression {
    public Expression left;
    public Expression right;
    public String operator;

    public BinaryExpression(Expression left, Expression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public void receive(Visitor visitor) {
        visitor.run(this);
    }

}