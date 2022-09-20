package it.unitn.disi.prog2.Miazzo.esame.Bandiera;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BandieraTriang extends Bandiera{

    @Override
    public Object clone(){
        BandieraTriang b = (BandieraTriang) super.clone();
        b.createCanvas(b.flagCanva.getGraphicsContext2D());
        return b;
    }
    public BandieraTriang(String s, String c, Color[] colors){
        super(s, c, colors);
    }

    @Override
    void createCanvas(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0);
        gc.setFill(this.colors[0]);
        gc.fillRect(0,0,60,21);
        gc.strokeRect(0,0,60,21);
        gc.setFill(this.colors[1]);
        gc.fillRect(0,21,60,42);
        gc.strokeRect(0,21,60,42);
        gc.setFill(this.colors[2]);
        gc.fillPolygon(new double[]{0, 21, 0}, new double[]{0, 21, 42}, 3);
    }

}
