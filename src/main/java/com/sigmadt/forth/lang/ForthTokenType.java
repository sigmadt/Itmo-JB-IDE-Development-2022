package com.sigmadt.forth.lang;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.NonNls;

import java.util.Arrays;
import java.util.List;


public class ForthTokenType extends IElementType {
    private static ForthTokenType TRUE = new ForthTokenType("TRUE"); // true (-1)
    private static ForthTokenType FALSE = new ForthTokenType("FALSE"); // false (0)

    private static ForthTokenType DOT = new ForthTokenType("DOT"); // .
    private static ForthTokenType STACK = new ForthTokenType("STACK"); // .s

    private static ForthTokenType SEMICOLON = new ForthTokenType("SEMICOLON"); // ;
    private static ForthTokenType DOUBLE_COLON = new ForthTokenType("DOUBLE_COLON"); // :

    private static ForthTokenType COMPILE_MODE = new ForthTokenType("COMPILE_MODE"); // block : ______ ;

    private static ForthTokenType EQUAL = new ForthTokenType("EQUAL"); // =
    private static ForthTokenType LESS = new ForthTokenType("LESS"); // <
    private static ForthTokenType GREATER = new ForthTokenType("GREATER"); // >


    private static ForthTokenType OR = new ForthTokenType("OR"); // or
    private static ForthTokenType AND = new ForthTokenType("AND"); // and

    private static ForthTokenType IF = new ForthTokenType("IF"); // if
    private static ForthTokenType ELSE = new ForthTokenType("ELSE"); // else
    private static ForthTokenType THEN = new ForthTokenType("THEN"); // then

    private static ForthTokenType DO = new ForthTokenType("DO"); // do
    private static ForthTokenType LOOP = new ForthTokenType("LOOP"); // loop
    private static ForthTokenType BEGIN = new ForthTokenType("BEGIN"); // begin
    private static ForthTokenType UNTIL = new ForthTokenType("UNTIL"); // until

    private static ForthTokenType ADD = new ForthTokenType("ADD"); // +
    private static ForthTokenType SUB = new ForthTokenType("SUB"); // -
    private static ForthTokenType MUL = new ForthTokenType("MUL"); // *
    private static ForthTokenType DIV = new ForthTokenType("DIV"); // /
    private static ForthTokenType MOD = new ForthTokenType("MOD"); // mod
    private static ForthTokenType DIV_AND_MOD = new ForthTokenType("DIV_AND_MOD"); // /mod

    private static ForthTokenType EMIT = new ForthTokenType("EMIT"); // emit

    private static ForthTokenType SWAP = new ForthTokenType("SWAP"); // swap
    private static ForthTokenType DUP = new ForthTokenType("DUP"); // dup
    private static ForthTokenType ROT = new ForthTokenType("ROT"); // rot

    private static ForthTokenType OVER = new ForthTokenType("OVER"); // over
    private static ForthTokenType DROP = new ForthTokenType("DROP"); // drop

    private static ForthTokenType IDENTIFIER = new ForthTokenType("IDENTIFIER"); // IDENTIFIER
    private static ForthTokenType CONST = new ForthTokenType("CONST"); // constant
    private static ForthTokenType KEY = new ForthTokenType("KEY"); // key

    private static ForthTokenType SPACE = new ForthTokenType("SPACE"); // ' '
    private static ForthTokenType NEW_LINE = new ForthTokenType("NEW_LINE"); // '\n'
    private static ForthTokenType COMMENT = new ForthTokenType("COMMENT"); // '( -- )'

    private static ForthTokenType STRING = new ForthTokenType("STRING"); // ." "
    private static ForthTokenType NUMBER = new ForthTokenType("NUMBER"); // 17

    public static TokenSet KEYWORDS =
            TokenSet.create(
            DOUBLE_COLON, SEMICOLON,
            IF, ELSE, THEN,
            DO, LOOP, BEGIN, UNTIL,
            CONST, KEY, IDENTIFIER

            );

    public static TokenSet STACK_OPERATIONS =
            TokenSet.create(
                    DOT, STACK, SWAP,
                    DUP, ROT, OVER,
                    DROP
            );

    public static TokenSet OPERATORS =
            TokenSet.create(
                    ADD, SUB, MUL, DIV, MOD, DIV_AND_MOD,
                    EQUAL, LESS, GREATER,
                    EMIT, AND, OR
            );

    public static TokenSet IDENTS =
            TokenSet.create(SPACE, NEW_LINE);

    public static TokenSet COMMENTS = TokenSet.create(COMMENT);

    public static TokenSet NUMBERS = TokenSet.create(NUMBER);

    public static TokenSet STRINGS = TokenSet.create(STRING);

    public static TokenSet CONSTANTS = TokenSet.create(TRUE, FALSE);

    public static ForthTokenType getDoubleColon() {
        return DOUBLE_COLON;
    }

    public static ForthTokenType getCompileMode() {
        return COMPILE_MODE;
    }

    public static ForthTokenType getDivAndMod() {
        return DIV_AND_MOD;
    }

    public static ForthTokenType getSTRING() {
        return STRING;
    }

    public static ForthTokenType getNUMBER() {
        return NUMBER;
    }

    public static ForthTokenType getTRUE() {
        return TRUE;
    }

    public static ForthTokenType getFALSE() {
        return FALSE;
    }

    public static ForthTokenType getDOT() {
        return DOT;
    }

    public static ForthTokenType getSTACK() {
        return STACK;
    }

    public static ForthTokenType getSEMICOLON() {
        return SEMICOLON;
    }

    public static ForthTokenType getDOUBLE_COLON() {
        return DOUBLE_COLON;
    }

    public static ForthTokenType getEQUAL() {
        return EQUAL;
    }

    public static ForthTokenType getLESS() {
        return LESS;
    }

    public static ForthTokenType getGREATER() {
        return GREATER;
    }

    public static ForthTokenType getOR() {
        return OR;
    }

    public static ForthTokenType getAND() {
        return AND;
    }

    public static ForthTokenType getIF() {
        return IF;
    }

    public static ForthTokenType getELSE() {
        return ELSE;
    }

    public static ForthTokenType getTHEN() {
        return THEN;
    }

    public static ForthTokenType getDO() {
        return DO;
    }

    public static ForthTokenType getLOOP() {
        return LOOP;
    }

    public static ForthTokenType getBEGIN() {
        return BEGIN;
    }

    public static ForthTokenType getUNTIL() {
        return UNTIL;
    }

    public static ForthTokenType getADD() {
        return ADD;
    }

    public static ForthTokenType getSUB() {
        return SUB;
    }

    public static ForthTokenType getMUL() {
        return MUL;
    }

    public static ForthTokenType getDIV() {
        return DIV;
    }

    public static ForthTokenType getMOD() {
        return MOD;
    }

    public static ForthTokenType getDIV_AND_MOD() {
        return DIV_AND_MOD;
    }

    public static ForthTokenType getEMIT() {
        return EMIT;
    }

    public static ForthTokenType getSWAP() {
        return SWAP;
    }

    public static ForthTokenType getDUP() {
        return DUP;
    }

    public static ForthTokenType getROT() {
        return ROT;
    }

    public static ForthTokenType getOVER() {
        return OVER;
    }

    public static ForthTokenType getDROP() {
        return DROP;
    }

    public static ForthTokenType getIDENTIFIER() {
        return IDENTIFIER;
    }

    public static ForthTokenType getCONST() {
        return CONST;
    }

    public static ForthTokenType getKEY() {
        return KEY;
    }

    public static ForthTokenType getSPACE() {
        return SPACE;
    }

    public static ForthTokenType getNEW_LINE() {
        return NEW_LINE;
    }

    public static ForthTokenType getCOMMENT() {
        return COMMENT;
    }

    public static TokenSet getKEYWORDS() {
        return KEYWORDS;
    }

    public static TokenSet getOPERATORS() {
        return OPERATORS;
    }

    public static TokenSet getIDENTS() {
        return IDENTS;
    }

    public static TokenSet getCOMMENTS() {
        return COMMENTS;
    }


    public ForthTokenType(@NotNull @NonNls String debugName) {
        super(debugName, ForthLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "ForthTokenType." + super.toString();
    }

}
