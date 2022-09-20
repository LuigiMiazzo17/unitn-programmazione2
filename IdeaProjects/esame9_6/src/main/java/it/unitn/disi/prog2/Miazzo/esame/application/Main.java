package it.unitn.disi.prog2.Miazzo.esame.application;

import it.unitn.disi.prog2.Miazzo.esame.Bandiera.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {

    private static ArrayList<Bandiera> flags;
    private static GridPane gameGrid;
    private static ArrayList<TextField> textFields;

    private static ArrayList<Integer> orderList;

    private static Button controlla;

    private static ArrayList<Bandiera> rndFlags;

    private static void setupFlags(){
        flags = new ArrayList<>();

        flags.add(new Bandiera2Vert("Algeria", "Algiers", new Color[] {Color.GREEN, Color.WHITE}));
        flags.add(new Bandiera3Oriz("Armenia","Yerevan", new Color[]{Color.RED,Color.BLUE, Color.ORANGE}));
        flags.add(new Bandiera3Vert("Chad","N'Djamena", new Color[]{Color.BLUE,Color.YELLOW, Color.RED}));
        flags.add(new BandieraTriang("Czech Republic","Prague", new Color[]{Color.WHITE, Color.RED, Color.BLUE}));
        flags.add(new BandieraTriang("Djibouti","Djibouti", new Color[]{Color.CYAN, Color.GREEN, Color.WHITE}));
        flags.add(new Bandiera3Vert("Gabon","Libreville", new Color[]{Color.GREEN, Color.YELLOW, Color.BLUE}));
        flags.add(new Bandiera2Oriz("Indonesia","Jakarta", new Color[]{Color.RED, Color.WHITE}));
        flags.add(new Bandiera3Oriz("Lithuania","Vilnius", new Color[]{Color.YELLOW, Color.GREEN, Color.RED}));
        flags.add(new Bandiera2Vert("Malta","La Valletta",new Color[]{Color.WHITE, Color.RED}));
        flags.add(new Bandiera2Oriz("Ukraine","Kiev", new Color[]{Color.BLUE, Color.YELLOW}));
    }

    @Override
    public void start(Stage primaryStage) {

        setupFlags();

        Stage listStage = new Stage();
        Stage gameStage = new Stage();

        setupListView(listStage, primaryStage, gameStage);

        setupGameView(gameStage, primaryStage, listStage);

        setupPrimaryView(primaryStage, listStage, gameStage);
    }

    private static void setupPrimaryView(Stage primaryStage, Stage listStage, Stage gameStage){
        Button buttonMostra = new Button("Mostra tutto");
        buttonMostra.setPadding(new Insets(5.0));
        buttonMostra.setOnAction(actionEvent -> listStage.show());

        Button buttonGioca = new Button("Gioca");
        buttonGioca.setPadding(new Insets(5.0));
        buttonGioca.setOnAction(actionEvent -> {
            layoutFlagsGame(Main.gameGrid, Main.textFields, Main.orderList, Main.controlla);
            gameStage.show();
        });

        VBox box = new VBox(buttonMostra, buttonGioca);
        box.setAlignment(Pos.CENTER);

        StackPane root = new StackPane(box);
        primaryStage.setTitle("Luigi Miazzo");
        primaryStage.setScene(new Scene(root, 300, 150));
        primaryStage.show();
    }
    private static void setupListView(Stage listStage, Stage primaryStage, Stage gameStage){

        BorderPane listRoot = new BorderPane();
        GridPane listGridLayout = new GridPane();
        listStage.setTitle("Lista dei dati");
        layoutFlagsList(listGridLayout, flags, new Bandiera.ComparatorByState());

        HBox box = new HBox();
        Button sortByState = new Button("Ordina per Stato");
        sortByState.setOnAction(actionEvent -> {
            listGridLayout.getChildren().clear();
            layoutFlagsList(listGridLayout, flags, new Bandiera.ComparatorByState());
        });


        Button sortByCapitol = new Button("Ordina per Capitale");
        sortByCapitol.setOnAction(actionEvent -> {
            listGridLayout.getChildren().clear();
            layoutFlagsList(listGridLayout, flags, new Bandiera.ComparatorByCapitol());
        });
        box.getChildren().addAll(sortByState, sortByCapitol);
        box.setAlignment(Pos.CENTER);

        listRoot.setBottom(box);
        listRoot.setCenter(listGridLayout);
        listStage.setScene(new Scene(listRoot,250, 445));
        listStage.onShowingProperty().set(windowEvent -> {
            if (gameStage.isShowing())
                gameStage.close();
        });
        listStage.setOnCloseRequest(event -> primaryStage.show());
        listStage.setOnShowing(windowEvent -> {
            listGridLayout.getChildren().clear();
            layoutFlagsList(listGridLayout, flags, new Bandiera.ComparatorByState());
        });
    }
    private static void layoutFlagsList(GridPane listGridLayout, ArrayList<Bandiera> flags, Comparator<Bandiera> c){
        flags.sort(c);
        for(int i = 0; i < 10; i++) {
            listGridLayout.add(flags.get(i).getCanvas(), 0, i);
            listGridLayout.add(new Label(flags.get(i).getState()), 1, i);
            listGridLayout.add(new Label(flags.get(i).getCapitol()), 2, i);
        }
    }
    private static void setupGameView(Stage gameStage, Stage primaryStage, Stage listStage){
        Main.gameGrid = new GridPane();
        Main.textFields = new ArrayList<>();
        Main.textFields.add(new TextField());
        Main.textFields.add(new TextField());
        Main.textFields.add(new TextField());
        gameStage.setTitle("Gioca!");
        Main.orderList = new ArrayList<>();

        AtomicBoolean gameEnded = new AtomicBoolean(false);
        Main.controlla = new Button("Controlla");
        controlla.setOnAction(actionEvent -> {
            if(gameEnded.get()){
                gameEnded.set(false);
                for(TextField t : Main.textFields)
                    t.setText("");
                Main.controlla.setText("Controlla");
                layoutFlagsGame(Main.gameGrid, Main.textFields, Main.orderList, Main.controlla);
                return;
            }
            boolean winCondition = true;
            for (int i = 0; i < 3; i++) {
                String c = textFields.get(i).getText();
                String e = Main.rndFlags.get(i).getCapitol();

                if(!c.equals(e))
                    winCondition = false;

            }
            gameEnded.set(true);
            if(winCondition){
                Main.controlla.setText("Hai vinto, rigioca!");
            }
            else {
                Main.controlla.setText("Hai perso, rigioca!");
            }
        });

        gameStage.setScene(new Scene(Main.gameGrid,300, 200));
        gameStage.onShowingProperty().set(windowEvent -> {
            if (listStage.isShowing())
                listStage.close();
        });
        gameStage.setOnCloseRequest(event -> primaryStage.show());
    }
    private static ArrayList<Bandiera> randomFlags(){

        ArrayList<Bandiera> l = new ArrayList<>();
        for (Bandiera f : Main.flags)
            l.add((Bandiera)f.clone());

        Collections.shuffle(l);
        l.subList(0,7).clear();
        return l;
    }

    private static void layoutFlagsGame(GridPane gameGridLayout, ArrayList<TextField> textFields, ArrayList<Integer> orderList, Button but){
        gameGridLayout.getChildren().clear();
        Main.rndFlags = randomFlags();

        for(TextField t : Main.textFields){
            t.setOnKeyTyped(keyEvent -> {
                String c = keyEvent.getCharacter();
                if((c.equals("1") || c.equals("2") || c.equals("3"))){
                    t.setText(rndFlags.get(orderList.get(Integer.parseInt(c) - 1) - 1).getCapitol());
                } else{
                    t.setText("");
                }
            });
        }

        orderList.clear();
        orderList.add(1);   orderList.add(2);   orderList.add(3);
        Collections.shuffle(orderList);

        for (int i = 0; i < 3; i++) {
            gameGridLayout.add(rndFlags.get(i).getCanvas(),0, i);
            gameGridLayout.add(textFields.get(i),1, i);
            gameGridLayout.add(new Label((orderList.get(i)) + ": " + rndFlags.get(i).getCapitol()), 0, 3+orderList.get(i));
        }

        gameGridLayout.add(but, 1, 7);

    }

    public static void main(String[] args) {
        launch(args);
    }
}