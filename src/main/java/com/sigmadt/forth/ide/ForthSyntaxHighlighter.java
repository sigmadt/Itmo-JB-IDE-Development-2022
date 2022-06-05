package com.sigmadt.forth.ide;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.sigmadt.forth.lang.ForthLexer;
import com.sigmadt.forth.lang.ForthTokenType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ForthSyntaxHighlighter extends SyntaxHighlighterBase {
    public Map<IElementType, TextAttributesKey> elementTextAttributes = new HashMap<>();


    public ForthSyntaxHighlighter() {
        fillMap(elementTextAttributes, ForthTokenType.KEYWORDS, ForthTextAttributeKeys.KEYWORD.key);
        fillMap(elementTextAttributes, ForthTokenType.OPERATORS, ForthTextAttributeKeys.OPERATOR.key);
        fillMap(elementTextAttributes, ForthTokenType.NUMBERS, ForthTextAttributeKeys.NUMBER.key);
        fillMap(elementTextAttributes, ForthTokenType.COMMENTS, ForthTextAttributeKeys.COMMENT.key);
        fillMap(elementTextAttributes, ForthTokenType.STRINGS, ForthTextAttributeKeys.STRING.key);
        fillMap(elementTextAttributes, ForthTokenType.CONSTANTS, ForthTextAttributeKeys.CONSTANT.key);
        fillMap(elementTextAttributes, ForthTokenType.STACK_OPERATIONS, ForthTextAttributeKeys.STACK_OPERATIONS.key);

        elementTextAttributes.put(ForthTokenType.getIDENTIFIER(), ForthTextAttributeKeys.IDENTIFIER.key);
    }


    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new ForthLexer();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        return pack(elementTextAttributes.get(tokenType));
    }

}
