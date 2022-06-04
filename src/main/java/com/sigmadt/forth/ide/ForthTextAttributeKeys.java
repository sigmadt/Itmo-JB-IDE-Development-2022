package com.sigmadt.forth.ide;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.options.colors.AttributesDescriptor;

public enum ForthTextAttributeKeys {
    COMMENT("Comment", DefaultLanguageHighlighterColors.DOC_COMMENT),
    STRING("String//String text", DefaultLanguageHighlighterColors.STRING),
    CONSTANT("Built in constants", DefaultLanguageHighlighterColors.CONSTANT),
    NUMBER("Number//Number", DefaultLanguageHighlighterColors.NUMBER),
    KEYWORD("Keyword", DefaultLanguageHighlighterColors.KEYWORD),

    DOUBLE_COLON("Braces and Operators//Double colon", DefaultLanguageHighlighterColors.DOT),
    SEMICOLON("Braces and Operators//Semicolon", DefaultLanguageHighlighterColors.SEMICOLON),
    DOT("Braces and Operators//Dot", DefaultLanguageHighlighterColors.DOT),

    OPERATOR("Braces and Operators//Operators", DefaultLanguageHighlighterColors.OPERATION_SIGN);


    public String humanName;
    public TextAttributesKey fallback;

    public TextAttributesKey key;
    public AttributesDescriptor descriptor;

    ForthTextAttributeKeys(String humanName, TextAttributesKey fallback) {
        this.humanName = humanName;
        this.fallback = fallback;

        key = TextAttributesKey.createTextAttributesKey(
                String.format("Forth.%s", name()),
                fallback);

        descriptor = new AttributesDescriptor(humanName, key);
    }

}
