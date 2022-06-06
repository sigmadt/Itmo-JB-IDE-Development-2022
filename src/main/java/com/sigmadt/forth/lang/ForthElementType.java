package com.sigmadt.forth.lang;


import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class ForthElementType extends IElementType {
    private static IFileElementType FORTH_CHUNK_FILE = new IFileElementType(new ForthLanguage());
    private static ForthElementType FORTH_CHUNK = new ForthElementType("FORTH_CHUNK");

    // region statements
    private static ForthElementType FORTH_BLOCK = new ForthElementType("FORTH_BLOCK");
    private static ForthElementType STATEMENT_LIST = new ForthElementType("STATEMENT_LIST");

    public static ForthElementType getIDENTIFIER() {
        return IDENTIFIER;
    }

    // identifier (word in terms of forth)
    private static ForthElementType IDENTIFIER = new ForthElementType("IDENTIFIER");

    // if else then block
    private static ForthElementType IF_STATEMENT = new ForthElementType("IF_STATEMENT");

    // do block
    private static ForthElementType DO_STATEMENT = new ForthElementType("DO_STATEMENT");

    // begin block
    private static ForthElementType BEGIN_STATEMENT = new ForthElementType("BEGIN_STATEMENT");


    /* Compile Mode Block */
    private static ForthElementType COMPILE_MODE_BLOCK = new ForthElementType("COMPILE_MODE_BLOCK");



    //     `<` | `>` | `=` |
    private static ForthTokenType EXPRESSION = new ForthTokenType("EXPRESSION");
    private static ForthTokenType COMP_OPER_EXPRESSION = new ForthTokenType("COMP_OPER_EXPRESSION");



    private static ForthTokenType MATH_EXPRESSION = new ForthTokenType("MATH_EXPRESSION");

    public static ForthTokenType getMathOperations() {
        return MATH_OPERATIONS;
    }

    private static ForthTokenType MATH_OPERATIONS = new ForthTokenType("MATH_OPERATIONS");


    private static ForthTokenType LOW_PRIOR_EXPRESSION = new ForthTokenType("LOW_PRIOR_EXPRESSION");



    private static ForthTokenType HIGH_PRIOR_EXPRESSION = new ForthTokenType("HIGH_PRIOR_EXPRESSION");

    private static ForthTokenType INNER_TERM_EXPRESSION = new ForthTokenType("INNER_TERM_EXPRESSION");
    /* Binary expressions */
    // exp binop exp
    private static ForthElementType BINARY_EXPRESSION = new ForthElementType("BINARY_EXPRESSION");

    //     `+` | `-` | `*` | `/` | `/` | `mod` |
    //     and | or
    private static ForthElementType BINARY_OPERATOR = new ForthElementType("BINARY_OPERATOR");


    public static IFileElementType getForthChunkFile() {
        return FORTH_CHUNK_FILE;
    }

    public static ForthElementType getForthChunk() {
        return FORTH_CHUNK;
    }

    public static ForthElementType getForthBlock() {
        return FORTH_BLOCK;
    }

    public static ForthElementType getStatementList() {
        return STATEMENT_LIST;
    }

    public static ForthElementType getIfStatement() {
        return IF_STATEMENT;
    }

    public static ForthElementType getDoStatement() {
        return DO_STATEMENT;
    }

    public static ForthElementType getBeginStatement() {
        return BEGIN_STATEMENT;
    }

    public static ForthElementType getCompileModeBlock() {
        return COMPILE_MODE_BLOCK;
    }

    public static ForthElementType getBinaryExpression() {
        return BINARY_EXPRESSION;
    }

    public static ForthElementType getBinaryOperator() {
        return BINARY_OPERATOR;
    }

    public static ForthTokenType getEXPRESSION() {
        return EXPRESSION;
    }
    public static ForthTokenType getHighPriorExpression() {
        return HIGH_PRIOR_EXPRESSION;
    }

    public static ForthTokenType getInnerTermExpression() {
        return INNER_TERM_EXPRESSION;
    }

    public static ForthTokenType getLowPriorExpression() {
        return LOW_PRIOR_EXPRESSION;
    }

    public static ForthTokenType getCompOperExpression() {
        return COMP_OPER_EXPRESSION;
    }

    public static ForthTokenType getMathExpression() {
        return MATH_EXPRESSION;
    }

    public ForthElementType(@NotNull @NonNls String debugName) {
        super(debugName, ForthLanguage.INSTANCE);
    }

}


