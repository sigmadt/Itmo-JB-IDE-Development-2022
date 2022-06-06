package com.sigmadt.forth.lang;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.NavigatablePsiElement;
import com.sigmadt.forth.lang.ForthFileType;
import com.sigmadt.forth.lang.ForthLanguage;
import org.jetbrains.annotations.NotNull;

public class ForthFile extends PsiFileBase implements NavigatablePsiElement {

    public ForthFile(FileViewProvider viewProvider) {
        super(viewProvider, ForthLanguage.INSTANCE);
    }

    @Override
    public @NotNull FileType getFileType() {
        return new ForthFileType();
    }




}