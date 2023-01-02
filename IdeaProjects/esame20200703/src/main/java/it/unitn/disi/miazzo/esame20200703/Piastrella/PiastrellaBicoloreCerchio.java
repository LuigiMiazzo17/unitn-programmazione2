package it.unitn.disi.miazzo.esame20200703.Piastrella;

import javafx.scene.paint.Color;

public class PiastrellaBicoloreCerchio extends PiastrellaBicolore{

    public PiastrellaBicoloreCerchio(String codice, String materiale, double prezzo, double dimensione, boolean colorabile) {
        super(codice, materiale, prezzo, dimensione, colorabile);
    }

    @Override
    boolean isInsideShape(double x, double y) {
        return (Math.sqrt(Math.pow((x - Piastrella.CANVAS_DIM / 2), 2) + Math.pow(y - Piastrella.CANVAS_DIM / 2, 2))) <= Piastrella.CANVAS_DIM / 4;
    }

    @Override
    void createCanvas() {
        this.gc.setFill(Piastrella.COLORS[this.indiceColore]);
        this.gc.setStroke(Color.BLACK);
        this.gc.fillRect(0,0, Piastrella.CANVAS_DIM, Piastrella.CANVAS_DIM);
        this.gc.strokeRect(0,0, Piastrella.CANVAS_DIM, Piastrella.CANVAS_DIM);
        this.gc.setFill((Piastrella.COLORS[this.indiceSecondoColore]));
        this.gc.fillOval(Piastrella.CANVAS_DIM / 4,Piastrella.CANVAS_DIM / 4,Piastrella.CANVAS_DIM / 2,Piastrella.CANVAS_DIM / 2);
        this.gc.strokeOval(Piastrella.CANVAS_DIM / 4,Piastrella.CANVAS_DIM / 4,Piastrella.CANVAS_DIM / 2,Piastrella.CANVAS_DIM / 2);
    }
}
