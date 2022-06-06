package com.sigmadt.forth.ide;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.options.colors.AttributesDescriptor;

public enum ForthTextAttributeKeys {
    COMMENT("Comment", DefaultLanguageHighlighterColors.DOC_COMMENT),
    STRING("String//String text", DefaultLanguageHighlighterColors.STRING),
    CONSTANT("Built in constants", DefaultLanguageHighlighterColors.CONSTANT),
    NUMBER("Number", DefaultLanguageHighlighterColors.NUMBER),
    KEYWORD("Keyword", DefaultLanguageHighlighterColors.KEYWORD),

    STACK_OPERATIONS("Stack//Operations",
                    TextAttributesKey.createTextAttributesKey("ANNOTATION_NAME_ATTRIBUTES")),

    DOUBLE_COLON("Operators//Double colon", DefaultLanguageHighlighterColors.DOT),
    SEMICOLON("Operators//Semicolon", DefaultLanguageHighlighterColors.SEMICOLON),
    DOT("Operators//Dot", DefaultLanguageHighlighterColors.DOT),

    OPERATOR("Operators//Operators", DefaultLanguageHighlighterColors.OPERATION_SIGN),
    COMP_OPERATOR("Operators//Comparison operators", DefaultLanguageHighlighterColors.OPERATION_SIGN),
    IDENTIFIER("Identifier", DefaultLanguageHighlighterColors.IDENTIFIER);


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
