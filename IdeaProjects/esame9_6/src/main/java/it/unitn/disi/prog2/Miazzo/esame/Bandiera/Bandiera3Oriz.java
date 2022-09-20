package it.unitn.disi.prog2.Miazzo.esame.Bandiera;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bandiera3Oriz extends Bandiera {

    @Override
    public Object clone(){
        Bandiera3Oriz b = (Bandiera3Oriz) super.clone();
        b.createCanvas(b.flagCanva.getGraphicsContext2D());
        return b;
    }
    public Bandiera3Oriz(String s, String c, Color[] colors){
        super(s, c, colors);
    }

    @Override
    void createCanvas(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0);
        gc.setFill(this.colors[0]);
        gc.fillRect(0,0,60,14);
        gc.strokeRect(0,0,60,14);
        gc.setFill(this.colors[1]);
        gc.fillRect(0,14,60,28);
        gc.strokeRect(0,14,60,28);
        gc.setFill(this.colors[2]);
        gc.fillRect(0,28,60,42);
        gc.strokeRect(0,28,60,42);
    }
}
