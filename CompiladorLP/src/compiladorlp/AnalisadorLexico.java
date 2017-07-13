/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorlp;

import java.io.File;

/**
 *
 * @author YiazmaT
 */
public class AnalisadorLexico {
    private int indice;
    private String [] linha;
    
    public Token nextToken(){
        Token t=null;
        
        //verify if still have tokens on the line
        try{
            linha[indice] = linha[indice];
        }
        catch(Exception e){
            return null;
        }
        
        //verify if the current token is a command
        t = this.verifyCommand(linha[indice]);
        if(t != null){
            this.indice++;
            return t;
        }
        
        //verify if the current token is a variable
        t = verifyVariable(linha[indice]);
        if(t != null){
            this.indice++;
            return t;
        }
        
        //verify if the current token is a number
        if(this.verifyNumber(linha[indice])){
            this.indice++;
            return new Token(3, "number", Integer.parseInt(linha[indice]));
        }
        
        //verify if the current token is an operator
        t = verifyOperator(linha[indice]);
        if(t != null){
            this.indice++;
            return t;
        }
        
        //verify if the current token is a bracket
        t = this.verifyBracket(linha[indice]);
        if(t != null){
            this.indice++;
            return t;
        }
        
        //verify if the current token is a comparator
        t = this.verifyComparator(linha[indice]);
        if(t != null){
            this.indice++;
            return t;
        }
        
        this.indice++;
        return t;
    }
    
    public void setNewLine(String linha){
        this.indice = 0;
        this.linha = linha.split(" ");
    }
    
    public Token verifyCommand(String s){
        if(s.equals("input")) return new Token(1, "input", 0);
        if(s.equals("if")) return new Token(1, "if", 1);
        if(s.equals("while")) return new Token(1, "while", 2);
        if(s.equals("rem")) return new Token(1, "rem", 3);
        if(s.equals("print")) return new Token(1, "print", 4);
        if(s.equals("goto")) return new Token(1, "goto", 5);
        if(s.equals("let")) return new Token(1, "let", 6);
        return null;
    }
    
    public Token verifyVariable(String s){
        Token t=null;
        if(s.length() != 1) return null;
        if(s.charAt(0) >= 97 && s.charAt(0) <= 122) return new Token(2, s, 7);
        return t;
    }
    
    public boolean verifyNumber(String s){
        int i;
        try{
            i = Integer.parseInt(s);
        }
        catch(NumberFormatException e){
            return false;
        }
        return true;
    }
    
    public Token verifyOperator(String s){
        Token t=null;
        if(s.equals("+")) return new Token(4, s, 8);
        if(s.equals("-")) return new Token(4, s, 9);
        if(s.equals("*")) return new Token(5, s, 10);
        if(s.equals("/")) return new Token(5, s, 11);
        return t;
    }
    
    public Token verifyBracket(String s){
        Token t = null;
        if(s.equals("(")) return new Token(6, s, 12);
        if(s.equals(")")) return new Token(6, s, 13);
        return t;
    }
    
    public Token verifyComparator(String s){
        Token t = null;
        if(s.equals(">")) return new Token(7, s, 14);
        if(s.equals("<")) return new Token(7, s, 15);
        if(s.equals(">=")) return new Token(7, s, 16);
        if(s.equals("<=")) return new Token(7, s, 17);
        if(s.equals("==")) return new Token(7, s, 18);
        if(s.equals("!=")) return new Token(7, s, 19);
        return t;
    }
}
