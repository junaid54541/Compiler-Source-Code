/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

/**
 *
 * @author Junaid
 */
public class breaker {

    static String path;

    public breaker(String path) {
        this.path = path;
    }

    static public void split() throws FileNotFoundException, IOException {
        File file = new File(path);
        TokenValidate tv = new TokenValidate();
        TokenClassification tc = new TokenClassification();
        Token t = new Token();
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<String> st1 = new ArrayList();
        String st;
        int m_comment = 0;
        boolean c = false;
        int k = 0;
        int lineNum = 0;
        o_outer:
        while ((st = br.readLine()) != null) {
            lineNum++;
            int val = st.length();
            String temp = "";
            inner1:
            for (int i = 0; i < st.length(); i++) {
                k = 0;

                temp = "";
                //for line change
                if ((i + 1 == val) && st.charAt(i) != ' ' && st.charAt(i) != '#') {
                    temp += st.charAt(i);
                    if (temp != "") {
                        tv = new TokenValidate(temp, lineNum);
                        tv.validate();
                    }

                    break;
                } else {
                    outer:
                    while (k == 0) {

                        if (st.charAt(i) == '#') {
                            m_comment = 1;
                            if (st.charAt(i + 1) == '#') {
                                m_comment = 1;
                                if (st.charAt(i + 2) == '#') {
                                    m_comment = 0;
                                    i += 3;
                                    while (st.charAt(i) != '#') {
                                        i++;
                                        if (i == val) {
                                            c = true;
                                            break;
                                        }
                                    }
                                    if (i == val) {
                                        inner:
                                        while (c == true) {
                                            if ((st = br.readLine()) != null) {
                                                i = 0;
                                                val = st.length();
                                                while ((st.charAt(i) != '#')) {

                                                    i++;
                                                    if (i == val) {
                                                        c = true;
                                                        break;
                                                    }
                                                }
                                                if (i == val) {
                                                    continue inner;
                                                } else {
                                                    if (st.charAt(i + 1) == '#') {
                                                        m_comment = 0;
                                                        if (st.charAt(i + 2) == '#') {
                                                            m_comment = 0;
                                                            i += 3;
                                                            if (i == val) {
                                                                k = 0;
                                                                continue o_outer;
                                                            } else {
                                                                k = 0;
                                                                continue outer;
                                                            }
                                                        }
                                                    }
                                                }

                                            } else {
                                                break o_outer;
                                            }
                                        }
                                    } else {
                                        if (st.charAt(i + 1) == '#') {
                                            m_comment = 0;
                                            if (st.charAt(i + 2) == '#') {
                                                m_comment = 0;
                                                i += 3;
                                                if (i == val) {
                                                    k = 0;
                                                    continue o_outer;
                                                } else {
                                                    k = 0;
                                                    continue outer;
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                            if (m_comment == 1) {
                                break inner1;
                            }
                        } else if (st.charAt(i) == ' ') {

                        } // for float like (.543)
                        else if (st.charAt(i) == '.') {
                            temp += st.charAt(i);
                            while (dig_matcher(st.charAt(i + 1))) {
                                i++;
                                temp += st.charAt(i);
                                if (i + 1 == val) {
                                    break;
                                }
                            }

                            if (temp != "") {
                                tv = new TokenValidate(temp, lineNum);
                                tv.validate();
                            }

                        } else if (st.charAt(i) == '+') {
                            temp += st.charAt(i);

                            if ((st.charAt(i + 1) == '+') || (st.charAt(i + 1) == '=')) {
                                i++;
                                temp += st.charAt(i);

                            }
                            if (temp != "") {
                                tv = new TokenValidate(temp, lineNum);
                                tv.validate();
                            }

                        } else if (st.charAt(i) == '-') {
                            temp += st.charAt(i);

                            if (st.charAt(i + 1) == '-' || (st.charAt(i + 1) == '=')) {
                                i++;
                                temp += st.charAt(i);

                            }

                            if (temp != "") {
                                tv = new TokenValidate(temp, lineNum);
                                tv.validate();
                            }

                        } else if (st.charAt(i) == '*') {
                            temp += st.charAt(i);

                            if (st.charAt(i + 1) == '*' || (st.charAt(i + 1) == '=')) {
                                i++;
                                temp += st.charAt(i);

                            }

                            if (temp != "") {
                                tv = new TokenValidate(temp, lineNum);
                                tv.validate();
                            }
                        } else if (st.charAt(i) == '/') {
                            temp += st.charAt(i);

                            if (st.charAt(i + 1) == '=') {
                                i++;
                                temp += st.charAt(i);

                            }

                            if (temp != "") {
                                tv = new TokenValidate(temp, lineNum);
                                tv.validate();
                            }

                        } else if (st.charAt(i) == '=') {
                            temp += st.charAt(i);

                            if (st.charAt(i + 1) == '=') {
                                i++;
                                temp += st.charAt(i);

                            }
                            if (st.charAt(i + 1) == '=') {
                                i++;
                                temp += st.charAt(i);

                            }

                            if (temp != "") {
                                tv = new TokenValidate(temp, lineNum);
                                tv.validate();
                            }

                        } else if (st.charAt(i) == '>' || st.charAt(i + 1) == '>') {
                            temp += st.charAt(i);

                            if (st.charAt(i + 1) == '=') {
                                i++;
                                temp += st.charAt(i);

                            }

                            if (temp != "") {
                                tv = new TokenValidate(temp, lineNum);
                                tv.validate();
                            }

                        } else if (st.charAt(i) == '<') {
                            temp += st.charAt(i);

                            if (st.charAt(i + 1) == '=' || st.charAt(i + 1) == '<') {
                                i++;
                                temp += st.charAt(i);

                            }

                            if (temp != "") {
                                tv = new TokenValidate(temp, lineNum);
                                tv.validate();
                            }

                        } else if (st.charAt(i) == '!') {
                            temp += st.charAt(i);

                            if (st.charAt(i + 1) == '=') {
                                i++;
                                temp += st.charAt(i);

                            }

                            if (temp != "") {
                                tv = new TokenValidate(temp, lineNum);
                                tv.validate();
                            }

                        } //for String checking "val"
                        else if (st.charAt(i) == '"') {
                            //temp+=st.charAt(i);
                            //System.out.println("printed in = "+temp);
                            temp += st.charAt(i);
                            if (i + 1 != val) {

                                while (st.charAt(i + 1) != '"') {
                                    i++;
                                    temp += st.charAt(i);
                                    if (st.charAt(i + 1) == ';')//there shoud be line change condition
                                    {
                                        break;
                                    }
                                    if (st.charAt(i) == '\\') {//concatenate next char withput checking
                                        i++;
                                        temp += st.charAt(i);
                                    }
                                }
                                if (st.charAt(i + 1) == '"') {//mandatoty so that, this value should be skiiped for the next iteration
                                    i++;
                                    temp += st.charAt(i);
                                }
                            }

                            if (temp != "") {

                                tv = new TokenValidate(temp, lineNum);
                                tv.validate();
                            }

                        } else if (st.charAt(i) == '\'') {
                            while (st.charAt(i + 1) != '\'') {

                                temp += st.charAt(i);
                                i++;
                                if (st.charAt(i) == '\\') {
                                    temp += st.charAt(i);
                                    i++;
                                    temp += st.charAt(i);
                                } else {
                                    temp += st.charAt(i);
                                }
                                if (i + 1 == val) {
                                    break;
                                }
                            }

                            if (i + 1 != val) {
                                if (st.charAt(i + 1) == '\'')//mandatoty so that, this value should be skiiped for the next iteration
                                {
                                    i++;
                                }
                                temp += st.charAt(i);
                            }

                            if (temp != "") {
                                tv = new TokenValidate(temp, lineNum);
                                tv.validate();
                            }

                        } else if (Breaker(st.charAt(i))) {
                            if (st.charAt(i) != ' ') {
                                if (temp != "") {
                                    tv = new TokenValidate(temp, lineNum);
                                    tv.validate();
                                }
                                tv = new TokenValidate("" + st.charAt(i), lineNum);
                                tv.validate();

                            } else {
                                if (temp != "") {
                                    tv = new TokenValidate(temp, lineNum);
                                    tv.validate();

                                }
                            }

                        } // for float like (.543, .7y6)
                        else if (st.charAt(i) == '.') {
                            temp += st.charAt(i);
                            if (dig_matcher(st.charAt(i + 1))) {
                                while (!Breaker(st.charAt(i + 1))) {
                                    i++;
                                    temp += st.charAt(i);
                                    tv = new TokenValidate(temp, lineNum);
                                    tv.validate();
                                }
                            }

                        } //for float(45.87, 76.98n4, 4h6.867)
                        else if (dig_matcher(st.charAt(i))) {
                            boolean id_approved = false;
                            temp += st.charAt(i);
                            while (st.charAt(i + 1) != '.' || !Breaker(st.charAt(i + 1))) { //chale until any breaker or point occured

                                if (st.charAt(i + 1) == '.') {
                                    break;
                                }

                                //concatenating other than digits(_,h,K)        
                                while (!dig_matcher(st.charAt(i + 1)) && !Breaker(st.charAt(i + 1))) {
                                    i++;
                                    temp += st.charAt(i);
                                    id_approved = true;
                                }

                                //concatenating digits
                                while (dig_matcher(st.charAt(i + 1))) {
                                    i++;
                                    temp += st.charAt(i);

                                }

                                if (Breaker(st.charAt(i + 1)))//for the cases like 542; 76,65;
                                {
                                    break;
                                }
                            }// main while ends

                            if (st.charAt(i + 1) == '.' && (dig_matcher(st.charAt(i + 2))) && id_approved == false) {//expression like 45.85,86.7f1,86.7n7
                                do {
                                    i++;
                                    temp += st.charAt(i);
                                } while (!Breaker(st.charAt(i + 1)));
                            } //optional but wrote to explain test case  
                            else { //means nothing need to be concatentaed after 54 because real value is: 45.g
                                //break;// . and g will be dealed with if condition differently in . and cgar respectivey

                            }
                            tv = new TokenValidate(temp, lineNum);
                            tv.validate();
                        } //float ends
                        else {
                            while (!Breaker(st.charAt(i))) {
                                temp += st.charAt(i);

                                i++;
                                if (i == val) {

                                    break;
                                }
                            }
                            i--;
                            if (temp != "") {
                                tv = new TokenValidate(temp, lineNum);
                                tv.validate();
                            }

                        }
                        k = 1;
                    }

                }

            }
        }

    }// split method ends

    public static boolean Breaker(char i) {

        switch (i) {

            case '.':
                return true;

            case '@':
                return true;

            case ';':
                return true;

            case ':':
                return true;

            case ',':
                return true;

            case '(':
                return true;

            case ')':
                return true;

            case '{':
                return true;

            case '}':
                return true;

            case '[':
                return true;

            case ']':
                return true;

            case ' ':
                return true;

            case '+':
                return true;

            case '-':
                return true;

            case '*':
                return true;

            case '/':
                return true;

            case '\\':
                return true;

            case '%':
                return true;

            case '>':
                return true;

            case '<':
                return true;

            case '!':
                return true;

            case '=':
                return true;

            case '?':
                return true;

            case '#':
                return true;

            case '"':
                return true;

            default:
                return false;
        }
    }

    public static boolean dig_matcher(char i) {

        switch (i) {
            case '0':
                return true;

            case '1':
                return true;

            case '2':
                return true;

            case '3':
                return true;

            case '4':
                return true;

            case '5':
                return true;

            case '6':
                return true;

            case '7':
                return true;

            case '8':
                return true;

            case '9':
                return true;

            default:
                return false;
        }
    }

}
