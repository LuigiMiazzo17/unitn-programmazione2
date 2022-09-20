package it.unitn.prog.sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import java.util.Locale;
import java.util.Random;

public class Main extends Application {

    private final GridPane root = new GridPane();

    private final MyFigure[][] figures = new MyFigure[8][8];

    private int lastNumPressed;

    @Override
    public void start(Stage primaryStage) {

        initialize();

        primaryStage.setTitle("Laboratorio 6");
        Scene scene = new Scene(root, 425, 425);
        scene.setOnKeyTyped(event -> {
            String c = event.getCharacter();
            try {
                int i = Integer.parseInt(c);
                if (i >= 1 && i <= 8)
                    numPressed(i-1);
            } catch (Exception e) {
                c = c.toLowerCase(Locale.ROOT);
                if (c.equals("c")){
                    for(MyFigure f : figures[lastNumPressed]){
                        f.s.setStrokeWidth(1);
                    }
                    lastNumPressed = -1;
                }
                else if (c.equals("r")) {
                    lastNumPressed = -1;
                    for (MyFigure[] f : figures)
                        for (MyFigure x : f){
                            x.s.setFill(Color.WHITE);
                            x.s.setStrokeWidth(1);
                        }
                }
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void numPressed(int n){
        if(lastNumPressed == -1){
            lastNumPressed = n;
            for(MyFigure f : figures[n]){
                f.s.setStrokeWidth(1.4);
            }
            return;
        }
        figures[lastNumPressed][n].changeColor();
        for(MyFigure f : figures[lastNumPressed]){
            f.s.setStrokeWidth(1);
        }
        lastNumPressed = -1;
    }
    private void initialize(){
        lastNumPressed = -1;
        for(int i = 0; i < figures.length; i++){
            for(int j = 0; j < figures[0].length; j++){
                figures[i][j] = new MyFigure(i, j);
                root.add(figures[i][j].s, i, j);
            }
        }
        root.setBackground(Background.fill(Color.YELLOW));
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class MyFigure{
    public Shape s;
    public int r;
    public int c;

    public MyFigure(int r, int c){
        Random RNG = new Random();
        switch (RNG.nextInt(0, 3)) {
            case 0 -> s = new Rectangle(50, 50);
            case 1 -> s = new Circle(25);
            case 2 -> s = new Polygon(0, 0, 50, 0, 25, 50);
            default -> {}
        }
        assert s != null;
        s.setFill(Color.WHITE);
        s.setStroke(Color.BLACK);

        this.r = r;
        this.c = c;

        s.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> changeColor());
    }

    public void changeColor(){
        s.setFill(
                s.getFill() == Color.WHITE ?
                        ((r+c)%2 == 0 ? Color.RED : Color.GREEN)
                        :
                        Color.WHITE );
    }
}
