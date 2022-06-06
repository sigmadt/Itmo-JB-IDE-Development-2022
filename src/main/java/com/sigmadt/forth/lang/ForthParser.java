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
            inner.parseFile();
            return builder.getTreeBuilt();
        }

    private class InnerParser {
            PsiBuilder builder;

            public InnerParser(PsiBuilder builder) {
                this.builder = builder;
            }

            public void parseFile() {
                var mark = builder.mark();

                parseAnyBlock();

                mark.done(ForthElementType.getForthFile());
            }

            /*
            in forth file u can just type numbers, they will be pushed on stack
            so all that's matter is compile mode block,
            because if, do and other keywords are compile-mode only
             */

            public void parseAnyBlock() {
                var mark = builder.mark();

                while (builder.getTokenType() != null) {
                    if (builder.getTokenType().equals(ForthTokenType.getDOUBLE_COLON())) {
                        parseCompileModeBlock();
                    } else {
                        parseStatement();
                    }
                }

                mark.done(ForthElementType.getForthBlock());
            }

            public void parseStatement() {
                var mark = builder.mark();

                while (ForthTokenType.ANY_GOOD_TOKENS.contains(builder.getTokenType())) {
                    var currElem = builder.getTokenType();
                    if (ForthTokenType.QUALIFIERS.contains(currElem)) {
                        parseQualifiers();
                    }
                    else if (ForthTokenType.STACK_OPERATIONS.contains(currElem)) {
                        parseStackOperations();
                    }
                    else if (ForthTokenType.ANY_FUNCTION.contains(currElem)) {
                        parseMathOperations();
                    }
                }

                mark.done(ForthElementType.getSTATEMENT());

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

                mark.done(ForthElementType.getCompileModeBody());

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
                if (builder.getTokenType().equals(ForthTokenType.getELSE())) {
                    expectAdvance(ForthTokenType.getELSE(), "else");
                    parseStatement(); // (elsetrue)
                }
                expectAdvance(ForthTokenType.getTHEN(), "then");
                parseStatement();

                mark.done(ForthElementType.getIfElseThenStatement());
            }

            // numbers | identifiers | strings
            public void parseQualifiers() {
                var mark = builder.mark();

                while (ForthTokenType.QUALIFIERS.contains(builder.getTokenType())) {
                    if (builder.getTokenType().equals(ForthTokenType.getNUMBER())) {
                        expectAdvance(ForthTokenType.getNUMBER(), "NUMBER");
                    }
                    else if (builder.getTokenType().equals(ForthTokenType.getSTRING())) {
                        expectAdvance(ForthTokenType.getSTRING(), "STRING");
                    }
                    else if (builder.getTokenType().equals(ForthTokenType.getIDENTIFIER())) {
                        expectAdvance(ForthTokenType.getIDENTIFIER(), "IDENTIFIER");
                    }
                }

                mark.done(ForthElementType.getQUALIFIERS());
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
                while (ForthTokenType.OPERATORS.contains(builder.getTokenType())) {
                    parseInnerTerm();
                    parseMathOperations();
                }

                mark.done(ForthElementType.getMathExpression());
            }

            // + - or
            public void parseMathOperations() {
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

            public void parseStackOperations() {
                var mark = builder.mark();
                while (ForthTokenType.ANY_GOOD_TOKENS.contains(builder.getTokenType())) {
                    if (builder.getTokenType().equals(ForthTokenType.getROT())) {
                        expectAdvance(ForthTokenType.getROT(), "ROT");
                    }
                    else if (builder.getTokenType().equals(ForthTokenType.getOVER())) {
                        expectAdvance(ForthTokenType.getOVER(), "OVER");
                    }
                    else if (builder.getTokenType().equals(ForthTokenType.getDOT())) {
                        expectAdvance(ForthTokenType.getDOT(), "DOT");
                    }
                    else if (builder.getTokenType().equals(ForthTokenType.getSTACK())) {
                        expectAdvance(ForthTokenType.getSTACK(), "STACK");
                    }
                    else if (builder.getTokenType().equals(ForthTokenType.getSWAP())) {
                        expectAdvance(ForthTokenType.getSWAP(), "SWAP");
                    }
                    else if (builder.getTokenType().equals(ForthTokenType.getDUP())) {
                        expectAdvance(ForthTokenType.getDUP(), "DUP");
                    }
                    else if (builder.getTokenType().equals(ForthTokenType.getDROP())) {
                        expectAdvance(ForthTokenType.getDROP(), "DROP");
                    }

                }
                mark.done(ForthElementType.getStackOperations());
            }

            public void parseInnerTerm() {
                var mark = builder.mark();
                parseParameterList();
                mark.done(ForthElementType.getInnerTermExpression());

            }

            public void parseNum() {
                var mark = builder.mark();
                expectAdvance(ForthTokenType.getNUMBER(), "NUMBER");
                mark.done(ForthElementType.getNUMBER());
            }

            public void parseString() {
                var mark = builder.mark();
                expectAdvance(ForthTokenType.getSTRING(), "STRING");
                mark.done(ForthElementType.getSTRING());
            }

            // parameters are always numbers and parse until variable
            public void parseParameterList() {
                var mark = builder.mark();

                while (!ForthTokenType.ANY_FUNCTION.contains(builder.getTokenType())) {
                    if (builder.getTokenType().equals(ForthTokenType.getIDENTIFIER())) {
                        parseIdentifier();
                    }
                    parseNum();
                }

                mark.done(ForthElementType.getParameterList());
            }


            public void parseDoLoopStatement() {
                var mark = builder.mark();

                expectAdvance(ForthTokenType.getDO(), "do");
                parseStatement();
                expectAdvance(ForthTokenType.getLOOP(), "loop");

                mark.done(ForthElementType.getDoLoopStatement());
            }

            public void parseBeginUntilStatement() {
                var mark = builder.mark();

                expectAdvance(ForthTokenType.getBEGIN(), "begin");
                parseStatement();
                expectAdvance(ForthTokenType.getUNTIL(), "until");

                mark.done(ForthElementType.getBeginUntilStatement());

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