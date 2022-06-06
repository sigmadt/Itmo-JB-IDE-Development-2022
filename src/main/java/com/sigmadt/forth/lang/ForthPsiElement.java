package com.sigmadt.forth.lang;


import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;

public abstract class ForthPsiElement extends ASTWrapperPsiElement {

        public ForthPsiElement(ASTNode node) {
            super(node);
        }


        @Override
        public ForthFile getContainingFile()  {
            return (ForthFile) super.getContainingFile();
        }
}


