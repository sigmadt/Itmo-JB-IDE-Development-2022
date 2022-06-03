package com.sigmadt.forth.lang;


import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.FlexLexer;

import com.sigmadt.forth.lang._ForthLexer;

public class ForthLexer extends FlexAdapter {
    public ForthLexer() {
        super(new _ForthLexer(null));
    }
}

