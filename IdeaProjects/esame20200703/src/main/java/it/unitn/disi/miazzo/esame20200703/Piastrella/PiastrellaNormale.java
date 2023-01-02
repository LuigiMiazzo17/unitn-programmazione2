package it.unitn.disi.miazzo.esame20200703.Piastrella;

import javafx.scene.paint.Color;

public class PiastrellaNormale extends Piastrella{

    public PiastrellaNormale(String codice, String materiale, double prezzo, double dimensione, boolean colorabile) {
        super(codice, materiale, prezzo, dimensione, colorabile);
        this.canvas.setOnMouseClicked(mouseEvent -> {
            if(colorabile){
                this.indiceColore = (this.indiceColore + 1) % Piastrella.COLORS.length;
                this.createCanvas();
            }
        });
        this.createCanvas();
    }

    @Override
    protected void createCanvas() {
        this.gc.setFill(Piastrella.COLORS[this.indiceColore]);
        this.gc.setStroke(Color.BLACK);
        this.gc.fillRect(0,0, Piastrella.CANVAS_DIM, Piastrella.CANVAS_DIM);
        this.gc.fillRect(0,0, Piastrella.CANVAS_DIM, Piastrella.CANVAS_DIM);
    }
}
