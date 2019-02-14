/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

/**
 *
 * @author ABC
 */
public class Token {
    String C_p;
    String V_p;
    int lineNumber;

    public Token() {
    }

    public Token(String C_p, String V_p, int lineNumber) {
        this.C_p = C_p;
        this.V_p = V_p;
        this.lineNumber = lineNumber;
    }
    
    
}
