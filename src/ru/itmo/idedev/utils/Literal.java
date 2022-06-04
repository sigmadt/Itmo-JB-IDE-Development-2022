package ru.itmo.idedev.utils;


public class Literal implements Expression {
    public String content;

    public Literal(String name) {
        this.content = name;
    }

    @Override
    public void receive(Visitor visitor) {
        visitor.run(this);
    }

}