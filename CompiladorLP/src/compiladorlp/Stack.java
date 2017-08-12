/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorlp;

import java.util.ArrayList;

/**
 *
 * @author YiazmaT
 */
public class Stack {
    private ArrayList<String> s;

    public Stack(){
       s = new ArrayList<>();
    }

    public boolean isEmpty(){
         return s.isEmpty();
    }

    public String pop(){
        String last;
        last = s.remove((s.size()- 1));
        return(last);
    }

    public void push(String x){
        s.add(x);
    }

    public String arrayTop(){
        return(s.get(s.size() -1));
    }
}
