/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.util.regex.*;

/**
 *
 * @author ABC
 */
public class TokenClassification {

    String token;
    static int a, b;

    public TokenClassification(String token) {
        this.token = token;
    }

    public TokenClassification() {
    }

// Function for keyword checking
    boolean isKeyword(String token) {
        String keywords[][] = {{"num", "point", "Char", "word", "Short", "Long", "Double", "Boolean", "Vector"}, //DT
        {"Public", "Private", "Protected"},//AM
        {"True","False"},// Boolean_Cons
        {"While"},
        {"Do"},
        {"If"},
        {"Else"},
        {"Switch"},
        {"Return"},
        {"Void"},
        {"Static"},
        {"Case"},
        {"Break"},
        {"Continue"},
        {"Main"},
        {"Enum"},
        {"Cons"},
        {"Default"},
        {"Class"},
        {"For"},
        {"Final"},
        {"New"},
        {"This"}};

        for (int i = 0; i < 23; i++) {
            for (int j = 0; j < keywords[i].length; j++) {
                if (keywords[i][j].equals(token)) {
                    a = i;
                    return true;
                }
            }
        }

        return false;
    }
//Function for operator checking

    boolean isOperator(String token) {
        String operator[][] = {{"+", "-"}, //PM
            { "*", "/", "%"},// MDM
        {"<", ">", "<=", ">=", "==", "!=", "==="}, //R_Opr
        {"=", "+=", "-=", "*=", "/=", "%="}, //Ass_Opr
        { "AND", "OR"}, //Cndtnl_Opr
        {"++", "--","NOT"}, //U_Opr
        {"<<", ">>"},//S_Opr
        {"**"}}; // pointer

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < operator[i].length; j++) {
                if ((operator[i][j]).equals(token)) {
                    b = i;
                    return true;
                }
            }
        }

        return false;
    }
//Function for punctuator checking 

    boolean isPunctuator(String token) {
        String punctuator[] = {"(", ")", "{", "}", "@", "[", "]", ":", ";", ".", ","};

        for (String punctuator1 : punctuator) {
            if (punctuator1.equals(token)) {
                return true;
            }
        }

        return false;
    }
//Function for identifier checking 

    boolean isIdentifier(String token) {
        Pattern id = Pattern.compile("([$A-Z$]|[$a-z$])([$A-Z$]|[$a-z$]|[$0-9$]|_|$)*|_([$A-Z$]|[$a-z$]|[$0-9$]|_)+|$([$A-Z$]|[$a-z$]|[$0-9$]|_)+");
        Matcher mtch = id.matcher(token);
        if (mtch.matches()) {
            return true;
        }

        return false;
    }

    //Function for Float constant checking 
    boolean isFloat(String token) {
        Pattern id = Pattern.compile("[+-]?[0-9]*[.][0-9]+([Ee]([+-]?[0-9]+))?");
        Matcher mtch = id.matcher(token);
        if (mtch.matches()) {
            return true;
        }

        return false;
    }
    //Function for Integer constant checking 

    boolean isInt(String token) {
        Pattern id = Pattern.compile("[+-]?[0-9]+");
        Matcher mtch = id.matcher(token);
        if (mtch.matches()) {
            return true;
        }

        return false;
    }
    //Function for String constant checking 

    boolean isString(String token) {
        Pattern id = Pattern.compile("\"((\\\\(\\\\|\"|r|b|t|n|o))|([^\\\\\"]))*\"");
        Matcher mtch = id.matcher(token);
        if (mtch.matches()) {
            return true;
        }

        return false;
    }

    //Function for Char constant checking 
    boolean isChar(String token) {
        Pattern id = Pattern.compile("(\'(\\\\(\\\\|\"|\'|r|b|t|n|o))\')|(\'[^\\\\\"\']\')");
        Matcher mtch = id.matcher(token);
        if (mtch.matches()) {
            return true;
        }

        return false;
    }
}
