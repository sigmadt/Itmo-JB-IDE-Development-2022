package com.sigmadt.forth.lang;


import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class ForthParser implements PsiParser {
        @Override
        @NotNull
        public  ASTNode parse(@NotNull IElementType root, @NotNull PsiBuilder builder) {
            builder.setDebugMode(true);
            var inner = new InnerParser(builder);
            inner(builder).parseChunk();
            return builder.treeBuilt;
        }

    private class InnerParser {
            PsiBuilder builder;

            public InnerParser(PsiBuilder builder) {
                this.builder = builder;
            }

            public void parseFile() {
                var mark = builder.mark();

                parseBlock();

            }


            public void parseBlock() {
                var mark = builder.mark();

                parseIfBlock();


            }

            /*      : square dup * ;
            * ->    (:) begin compile mode
            * ->    (square) identifier
            *       (dup *)  list of statements
            *                        [if else then]
            *                        [do loop]
            *                        [begin until]
            *       (;) end of compile mode
            * */

            public void parseCompileModeBlock() {
                var mark = builder.mark();

                expectAdvance(ForthTokenType.getDOUBLE_COLON(), ":");
                parseIdentifier();
                parseCompileModeBody();
                expectAdvance(ForthTokenType.getSEMICOLON(), ";");

                mark.done(ForthElementType.getCompileModeBlock());

            }

            public void parseIdentifier() {
                var mark = builder.mark();
                expectAdvance(ForthTokenType.getIDENTIFIER(), "IDENTIFIER");
                mark.done(ForthElementType.getIDENTIFIER());
            }


            public void parseCompileModeBody() {
                var mark = builder.mark();

                parseIfElseThenStatement();
                parseDoLoopStatement();
                parseBeginUntilStatement();

            }

            /*  (cond) if (iftrue) else (elsetrue) then (work here)   */
            /* same as ternary operator
            * (cond) ? (iftrue) : (else) and continue with then
            *
            * */
            public void parseIfElseThenStatement() {
                var mark = builder.mark();

                parseExpression(); // (cond)
                expectAdvance(ForthTokenType.getIF(), "if");
                parseStatement(); // (iftrue)
                if (builder.tokenType == PascalTokenType.ELSE) {
                    expectAdvance(ForthTokenType.getELSE(), "else");
                    parseStatement(); // (elsetrue)
                }
                expectAdvance(ForthTokenType.getTHEN(), "then");
                parseStatement();


            }
            public void parseExpression() {
                var mark = builder.mark();

                parseMathExpression();
                if (ForthTokenType.COMPARISON_OPERATORS.contains(builder.getTokenType())) {
                    parseCompOperExpression();
                    parseMathExpression();
                }

                mark.done(ForthElementType.getEXPRESSION());
            }

            public void parseCompOperExpression() {
                var mark = builder.mark();

                IElementType tokenType = builder.getTokenType();
                if (ForthTokenType.getEQUAL().equals(tokenType)) {
                    expectAdvance(ForthTokenType.getEQUAL(), "=");
                } else if (ForthTokenType.getLESS().equals(tokenType)) {
                    expectAdvance(ForthTokenType.getLESS(), "<");
                } else if (ForthTokenType.getGREATER().equals(tokenType)) {
                    expectAdvance(ForthTokenType.getGREATER(), ">");
                } else {
                    errorAdvance("COMP_OPER_EXPRESSION");
                }

                mark.done(ForthElementType.getCompOperExpression());
            }

            // postfix : (term1) (term2) (oper)
            public void parseMathExpression() {
                var mark = builder.mark();

                parseInnerTerm();
                while (ForthTokenType.LOW_PRIOR_OPERATORS.contains(builder.getTokenType())) {
                    parseInnerTerm();
                    parseMathOper();
                }

                mark.done(ForthElementType.getMathExpression());
            }

            // + - or
            public void parseMathOper() {
                var mark = builder.mark();

                IElementType tokenType = builder.getTokenType();
                if (ForthTokenType.getADD().equals(tokenType)) {
                    expectAdvance(ForthTokenType.getADD(), "+");
                } else if (ForthTokenType.getSUB().equals(tokenType)) {
                    expectAdvance(ForthTokenType.getSUB(), "-");
                } else if (ForthTokenType.getOR().equals(tokenType)) {
                    expectAdvance(ForthTokenType.getOR(), "or");
                } else if (ForthTokenType.getMUL().equals(tokenType)) {
                    expectAdvance(ForthTokenType.getMUL(), "*");
                } else if (ForthTokenType.getDIV().equals(tokenType)) {
                    expectAdvance(ForthTokenType.getDIV(), "/");
                } else if (ForthTokenType.getAND().equals(tokenType)) {
                    expectAdvance(ForthTokenType.getAND(), "and");
                } else if (ForthTokenType.getMOD().equals(tokenType)) {
                    expectAdvance(ForthTokenType.getMOD(), "mod");
                } else if (ForthTokenType.getDIV_AND_MOD().equals(tokenType)) {
                    expectAdvance(ForthTokenType.getDIV_AND_MOD(), "/mod");
                }

                mark.done(ForthElementType.getMathOperations());
            }

            public void parseInnerTerm() {
                var mark = builder.mark();

                parseTerm();
                while (ForthTokenType.LOW_PRIOR_OPERATORS.contains(builder.getTokenType())) {
                    parseLowPriorOper();
                    parseTerm();
                }

                mark.done(ForthElementType.getMathExpression());




            }


            public void parseDoLoopStatement() {
                var mark = builder.mark();




            }

            public void parseBeginUntilStatement() {





            }




            // helper methods
            private boolean expectAdvance(ForthTokenType expectedTokenType, String expectedName) {
                if (builder.getTokenType() == expectedTokenType) {
                    advance();
                    return true;
                }
                else {
                    builder.error(String.format("Expected %s", expectedName));
                    return false;
                }
            }

            private IElementType advance() {
                var result = builder.getTokenType();
                builder.advanceLexer();
                while (builder.getTokenType() == TokenType.BAD_CHARACTER) {
                    var badMark = builder.mark();
                    builder.advanceLexer();
                    badMark.error("Unexpected character");
                }
                return result;
            }

            private void errorAdvance(String expectedName) {
                var mark = builder.mark();
                advance();
                mark.error(String.format("Expected %s", expectedName));
            }


    }


}