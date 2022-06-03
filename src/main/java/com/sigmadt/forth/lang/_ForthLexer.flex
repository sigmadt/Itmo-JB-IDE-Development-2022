package com.sigmadt.forth.lang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.sigmadt.forth.lang.ForthTokenType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;

%%

%public
%class _ForthLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode



/* Main Character Classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

Whitespace = {LineTerminator} | [ \t\f]
Alpha      = [a-zA-Z]
Num        = [0-9]
//AlphaNum   = {Alpha} | {Num}
//Underscore = _

//Identifier = ({Underscore} | {Alpha}) {AlphaNum}*

DoubleQuotedString = (.\" ([^\"\r\n\\]|\\.)*\")

LineComment  = (\(([^\"\r\n\\]|\\.)*\))


/* Real Numbers */

//RealNumber = ([0-9]+\.[0-9]*|[0-9]*\.[0-9]+) {Exponent}?
//Exponent   = [eE] [+-]? [0-9]+

%state LITERAL_STRING

%%

<YYINITIAL> {
    /* Boolean Literals */
    "true"              { return ForthTokenType.getTRUE(); }
    "false"             { return ForthTokenType.getFALSE(); }

    /* Logical Operators */
    "or"                { return ForthTokenType.getOR(); }
    "and"               { return ForthTokenType.getAND(); }

    /* Control Structures */
    "if"                { return ForthTokenType.getIF(); }
    "then"              { return ForthTokenType.getTHEN(); }
    "else"              { return ForthTokenType.getELSE(); }

    /* Loop syntax */
    "do"                { return ForthTokenType.getDO(); }
    "until"             { return ForthTokenType.getUNTIL(); }
    "begin"             { return ForthTokenType.getBEGIN(); }
    "until"             { return ForthTokenType.getUNTIL(); }

    /* Compile mode */
    ":"                 { return ForthTokenType.getDOUBLE_COLON(); }
    ";"                 { return ForthTokenType.getSEMICOLON(); }

    /* Stack operations */
    "."                 { return ForthTokenType.getDOT(); }
    ".s"                { return ForthTokenType.getSTACK(); }
    "dup"               { return ForthTokenType.getDUP(); }
    "over"              { return ForthTokenType.getOVER(); }
    "drop"              { return ForthTokenType.getDROP(); }
    "swap"              { return ForthTokenType.getSWAP(); }
    "rot"               { return ForthTokenType.getROT(); }


    /* Arithmetic Operators */
    "+"                 { return ForthTokenType.getADD(); }
    "-"                 { return ForthTokenType.getSUB(); }
    "*"                 { return ForthTokenType.getMUL(); }
    "/"                 { return ForthTokenType.getDIV(); }
    "mod"               { return ForthTokenType.getMOD(); }
    "/mod"              { return ForthTokenType.getDIV_AND_MOD(); }

    /* Relational Operators */
    "<"                 { return ForthTokenType.getLESS(); }
    ">"                 { return ForthTokenType.getGREATER(); }
    "="                 { return ForthTokenType.getEQUAL(); }

    /* */

}


/* Comments */
{LineComment}           { return ForthTokenType.getCOMMENT(); }


/* String Literals */
{DoubleQuotedString}    { return ForthTokenType.getSTRING(); }




/* error fallback */
[^]                     { return BAD_CHARACTER; }