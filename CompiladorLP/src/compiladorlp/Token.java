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
    //type == 4, + or -
    //type == 5, * or /
    //type == 6, ( or )
    //type == 7, <comparador>
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
    //personalID == 8 == +
    //personalID == 9 == -
    //personalID == 10 == *
    //personalID == 11 == /
    //personalID == 12 == (
    //personalID == 13 == )
    //personalID == 14 == >
    //personalID == 15 == <
    //personalID == 16 == >=
    //personalID == 17 == <=
    //personalID == 18 == ==
    //personalID == 19 == !=
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
