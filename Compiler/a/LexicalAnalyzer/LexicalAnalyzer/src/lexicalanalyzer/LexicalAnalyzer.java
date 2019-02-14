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
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ABC
 */
public class LexicalAnalyzer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String path = "C:\\Users\\ABC\\Desktop\\src_file.txt";
        breaker lexeme = new breaker(path);
        lexeme.split();
        TokenValidate tv = new TokenValidate();
        Token tmp=new Token();
        tmp.C_p="$";
        tmp.V_p="$";
        tmp.lineNumber=tv.TokenList.get(tv.TokenList.size()-1).lineNumber;
        tv.TokenList.add(tmp);
        CFG SA=new CFG();
        boolean a;
        a = SA.check();
        
        if(a)
        {
            System.out.println("There is no Error in Syntax Analysis");
        }
        else
        {
         
            System.out.println("There is Error in Syntax Analysis at Line Number: "+tv.TokenList.get(SA.index).lineNumber);

        }

        for (int i = 0; i < tv.TokenList.size(); i++) {
            System.out.println("(" + tv.TokenList.get(i).C_p + ", " + tv.TokenList.get(i).V_p + ", " + tv.TokenList.get(i).lineNumber + ")");

        }

        FileWriter writer = new FileWriter("C:\\Users\\ABC\\Desktop\\output.txt");
        for (Token str : tv.TokenList) {
            writer.write(str.C_p + "," + str.V_p + "," + str.lineNumber + "\r\n");

        }

        writer.close();

        
        
    }
}
