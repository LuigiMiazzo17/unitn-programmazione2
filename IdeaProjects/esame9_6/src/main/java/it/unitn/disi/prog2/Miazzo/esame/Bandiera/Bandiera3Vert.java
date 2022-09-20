package it.unitn.disi.prog2.Miazzo.esame.Bandiera;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bandiera3Vert extends Bandiera{

    @Override
    public Object clone(){
        Bandiera3Vert b = (Bandiera3Vert) super.clone();
        b.createCanvas(b.flagCanva.getGraphicsContext2D());
        return b;
    }
    public Bandiera3Vert(String s, String c, Color[] colors){
        super(s, c, colors);
    }

    @Override
    void createCanvas(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0);
        gc.setFill(this.colors[0]);
        gc.fillRect(0,0,20,42);
        gc.strokeRect(0,0,20,42);
        gc.setFill(this.colors[1]);
        gc.fillRect(20,0,40,42);
        gc.strokeRect(20,0,40,42);
        gc.setFill(this.colors[2]);
        gc.fillRect(40,0,60,42);
        gc.strokeRect(40,0,60,42);
    }
}
