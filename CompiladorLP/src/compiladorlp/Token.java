/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorlp;

/**
 *
 * @author YiazmaT
 */
public class Token {
    //type == 1, <comandos>
    //type == 2, <variavel>
    //type == 3, <numero>, the personalID will be the number
    private int type;
    private String text;
    
    //personalID == 0 == "input"
    //personalID == 1 == "if"
    //personalID == 2 == "while"
    //personalID == 3 == "rem"
    //personalID == 4 == "print"
    //personalID == 5 == "goto"
    //personalID == 6 == "let"
    //personalID == 7 == variable
    private int personalID;

    public Token(int type, String text) {
        this.type = type;
        this.text = text;
    }

    public Token(int type, String text, int personalID) {
        this.type = type;
        this.text = text;
        this.personalID = personalID;
    }
    

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPersonalID() {
        return personalID;
    }

    public void setPersonalID(int personalID) {
        this.personalID = personalID;
    }
    
    
}
