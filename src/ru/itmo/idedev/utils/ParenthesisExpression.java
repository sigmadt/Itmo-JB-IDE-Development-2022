package ru.itmo.idedev.utils;


public class ParenthesisExpression implements Expression {
    public Expression operand;

    public ParenthesisExpression(Expression operand) {
        this.operand = operand;
    }

    @Override
    public void receive(Visitor visitor) {
        visitor.run(this);
    }

}