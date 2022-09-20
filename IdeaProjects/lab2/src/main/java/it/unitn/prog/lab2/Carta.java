package it.unitn.prog.lab2;

public class Carta {

    // Enumerators for Carta

    public enum Seme {
        C("Cuori"),
        Q("Quadri"),
        F("Fiori"),
        P("Picche");

        private String valore;

        public String getSeme() {return this.valore; };

        private Seme(String seme) {this.valore = seme; };
    };
    public enum Numero {
        A("Asso"),
        _2("2"),
        _3("3"),
        _4("4"),
        _5("5"),
        _6("6"),
        _7("7"),
        _8("8"),
        _9("9"),
        _10("10"),
        J("Jack"),
        Q("Donna"),
        K("Re");
        private String valore;
        public String getNumero() {return this.valore; };
        private Numero(String numero) {this.valore = numero; }; };

    // property

    private Seme seme;
    private Numero numero;

    // constructor

    public Carta(Seme s, Numero n){
        this.seme = s;
        this.numero = n;
    }

    // methods

    public String getSeme(){
        return this.seme.getSeme();
    }

    public String getNumero(){
        return this.numero.getNumero();
    }

    public String getCarta(){
        return this.getSeme() + "-" + this.getNumero();
    }

    @Override
    public boolean equals(Object c){
        return (this.seme == ((Carta)c).seme && this.numero == ((Carta)c).numero);
    }
}
