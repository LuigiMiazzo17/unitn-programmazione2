package it.unitn.prog.lab2;

import java.util.Collections;
import java.util.LinkedList;

public class Mazzo extends LinkedList<Carta> {

    public Mazzo() {

        for (Carta.Seme seme : Carta.Seme.values()) {
            for (Carta.Numero numero : Carta.Numero.values()) {
                this.add(new Carta(seme, numero));
                this.add(new Carta(seme, numero));
            }
        }
        this.shuffle();
    }

    public void stampa(int n) {
        for (int i = 0; i < n; i++)
            System.out.println("Carta numero " + i + ": " + this.get(i).getSeme() + "-" + this.get(i).getNumero());

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++)
                if (this.get(i).equals(this.get(j))) {
                    System.out.println("Una carta uguale è stata trovata: " + this.get(i).getCarta() + ", hai vinto!");
                    return;
                }
        }

        System.out.println("Hai perso! :(");
    }

    public void shuffle(){
        Collections.shuffle(this);
    }

    public void stampa(){ stampa(10); }
}
