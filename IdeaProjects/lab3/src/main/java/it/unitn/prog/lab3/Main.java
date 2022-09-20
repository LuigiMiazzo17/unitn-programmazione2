package it.unitn.prog.lab3;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Main extends Application {

    /**
     * root pane of the scene
     */
    static BorderPane root = new BorderPane();

    /**
     * Containers parents for buttons and figures
     */
    static HBox buttonPane = new HBox();

    static HBox figurePane = new HBox();

    /**
     * Interaction components
     */
    static Button buttonGioca = new Button("Gioca!");

    static Button buttonCancella = new Button("Cancella");

    static Label label = new Label();

    @Override
    public void start(Stage primaryStage){

        initializeScene();

        primaryStage.setTitle("Gioco");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    /**
     * initalize all components of the scene
     */
    private void initializeScene(){

        //buttons gioca and cancella
        buttonGioca.setOnAction(actionEvent -> {
            Random RNG = new Random(System.nanoTime());
            Color[] c = {Color.RED, Color.BLUE, Color.YELLOW};
            Shape[] s = {new Circle(25, c[RNG.nextInt(c.length)]), new Rectangle(50, 50, c[RNG.nextInt(c.length)])};

            figurePane.getChildren().add(s[RNG.nextInt(s.length)]);

            if(figurePane.getChildren().size() == 2){
                buttonGioca.setDisable(true);
                if(((Shape)figurePane.getChildren().get(0)).getFill().equals(((Shape)figurePane.getChildren().get(1)).getFill())){
                    label.setText("Hai vinto!");
                }
                else
                    label.setText("Hai perso!");
            }
            else if(figurePane.getChildren().size() == 1)
                buttonCancella.setDisable(false);

        });

        buttonCancella.setOnAction(actionEvent -> {
            figurePane.getChildren().clear();
            buttonGioca.setDisable(false);
            buttonCancella.setDisable(true);
            label.setText("Giochiamo!");
        });

        buttonCancella.setDisable(true);

        buttonPane.getChildren().addAll(buttonGioca, buttonCancella);
        buttonPane.setAlignment(Pos.CENTER);
        root.setBottom(buttonPane);

        //text label
        label.setText("Giochiamo!");
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
        root.setTop(label);

        //figure pane
        figurePane.setAlignment(Pos.CENTER);
        root.setCenter(figurePane);

        try {
            FileInputStream input = new FileInputStream("/Users/luigi/IdeaProjects/lab3/src/img/gab.png");
            ImageView i = new ImageView(new Image(input));
            i.setFitHeight(150);
            i.setFitWidth(100);
            root.setLeft(i);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args){
        launch(args);
    }
}