package it.unitn.disi.miazzo.esame20200703;

import it.unitn.disi.miazzo.esame20200703.Piastrella.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private Label[] informationLabels;
    private int currentPiastrellaShowing;
    private ArrayList<Piastrella> piastrelle;

    private void initPiastrelle(){
        currentPiastrellaShowing = 0;
        piastrelle = new ArrayList<>();
        piastrelle.add(new PiastrellaNormale("P1", "ceramica", 50.0, 50.0, false));
        piastrelle.add(new PiastrellaNormale("P2", "laminato", 20.0, 80.0, true));
        piastrelle.add(new PiastrellaNormale("P3", "terracotta", 50.0, 40.0, true));
        piastrelle.add(new PiastrellaBicoloreQuadrato("B1", "laminato", 100.0, 40.0, false));
        piastrelle.add(new PiastrellaBicoloreCerchio("B2", "ceramica", 120.0, 90.0, true));
        piastrelle.add(new PiastrellaBicoloreQuadrato("B3", "terracotta", 140.0, 50.0, true));
    }

    @Override
    public void start(Stage primaryStage) {
        initPiastrelle();
        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);
        VBox labelBox = new VBox();

        HBox boxForCanva = new HBox();
        boxForCanva.setAlignment(Pos.CENTER);
        boxForCanva.setPadding(new Insets(10));
        boxForCanva.getChildren().add(piastrelle.get(0).getCanvas());

        informationLabels = new Label[5];
        for(int i = 0; i < 5; i++){
            informationLabels[i] = new Label();
            labelBox.getChildren().add(informationLabels[i]);
        }
        piastrelle.get(0).setupLabels(informationLabels);




        Button b1 = new Button("<prev");
        b1.setDisable(true);
        Button b2 = new Button("next>");

        b1.setOnAction(actionEvent -> {
            currentPiastrellaShowing--;
            if(currentPiastrellaShowing == 0){
                b1.setDisable(true);
            }else if(currentPiastrellaShowing == piastrelle.size() - 2){
                b2.setDisable(false);
            }
            boxForCanva.getChildren().clear();
            boxForCanva.getChildren().add(piastrelle.get(currentPiastrellaShowing).getCanvas());
            piastrelle.get(currentPiastrellaShowing).setupLabels(informationLabels);
        });

        b2.setOnAction(actionEvent -> {
            currentPiastrellaShowing++;
            if(currentPiastrellaShowing == piastrelle.size() -1){
                b2.setDisable(true);
            }else if(currentPiastrellaShowing == 1){
                b1.setDisable(false);
            }
            boxForCanva.getChildren().clear();
            boxForCanva.getChildren().add(piastrelle.get(currentPiastrellaShowing).getCanvas());
            piastrelle.get(currentPiastrellaShowing).setupLabels(informationLabels);
        });

        HBox bbox = new HBox(b1, b2);
        bbox.setPadding(new Insets(20));
        VBox newV = new VBox();
        newV.setAlignment(Pos.CENTER);
        bbox.setAlignment(Pos.CENTER);
        newV.getChildren().addAll(labelBox, bbox);

        root.getChildren().addAll(newV, boxForCanva);

        primaryStage.setTitle("Catalogo Piastelle");
        primaryStage.setScene(new Scene(root, 300, 180));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}