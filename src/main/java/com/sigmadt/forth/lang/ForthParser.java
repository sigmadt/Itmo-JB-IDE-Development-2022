package com.sigmadt.forth.lang;


import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class ForthParser implements PsiParser {
        @Override
        @NotNull
        public  ASTNode parse(@NotNull IElementType root, @NotNull PsiBuilder builder) {
            builder.setDebugMode(true);
            InnerParser(builder).parseChunk();
            return builder.treeBuilt;
        }




}