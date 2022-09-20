package it.unitn.prog.lab5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main extends Application {
    BorderPane root;
    HBox VBoxBox;
    VBox cartaCorrenteBox;
    VBox cartaPescataBox;
    VBox punteggioBox;

    Label labTextCorrente;
    Label labCartaCorrente;

    Label labTextPescata;
    Label labCartaPescata;

    Label labVincitaPerdita;
    Label labPunteggio;
    Label labCriterioOrdinamento;
    Label labPunteggioPartita;
    Label labKDRateo;

    Button button;

    int punti;
    int partiteVinte;
    int partitePerse;
    Mazzo mazzo;


    Carta cartaCorrente;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        initialize();

        primaryStage.setTitle("Laboratorio 5");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    private void initialize() throws FileNotFoundException {
        root = new BorderPane();
        VBoxBox = new HBox();

        labTextCorrente = new Label("Carta corrente");
        labTextCorrente.setAlignment(Pos.CENTER);
        labCartaCorrente = new Label("[]");
        labCartaCorrente.setAlignment(Pos.CENTER);
        cartaCorrenteBox = new VBox();
        cartaCorrenteBox.setAlignment(Pos.CENTER);
        cartaCorrenteBox.setPadding(new Insets(15.0));
        cartaCorrenteBox.getChildren().addAll(labTextCorrente, labCartaCorrente);

        labTextPescata = new Label("Carta Pescata");
        labTextPescata.setAlignment(Pos.CENTER);
        labCartaPescata = new Label("[]");
        labCartaPescata.setAlignment(Pos.CENTER);
        cartaPescataBox = new VBox();
        cartaPescataBox.setAlignment(Pos.CENTER);
        cartaPescataBox.setPadding(new Insets(15.0));
        cartaPescataBox.getChildren().addAll(labTextPescata, labCartaPescata);

        VBoxBox.getChildren().addAll(cartaCorrenteBox, cartaPescataBox);
        VBoxBox.setAlignment(Pos.CENTER);
        root.setTop(VBoxBox);

        labVincitaPerdita = new Label("Giochiamo!");
        labVincitaPerdita.setAlignment(Pos.CENTER);
        punti = 5;
        labPunteggio = new Label("Punti: "+ punti);
        labPunteggio.setAlignment(Pos.CENTER);
        labCriterioOrdinamento = new Label("Criterio ordinamento: ");
        labCriterioOrdinamento.setAlignment(Pos.CENTER);
        partiteVinte = 0;
        labPunteggioPartita = new Label("Partite vinte: 0");
        labPunteggioPartita.setAlignment(Pos.CENTER);
        partitePerse = 0;
        labKDRateo = new Label("K/D Rateo: 0");
        labKDRateo.setAlignment(Pos.CENTER);
        punteggioBox = new VBox();
        punteggioBox.getChildren().addAll(labVincitaPerdita, labPunteggio, labCriterioOrdinamento, labPunteggioPartita, labKDRateo);
        punteggioBox.setAlignment(Pos.CENTER);
        root.setCenter(punteggioBox);


        button = new Button("Gioca");
        BorderPane.setAlignment(button, Pos.CENTER);
        root.setBottom(button);


        mazzo = new Mazzo();
        Collections.shuffle(mazzo);
        cartaCorrente = mazzo.pop();
        labCartaCorrente.setText(cartaCorrente.toString());

        ImageView i = new ImageView(new Image(new FileInputStream("/Users/luigi/IdeaProjects/lab5/src/img/gab.png")));
        i.setFitWidth(100);
        i.setFitHeight(175);
        root.setLeft(i);

        button.setOnAction(actionEvent -> {

            if(cartaCorrente == null){
                mazzo = new Mazzo();
                Collections.shuffle(mazzo);
                cartaCorrente = mazzo.pop();
                labCartaCorrente.setText("[]");
                labCartaPescata.setText("[]");
                labPunteggio.setText("Punti: 5");
                return;
            }

            Random RNG = new Random();
            Carta cartaPescata = mazzo.pop();
            if(cartaPescata == null)
                loseGame();

            mazzo.sort(new Carta.ComparatorBySemeThenValore());

            labCartaCorrente.setText(cartaCorrente.toString());
            assert cartaPescata != null;
            labCartaPescata.setText(cartaPescata.toString());

            if(RNG.nextBoolean()){
                labCriterioOrdinamento.setText("Criterio ordinamento: per valore.");
                if(Carta.compareCarte(cartaCorrente, cartaPescata, new Carta.ComparatorByValore()))
                    winRound();
                else
                    loseRound();
            }
            else{
                labCriterioOrdinamento.setText("Criterio ordinamento: per seme.");
                if(Carta.compareCarte(cartaCorrente, cartaPescata, new Carta.ComparatorBySeme()))
                    winRound();
                else
                    loseRound();

            }
            labPunteggio.setText("Punti: " + punti);

            if (punti == 6){
                winGame();
                return;
            }
            else if(punti == 0){
                loseGame();
                return;
            }

            cartaCorrente = cartaPescata;

        });
    }

    private void winRound(){
        labVincitaPerdita.setText("Hai vinto il round!");
        punti += 1;
    }
    private void loseRound(){
        labVincitaPerdita.setText("Hai perso il round!");
        punti -= 1;
    }
    private void winGame(){
        labVincitaPerdita.setText("Hai vinto la partita!");
        punti = 5;
        partiteVinte++;
        labPunteggioPartita.setText("Partite vinte: " + partiteVinte);
        labKDRateo.setText("K/D Rateo: " + (((float)partiteVinte)/((float)(partitePerse == 0 ? 1 : partitePerse))));
        cartaCorrente = null;
    }
    private void loseGame(){
        labVincitaPerdita.setText("Hai perso la partita!");
        punti = 5;
        partitePerse++;
        labKDRateo.setText("K/D Rateo: " + (((float)partiteVinte)/((float)partitePerse)));
        cartaCorrente = null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}