package com.sigmadt.forth.lang;


import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;


public abstract class ForthNamedPsiElement extends ForthPsiElement implements PsiElement {
    public ForthNamedPsiElement(ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        return getNode().getText();
    }
}

