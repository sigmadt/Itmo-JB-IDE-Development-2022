package ru.itmo.idedev.utils;

public class VisitorImpl implements Visitor {
    private StringBuilder result = new StringBuilder();


    @Override
    public void run(Literal literal) {
        // trivial wrap;
        result.append(String.format("Literal{%s}", literal.content));
    }

    @Override
    public void run(Variable variable) {
        // trivial wrap;
        result.append(String.format("Variable{%s}", variable.name));
    }

    @Override
    public void run(BinaryExpression binaryExpression) {
        // pre order traversal of bin expr
        result.append("BinaryExpr[");
        // left
        binaryExpression.left.receive(this);
        result.append(binaryExpression.operator);
        //right
        binaryExpression.right.receive(this);

        result.append("]");
    }

    @Override
    public void run(ParenthesisExpression parenthesisExpression) {
        // pre order traversal of bin expr
        result.append("Parenthesis(");

        parenthesisExpression.operand.receive(this);

        result.append(")");
    }

    public String getResult() {
        return result.toString();
    }

}
