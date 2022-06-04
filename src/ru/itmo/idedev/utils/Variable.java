package ru.itmo.idedev.utils;


public class Variable implements Expression {
    public String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public void receive(Visitor visitor) {
        visitor.run(this);
    }

}