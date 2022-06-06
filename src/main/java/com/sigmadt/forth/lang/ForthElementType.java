package com.sigmadt.forth.lang;


import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.PsiFileStub;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.IStubFileElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class ForthElementType extends IElementType {
    private static IFileElementType FORTH_FILE = new IFileElementType(ForthLanguage.INSTANCE);



    private static IStubFileElementType<PsiFileStub<ForthFile>> FORTH_STUB_FILE =
            new IStubFileElementType<>(ForthLanguage.INSTANCE);


    /* TYPES */
    private static ForthElementType FORTH_BLOCK = new ForthElementType("FORTH_BLOCK");


    // identifier (word in terms of forth)
    private static ForthElementType IDENTIFIER = new ForthElementType("IDENTIFIER");

    // just number
    private static ForthElementType NUMBER = new ForthElementType("NUMBER");

    // just string ." str"
    private static ForthElementType STRING = new ForthElementType("STRING");

    // if else then block
    private static ForthElementType IF_ELSE_THEN_STATEMENT = new ForthElementType("IF_ELSE_THEN");

    // do block
    private static ForthElementType DO_LOOP_STATEMENT = new ForthElementType("DO_LOOP_STATEMENT");

    // begin block
    private static ForthElementType BEGIN_UNTIL_STATEMENT = new ForthElementType("BEGIN_UNTIL_STATEMENT");


    /* Compile Mode Block */
    private static ForthElementType COMPILE_MODE_BLOCK = new ForthElementType("COMPILE_MODE_BLOCK");

    /* body inside compile mode block */
    private static ForthElementType COMPILE_MODE_BODY = new ForthElementType("COMPILE_MODE_BODY");

    //     `<` | `>` | `=` |
    private static ForthTokenType EXPRESSION = new ForthTokenType("EXPRESSION");

    private static ForthTokenType COMP_OPER_EXPRESSION = new ForthTokenType("COMP_OPER_EXPRESSION");



    private static ForthTokenType MATH_EXPRESSION = new ForthTokenType("MATH_EXPRESSION");



    private static ForthTokenType STACK_OPERATIONS = new ForthTokenType("STACK_EXPRESSION");

    private static ForthTokenType MATH_OPERATIONS = new ForthTokenType("MATH_OPERATIONS");

    private static ForthTokenType PARAMETER_LIST = new ForthTokenType("PARAMETER_LIST");

    private static ForthTokenType STATEMENT = new ForthTokenType("STATEMENT");

    private static ForthTokenType QUALIFIERS = new ForthTokenType("QUALIFIERS");


    private static ForthTokenType INNER_TERM_EXPRESSION = new ForthTokenType("INNER_TERM_EXPRESSION");


    public static IFileElementType getForthFile() {
        return FORTH_FILE;
    }


    public static ForthElementType getForthBlock() {
        return FORTH_BLOCK;
    }


    public static ForthElementType getDoLoopStatement() {
        return DO_LOOP_STATEMENT;
    }

    public static ForthElementType getBeginUntilStatement() {
        return BEGIN_UNTIL_STATEMENT;
    }

    public static ForthElementType getCompileModeBlock() {
        return COMPILE_MODE_BLOCK;
    }

    public static ForthElementType getCompileModeBody() {
        return COMPILE_MODE_BODY;
    }

    public static ForthTokenType getEXPRESSION() {
        return EXPRESSION;
    }


    public static ForthTokenType getInnerTermExpression() {
        return INNER_TERM_EXPRESSION;
    }

    public static ForthTokenType getCompOperExpression() {
        return COMP_OPER_EXPRESSION;
    }

    public static ForthTokenType getMathExpression() {
        return MATH_EXPRESSION;
    }

    public static ForthTokenType getParameterList() {
        return PARAMETER_LIST;
    }

    public static ForthElementType getNUMBER() {
        return NUMBER;
    }

    public static ForthElementType getSTRING() {
        return STRING;
    }

    public static ForthElementType getIDENTIFIER() {
        return IDENTIFIER;
    }

    public static ForthTokenType getSTATEMENT() {
        return STATEMENT;
    }

    public static ForthElementType getIfElseThenStatement() {
        return IF_ELSE_THEN_STATEMENT;
    }

    public static ForthTokenType getMathOperations() {
        return MATH_OPERATIONS;
    }

    public static ForthTokenType getStackOperations() {
        return STACK_OPERATIONS;
    }

    public static ForthTokenType getQUALIFIERS() {
        return QUALIFIERS;
    }

    public static IStubFileElementType getForthStubFile() {
        return FORTH_STUB_FILE;
    }
    public ForthElementType(@NotNull @NonNls String debugName) {
        super(debugName, ForthLanguage.INSTANCE);
    }


    public static PsiElement createElement(ASTNode node) {
        if (FORTH_BLOCK.equals(node.getElementType())) {
            return new ForthSimpleNode(node);
        }
        else if (IDENTIFIER.equals(node.getElementType())) {
            return new ForthSimpleNode(node);
        }
        else if (NUMBER.equals(node.getElementType())) {
            return new ForthSimpleNode(node);
        }
        else if (STRING.equals(node.getElementType())) {
            return new ForthSimpleNode(node);
        }
        else if (IF_ELSE_THEN_STATEMENT.equals(node.getElementType())) {
            return new ForthSimpleNode(node);
        }
        else if (DO_LOOP_STATEMENT.equals(node.getElementType())) {
            return new ForthSimpleNode(node);
        }
        else if (BEGIN_UNTIL_STATEMENT.equals(node.getElementType())) {
            return new ForthSimpleNode(node);
        }
        else if (COMPILE_MODE_BLOCK.equals(node.getElementType())) {
            return new ForthSimpleNode(node);
        }
        else if (COMPILE_MODE_BODY.equals(node.getElementType())) {
            return new ForthSimpleNode(node);
        }
        else if (EXPRESSION.equals(node.getElementType())) {
            return new ForthSimpleNode(node);
        }
        else if (COMP_OPER_EXPRESSION.equals(node.getElementType())) {
            return new ForthSimpleNode(node);
        }
        else if (INNER_TERM_EXPRESSION.equals(node.getElementType())) {
            return new ForthSimpleNode(node);
        }
        else if (MATH_EXPRESSION.equals(node.getElementType())) {
            return new ForthSimpleNode(node);
        }
        else if (MATH_OPERATIONS.equals(node.getElementType())) {
            return new ForthSimpleNode(node);
        }
        else if (STACK_OPERATIONS.equals(node.getElementType())) {
            return new ForthSimpleNode(node);
        }
        else if (STATEMENT.equals(node.getElementType())) {
            return new ForthSimpleNode(node);
        }
        else if (QUALIFIERS.equals(node.getElementType())) {
            return new ForthSimpleNode(node);
        }
        else {
            throw new IllegalArgumentException(String.format("Unknown elementType : %s", node.getElementType()));
        }


    }

}


