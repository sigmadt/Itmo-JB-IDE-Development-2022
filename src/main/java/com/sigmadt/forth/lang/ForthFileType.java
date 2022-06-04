package com.sigmadt.forth.lang;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ForthFileType extends LanguageFileType {

    public static final ForthFileType INSTANCE = new ForthFileType();

    private ForthFileType() {
        super(ForthLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Forth";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Forth language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "fs";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return ForthIcons.FILE;
    }
}
