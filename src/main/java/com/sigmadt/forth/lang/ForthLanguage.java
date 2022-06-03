package com.sigmadt.forth.lang;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ForthLanguage extends Language {
    public static final ForthLanguage INSTANCE = new ForthLanguage();

    public ForthLanguage() {
        super("Forth");
    }
}




