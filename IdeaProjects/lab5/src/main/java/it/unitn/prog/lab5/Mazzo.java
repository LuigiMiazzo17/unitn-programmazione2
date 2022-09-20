package it.unitn.prog.lab5;

import java.util.Comparator;
import java.util.LinkedList;

public class Mazzo extends LinkedList<Carta> {

    public Mazzo(){
        for(Carta.Seme s : Carta.Seme.values())
            for(Carta.Valore v : Carta.Valore.values())
                this.add(new Carta(v, s));
    }

    public void print(){
        for(Carta c : this){
            System.out.println(c.toString());
        }
    }
}

