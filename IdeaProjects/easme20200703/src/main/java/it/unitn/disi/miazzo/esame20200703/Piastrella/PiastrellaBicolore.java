package it.unitn.disi.miazzo.esame20200703.Piastrella;

public abstract class PiastrellaBicolore extends Piastrella{

    protected int indiceSecondoColore;

    public PiastrellaBicolore(String codice, String materiale, double prezzo, double dimensione, boolean colorabile) {
        super(codice, materiale, prezzo, dimensione, colorabile);
        indiceSecondoColore = 1;
        this.createCanvas();
        this.canvas.setOnMouseClicked(mouseEvent -> {
            if(colorabile){
                if(isInsideShape(mouseEvent.getX(), mouseEvent.getY()))
                    this.indiceSecondoColore = (this.indiceSecondoColore + 1) % Piastrella.COLORS.length;
                else
                    this.indiceColore = (this.indiceColore + 1) % Piastrella.COLORS.length;
                this.createCanvas();
            }
        });
    }

    abstract boolean isInsideShape(double x, double y);
}
