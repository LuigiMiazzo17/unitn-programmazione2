package it.unitn.prog.playground;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<A> a = new ArrayList<>();
        a.add(new A());
        a.add(new A());
        a.add(new A());
        for(A element : a){
            System.out.println(element);
        }
    }
}
class A{
    int x;
    static int COUNTER = 9;
    public A(){
        this.x = ++COUNTER;
    }
    public String toString(){
        return "" + x;
    }
}
