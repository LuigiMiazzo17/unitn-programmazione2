package it.unitn.prog.lab4;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import java.util.Random;

public class Main extends Application{

    /**
     * layouts
     */
    BorderPane root;
    HBox buttonBox;
    HBox shapeBox;

    /**
     * Interaction objects
     */
    Button buttonCambiaColore;
    Button buttonCambiaVerso;

    /**
     * Where figurs are stored
     */
    Shape[] figures;

    /**
     * state of the program
     */
    boolean currentVerso; // if verso == true then left else right
    int currentShape;

    @Override
    public void start(Stage primaryStage) throws Exception{

        initializeGame();

        primaryStage.setTitle("Laboratorio 4");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    /**
     * Initialize the game
     */
    private void initializeGame(){

        root = new BorderPane();
        buttonBox = new HBox();
        shapeBox = new HBox();

        buttonCambiaColore = new Button("Cambia colore");
        buttonCambiaColore.setOnAction(actionEvent ->  {
            currentShape = getNextFigure();
            figures[currentShape].setFill(nextRandomColor());
        });

        buttonCambiaVerso = new Button("=>");

        buttonCambiaVerso.setOnAction(actionEvent -> {
            if(currentVerso){
                currentVerso = false;
                buttonCambiaVerso.setText("<=");
            }
            else {
                currentVerso = true;
                buttonCambiaVerso.setText("=>");
            }
        });

        figures = new Shape[3];

        figures[0] = new Rectangle(50,50);
        figures[1] = new Circle(25);
        figures[2] = new Polygon();
        ((Polygon)figures[2]).getPoints().setAll(0.0,0.0,50.0,00.0,25.0,52.0);
        for(byte i = 0; i < 3; i++){
            figures[i].setStroke(Color.BLACK);
            figures[i].setFill(Color.WHITE);
        }

        buttonBox.getChildren().addAll(buttonCambiaColore, buttonCambiaVerso);
        buttonBox.setAlignment(Pos.CENTER);
        shapeBox.getChildren().addAll(figures[0], figures[1], figures[2]);
        shapeBox.setAlignment(Pos.CENTER);

        root.setBottom(buttonBox);
        root.setCenter(shapeBox);

        currentVerso = true;
        currentShape = 2;

    }

    /**
     * nextRandomColor();
     * @return a new random color
     */
    private static Color nextRandomColor(){
        Random RNG = new Random();

        return Color.rgb(RNG.nextInt(0,256), RNG.nextInt(0, 256), RNG.nextInt(0, 256));
    }

    /**
     * getNextFigure()
     * @return the next figure according to the state of the program
     */
    private int getNextFigure(){
         return (currentShape + (currentVerso ? 1 : 2)) % 3;
    }

    public static void main(String[] args) {
        launch(args);
    }

}