/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.util.ArrayList;

/**
 *
 * @author ABC
 */
public class TokenValidate {

    int lineNum;
    String token;
    Token t;
    static ArrayList<Token> TokenList = new ArrayList();

    TokenClassification tc = new TokenClassification(this.token);
    TokenClassification tc1 = new TokenClassification();

    public TokenValidate() {
    }

    public TokenValidate(String token, int lineNum) {
        this.lineNum = lineNum;
        this.token = token;

    }

    public void validate() {
        if (tc.isIdentifier(token)) {
            if (tc.isKeyword(token)) {
                switch (tc1.a) {
                    case 0:

                        t = new Token("DT", token, lineNum);
                        TokenList.add(t);
                        break;

                    case 1:

                        t = new Token("AM", token, lineNum);
                        TokenList.add(t);

                        break;
                        
                         case 2:

                        t = new Token("Boolean_Cons", token, lineNum);
                        TokenList.add(t);

                        break;

                    default:
                        t = new Token(token, token, lineNum);
                        TokenList.add(t);

                        break;

                }
                
            }else if(tc.isOperator(token))
            { switch(token)
            { case "NOT":
                t = new Token("U_Opr", token, lineNum);
                        TokenList.add(t);
                        break;
                        
            default:
                 t = new Token("Cndtnl_Opr", token, lineNum);
                        TokenList.add(t);
            }
            }
            
            else
            {
               t=new Token("ID",token,lineNum);
               TokenList.add(t);
            }
        }
        
        else if(tc.isOperator(token))
        {
            switch(tc1.b)
            {
                case 0:
                    t=new Token("PM",token, lineNum);
                    TokenList.add(t);
                    
                    break;
                    
                case 1:
                 t = new Token("MDM", token, lineNum);
                        TokenList.add(t);

                        break;
                        
                case 2:
                     t = new Token("R_Opr", token, lineNum);
                        TokenList.add(t);

                        break;
                        
                case 3:
                     t = new Token("Ass_Opr", token, lineNum);
                        TokenList.add(t);

                        break;
                        
                case 4:
                     t = new Token("Cndtnl_Opr", token, lineNum);
                        TokenList.add(t);

                        break;
                        
                case 5:
                     t = new Token("U_Opr",token, lineNum);
                        TokenList.add(t);

                        break;
                        
                case 6:
                     t = new Token("S_Opr", token, lineNum);
                        TokenList.add(t);

                        break;
                case 7:
                    
                     t = new Token("Pointer", token, lineNum);
                        TokenList.add(t);

                        break;
                        
                default:
                    break;
   
            }
        }
        
        else if(tc.isPunctuator(token))
        {
            t=new Token(token,token,lineNum);
            TokenList.add(t);
        }
        else if(tc.isChar(token))
        {
            t=new Token("Char_Cons",token,lineNum);
            TokenList.add(t);
        }
        else if(tc.isString(token))
        {
            t=new Token("word_Cons",token,lineNum);
            TokenList.add(t);
        }
        else if(tc.isFloat(token))
        {
            t=new Token("point_Cons",token,lineNum);
            TokenList.add(t);
        }
        else if(tc.isInt(token))
        {
            t=new Token("num_Cons",token,lineNum);
            TokenList.add(t);
        }
        else if(token==" ")
        {
            
        }
        
        else
        {
            t=new Token("Invalid",token,lineNum);
            TokenList.add(t);
            System.out.println(token+" has misspelled, "+"Lexical Error at line number: "+lineNum);
            
        }

    }
}
