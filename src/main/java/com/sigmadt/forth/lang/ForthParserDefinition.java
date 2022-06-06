package com.sigmadt.forth.lang;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.sigmadt.forth.lang.ForthParser;
import com.sigmadt.forth.lang.ForthFileType;
//import com.sigmadt.forth.lang.ForthTypes;
import org.jetbrains.annotations.NotNull;

public class ForthParserDefinition implements ParserDefinition {
    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new ForthLexer();
    }

    @NotNull
    @Override
    public PsiParser createParser(final Project project) {
        return new ForthParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return ForthElementType.getForthStubFile();
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return ForthTokenType.COMMENTS;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return ForthTokenType.STRINGS;
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new ForthFile(viewProvider);
    }

    @Override
    public @NotNull SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return ForthElementType.createElement(node);
    }

}