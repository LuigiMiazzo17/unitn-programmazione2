package it.unitn.disi.prog2.Miazzo.esame.Bandiera;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bandiera2Oriz extends Bandiera{

    @Override
    public Object clone(){
        Bandiera2Oriz b = (Bandiera2Oriz) super.clone();
        b.createCanvas(b.flagCanva.getGraphicsContext2D());
        return b;
    }

    public Bandiera2Oriz(String s, String c, Color[] colors){
        super(s, c, colors);
    }

    @Override
    void createCanvas(GraphicsContext gc){
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0);
        gc.setFill(this.colors[0]);
        gc.fillRect(0,0,60,21);
        gc.strokeRect(0,0,60,21);
        gc.setFill(this.colors[1]);
        gc.fillRect(0,21,60,42);
        gc.strokeRect(0,21,60,42);
    }
}
