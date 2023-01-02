package it.unitn.disi.miazzo.esame20200703.Piastrella;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public abstract class Piastrella {
    protected static final double CANVAS_DIM = 130;
    protected static final Color[] COLORS = {Color.BROWN, Color.WHEAT, Color.TEAL, Color.DARKBLUE, Color.OLIVE, Color.GOLD};

    protected String codice;
    protected String materiale;
    protected double prezzo;
    protected double dimensione;
    protected boolean colorabile;
    protected int indiceColore;
    protected GraphicsContext gc;
    protected Canvas canvas;

    public Piastrella(String codice, String materiale, double prezzo, double dimensione, boolean colorabile){
        this.codice = codice;
        this.materiale = materiale;
        this.prezzo = prezzo;
        this.dimensione = dimensione;
        this.colorabile = colorabile;
        this.indiceColore = 0;
        this.canvas = new Canvas(Piastrella.CANVAS_DIM, Piastrella.CANVAS_DIM);
        this.gc = this.canvas.getGraphicsContext2D();
    }

    public void setupLabels(Label[] labels){
        labels[0].setText("Codice: " + this.codice);
        labels[1].setText("Materiale: " + this.materiale);
        labels[2].setText(String.format("Costo: %.2f", this.prezzo) + " Eur");
        labels[3].setText(String.format("Dimensione: %.1f", this.dimensione) + " cm");
        labels[4].setText("Scelta colore: " + (colorabile ? "Si" : "No"));
    }
    abstract void createCanvas();

    public Canvas getCanvas(){
        return this.canvas;
    }
}
