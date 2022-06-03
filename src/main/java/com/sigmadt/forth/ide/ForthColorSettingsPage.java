package com.sigmadt.forth.ide;

import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.sigmadt.forth.lang.ForthIcons;
import com.sigmadt.forth.lang.ForthLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ForthColorSettingsPage implements ColorSettingsPage {
    private String DEMO_TEXT = CodeStyleAbstractPanel.readFromFile(ForthLanguage.class, "Sample.fs");
    
    @Nullable
    @Override
    public Icon getIcon() {
        return ForthIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new ForthSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return DEMO_TEXT;
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return Arrays
                .stream(ForthTextAttributeKeys
                .values())
                .collect(Collectors.toMap(Enum::name, e -> e.key));
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
        return Arrays
                .stream(ForthTextAttributeKeys
                .values())
                .map(e -> e.descriptor)
                .toArray(AttributesDescriptor[]::new);
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return ForthLanguage.INSTANCE.getDisplayName();
    }

}