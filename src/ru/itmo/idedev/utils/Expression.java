package ru.itmo.idedev.utils;


public interface Expression {
    void receive(Visitor visitor);
}