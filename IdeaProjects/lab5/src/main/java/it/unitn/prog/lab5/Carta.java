package it.unitn.prog.lab5;

import java.util.Comparator;
import java.util.Objects;

public class Carta{

    static class ComparatorBySemeThenValore implements Comparator<Carta> {
        @Override
        public int compare(Carta c1, Carta c2) {
            if(c1._seme.equals(c2._seme)){
                return c1._valore.toValue() - c2._valore.toValue();
            }
            return c1._seme.toValue() - c2._seme.toValue();
        }
    }
    
    static class ComparatorByValoreThenSeme implements Comparator<Carta>{
        @Override
        public int compare(Carta c1, Carta c2) {
            if(c1._valore.equals(c2._valore)){
                return c1._seme.toValue() - c2._seme.toValue();
            }
            return c1._valore.toValue() - c2._valore.toValue();
        }
    }

    static class ComparatorBySeme implements Comparator<Carta> {
        @Override
        public int compare(Carta c1, Carta c2) {
            return c1._seme.toValue() - c2._seme.toValue();
        }
    }

    static class ComparatorByValore implements Comparator<Carta>{
        @Override
        public int compare(Carta c1, Carta c2) {
            return c1._valore.toValue() - c2._valore.toValue();
        }
    }

    private final Valore _valore;
    private final Seme _seme;

    public enum Valore{
        A("A",0),
        _2("2", 1),
        _3("3", 2),
        _4("4", 3),
        _5("5", 4),
        _6("6",5),
        _7("7", 6),
        _8("8",7),
        _9("9", 8),
        _10("10", 9),
        J("J", 10),
        Q("Q",11),
        K("K", 12);

        private final String s;
        private final int val;
        Valore(String s, int val){ this.s = s; this.val = val; }

        public String toString(){ return this.s; }
        public int toValue(){return this.val; }
    }
    public enum Seme{
        C("Cuori", 0),
        Q("Quadri", 1),
        F("Fiori", 2),
        P("Picche", 3);

        private final String s;
        private final int val;

        Seme(String s, int val){ this.s = s; this.val = val; }

        public String toString(){ return this.s; }
        public int toValue(){return this.val; }
    }

    public Carta(Valore v, Seme s){
        this._valore = v;
        this._seme = s;
    }

    @Override
    public String toString() {
        return String.format("[%s %s]", this._valore, this._seme);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carta carta = (Carta) o;
        return this._valore == carta._valore && this._seme == carta._seme;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this._valore, this._seme);
    }
    /*
    @Override
    public int compareTo(Object o) {
        if (o == null || getClass() != o.getClass()) return -1;
        Carta carta = (Carta) o;
        if(this._seme.toValue() - carta._seme.toValue() == 0){
            return this._valore.toValue() - carta._valore.toValue();
        }
        return this._seme.toValue() - carta._seme.toValue();
    }*/

    public static boolean compareCarte(Carta c1, Carta c2, Comparator<Carta> c){
        return c.compare(c1, c2) >= 0;
    }
}
