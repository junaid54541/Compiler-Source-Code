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
public class SymbolTable {
    public String name, type, rType;
        public int scope;
        public SymbolTable(String Name, String Type, String rtype, int Scope)
        {
            name = Name;
            type = Type;
            scope = Scope;
            rType = rtype;
        }
}
