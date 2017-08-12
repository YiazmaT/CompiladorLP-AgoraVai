/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorlp;

import java.io.File;
import static java.lang.System.exit;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author YiazmaT
 */
public class AnalisadorSintatico {
    AnalisadorLexico a;
    int lineNumber = 0;
    Stack limits;
    
    public void analisar(File f){
        a = new AnalisadorLexico(f);
        limits = new Stack();
        Token t;
        
        while(true){
            //t = null;
            t = a.nextToken();
            //end of file;
            if(t == null) break;
            
            //getting number line;
            if(t.getType() != 3){this.printErro(2);}
            this.lineNumber = t.getPersonalID();
            
            //getting first token
            t = a.nextToken();
            
            //finding the command
            switch(t.getPersonalID()){
                case 6:{ // let command
                    ArrayList<Token> tokens = new ArrayList<>();
                    t = a.nextToken();
                    if(t.getType() == 2){
                        t = a.nextToken();
                        if(t.getType() == 8){
                            while(true){
                                t = a.nextToken();
                                if(t.getType() == 9) break;
                                tokens.add(t);
                            }
                            boolean b = this.verifyExpression(tokens);
                            if(b == false) this.printErro(5);
                        }
                        else this.printErro(4);
                    }
                    else this.printErro(3);
                    break;
                }
                
                case 3:{ //rem command
                    a.readNewLine();
                    break;
                }
                
                case 4:
                case 0:{ //input and print command
                    t = a.nextToken();
                    if(t.getType() == 2){
                        t = a.nextToken();
                        if(t.getType() == 9){
                            //a.readNewLine();
                        }
                        else this.printErro(7);
                    }
                    else this.printErro(6);
                    break;
                }
                
                case 5:{ //goto command
                    t = a.nextToken();
                    if(t.getType() == 3){
                        t = a.nextToken();
                        if(t.getType() != 9) this.printErro(7);
                    }
                    else this.printErro(8);
                    break;
                }
                
                case 2:{ //while command
                    t = a.nextToken();
                    ArrayList<Token> ts = new ArrayList<Token>();
                    while(true){
                        if(t.getPersonalID()== 22) break;
                        ts.add(t);
                        t = a.nextToken();
                    }
                    boolean b = this.verifyBoolean(ts);
                    if(b == false) this.printErro(10);
                    limits.push("{");
                    a.readNewLine();
                    break;
                }
                
                case 23:{ //end while command
                    limits.pop();
                    a.readNewLine();
                    break;
                }
            }
            
        }
        
        if(limits.isEmpty()){
            System.out.println("Aceito");
        }
        else{
            System.out.println("Rejeitado");
        }
    }
    
    //verify if the expression is valid
    public boolean verifyExpression(ArrayList<Token> ex){
        if(ex.isEmpty()) return false;
        
        Token t;
        Stack stack = new Stack();
        
        while(!ex.isEmpty()){
            t = ex.get(0);
            if(t.getPersonalID() == 12){
                stack.push("(");
                ex.remove(0);
            }
            else{
                if(t.getType() == 3 || t.getType() == 2) ex.remove(0);
                if(ex.isEmpty() && stack.isEmpty()) return true;
                t = ex.get(0);
                
                if(t.getPersonalID() == 13 && !stack.isEmpty()){
                    stack.pop();
                    ex.remove(0);
                    if(ex.isEmpty() && stack.isEmpty()) return true;
                    if(ex.get(0).getType() == 4 || ex.get(0).getType() == 5) ex.remove(0);
                    if(ex.isEmpty()){
                        this.printErro(5);
                    }
                    else{
                        if(ex.get(0).getType() != 2 && ex.get(0).getType() != 3 && ex.get(0).getType() != 6) this.printErro(5);
                    }
                }
                else if(t.getType() == 4 || t.getType() == 5){
                    ex.remove(0);
                    if(ex.isEmpty()) return false;
                }
                else{
                    this.printErro(1);
                }
            }
        }
        
        if(!stack.isEmpty()){
            this.printErro(0);
        }
        return true;
    }
    
    public boolean verifyBoolean(ArrayList<Token> tokens){
        ArrayList<Token> ex1 = new ArrayList<Token>();
        ArrayList<Token> ex2 = new ArrayList<Token>();
        Token t;
        while(true){
            if(tokens.isEmpty()) this.printErro(9);
            t = tokens.get(0);
            if(t.getType() == 7){
                tokens.remove(0);
                break;
            }
            ex1.add(t);
            tokens.remove(0);
        }
        
        while(!tokens.isEmpty()){
            t = tokens.get(0);
            ex2.add(t);
            tokens.remove(0);
        }
        
        if(ex1.isEmpty() || ex1.isEmpty()){
            this.printErro(5);
        }
        else{
            boolean b1 = this.verifyExpression(ex1);
            boolean b2 = this.verifyExpression(ex2);
            if(b1 && b2){
                return true;
            }
        }
        return false;
    }
    
    //error print
    public void printErro(int erro){
        switch(erro){
            case 0: JOptionPane.showMessageDialog(null, "Espera-se o símbolo \")\" na linha: "+lineNumber+"."); break;
            case 1: JOptionPane.showMessageDialog(null, "Espera-se um operador na linha: "+lineNumber+"."); break;
            case 2: JOptionPane.showMessageDialog(null, "Espera-se o número da linha! Linha anterior: "+lineNumber+"."); break;
            case 3: JOptionPane.showMessageDialog(null, "Espera-se o uma variável na linha: "+lineNumber+"."); break;
            case 4: JOptionPane.showMessageDialog(null, "Espera-se o símbolo \"=\" na linha: "+lineNumber+"."); break;
            case 5: JOptionPane.showMessageDialog(null, "Expressão inválida na linha: "+lineNumber+"."); break;
            case 6: JOptionPane.showMessageDialog(null, "Espera-se uma variável na linha: "+lineNumber+"."); break;            
            case 7: JOptionPane.showMessageDialog(null, "Espera-se o símbolo \";\" na linha: "+lineNumber+"."); break;            
            case 8: JOptionPane.showMessageDialog(null, "Espera-se um número na linha: "+lineNumber+"."); break;                      
            case 9: JOptionPane.showMessageDialog(null, "Espera-se um comparador na linha: "+lineNumber+"."); break;                      
            case 10: JOptionPane.showMessageDialog(null, "Expressão de comparação inválida na linha: "+lineNumber+"."); break;                      
        }
        exit(1);
    }
}
