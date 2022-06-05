package com.sigmadt.forth.lang;


import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class ForthElementType extends IElementType {
    private IFileElementType FORTH_CHUNK_FILE = new IFileElementType(new ForthLanguage());
    private ForthElementType FORTH_CHUNK = new ForthElementType("FORTH_CHUNK");

    // region statements
    private ForthElementType FORTH_BLOCK = new ForthElementType("FORTH_BLOCK");
    private ForthElementType STATEMENT_LIST = new ForthElementType("STATEMENT_LIST");


    // if else then block
    private ForthElementType IF_STATEMENT = new ForthElementType("IF_STATEMENT");

    // do block
    private ForthElementType DO_STATEMENT = new ForthElementType("DO_STATEMENT");

    // begin block
    private ForthElementType BEGIN_STATEMENT = new ForthElementType("BEGIN_STATEMENT");


    /* Compile Mode Block */
    private ForthElementType COMPILE_MODE_BLOCK = new ForthElementType("COMPILE_MODE_BLOCK");



    /* Binary expressions */
    // exp binop exp
    private ForthElementType BINARY_EXPRESSION = new ForthElementType("BINARY_EXPRESSION");
    //     `+` | `-` | `*` | `/` | `/` | `mod` |
    //     `<` | `>` | `=` |
    //     and | or
    private ForthElementType BINARY_OPERATOR = new ForthElementType("BINARY_OPERATOR");



    public ForthElementType(@NotNull @NonNls String debugName) {
        super(debugName, ForthLanguage.INSTANCE);
    }

}


