package it.unitn.disi.prog2.Miazzo.esame.Bandiera;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Comparator;

public abstract class Bandiera implements Cloneable {
    @Override
    public Object clone(){
        Object tmp = null;
        try{
            tmp = super.clone();
        }catch (CloneNotSupportedException e) {return null;}
        Bandiera b = (Bandiera)tmp;
        b.state = this.state;
        b.capitol = this.capitol;
        b.colors = this.colors.clone();
        return b;
    }
    public static class ComparatorByState implements Comparator<Bandiera> {
        @Override
        public int compare(Bandiera o1, Bandiera o2) {
            return o1.getState().compareTo(o2.getState());
        }
    }
    public static class ComparatorByCapitol implements Comparator<Bandiera> {
        @Override
        public int compare(Bandiera o1, Bandiera o2) {
            return o1.getCapitol().compareTo(o2.getCapitol());
        }
    }
    protected String state;
    protected String capitol;

    protected Color[] colors;

    protected Canvas flagCanva;

    public Bandiera(String s, String c, Color[] colors){
        this.state = s;
        this.capitol = c;
        this.colors = colors;
        this.flagCanva = new Canvas(60, 42);
        this.createCanvas(this.flagCanva.getGraphicsContext2D());
    }

    public String getState() { return this.state; }
    public String getCapitol() { return this.capitol; }
    public Canvas getCanvas() { return this.flagCanva; };

    abstract void createCanvas(GraphicsContext gc);
}
