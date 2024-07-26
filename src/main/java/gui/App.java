package gui;

import Struktury.AbstrDoubleList;
import Struktury.IAbstrDoubleList;
import Obce.Generator;
import Obce.Obec;
import Obce.SpravceObci;
import Obce.Zaloha;
import Obce.eTypProhl;
import Obce.enumPozice;
import static Obce.enumPozice.NASLEDNIK;
import static Obce.enumPozice.POSLEDNI;
import static Obce.enumPozice.PREDCHUDCE;
import static Obce.enumPozice.PRVNI;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    
    SpravceObci obec = new SpravceObci();
    Generator generator = new Generator();
    Zaloha zaloha = new Zaloha();
    int pocet = -1;
    
    public static void main(String[] args) {
        launch();
    }
    
    ObservableList<String> novy = FXCollections.observableArrayList("SIRKA", "HLOUBKA");
        ComboBox comboProhlidka = new ComboBox(novy);
        
    ObservableList<String> novyTypProhlidky = FXCollections.observableArrayList("POCET", "NAZEV");
        ComboBox comboComparator = new ComboBox(novyTypProhlidky);

    @Override
    public void start(Stage stage) {

        
        VBox root = new VBox(8);
        Scene scene = new Scene(root, 825, 600);
        stage.setTitle("Evidence obcí");
        stage.setScene(scene);
        
        HBox top = new HBox(8);
        HBox bottom = new HBox(8);
        VBox topBtns = new VBox(8);
        
        
        ObservableList<Obec> radky = FXCollections.observableArrayList();
        ListView<Obec> list = new ListView(radky);
        list.setPrefSize(700,600);
        
        Button btnVybuduj = new Button();
        btnVybuduj.setText("vybuduj");
        btnVybuduj.setOnAction((ActionEvent event) -> {
            try {
                //obec.importDat("kraje.csv");
                obec.importDat("strom_big.csv");
            } catch (IOException ex) {
                
            }
            obnovLV(list);
            list.getSelectionModel().selectFirst();
            comboProhlidka.getSelectionModel().selectFirst();
        });
        
        Button btnOdeberMax = new Button();
        btnOdeberMax.setText("odeber max");
        btnOdeberMax.setOnAction((ActionEvent event) -> {
            obec.odeberMax();
            obnovLV(list);
        });

        
        Button btnZrus = new Button();
        btnZrus.setText("zrus");
        btnZrus.setOnAction((ActionEvent event) -> {
            obec.zrus();
            obnovLV(list);
            comboProhlidka.getSelectionModel().selectFirst();
        });
        
        
        
        
        TextField tfid = new TextField();
        
        Button btnVloz = new Button();
        btnVloz.setText("vloz");
        btnVloz.setOnAction((ActionEvent event) -> {
            Obec nova = DialogNovy.zobraz();
            obec.vlozObec(nova);
            obnovLV(list);
            
        });

        comboProhlidka.getSelectionModel().selectFirst();
        comboProhlidka.valueProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal.equals("SIRKA")){
                obnovLV(list);
            }else{
                obnovLV(list);
            }
        });
        
        comboComparator.getSelectionModel().selectFirst();
        comboComparator.valueProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal.equals("NAZEV")){
                obnovLV(list);
            }else{
                obnovLV(list);
            }
        });
        
        tfid.setPrefWidth(40);

        root.getChildren().addAll(top, bottom);
        top.getChildren().addAll(list, topBtns);
        topBtns.getChildren().addAll(btnVloz, btnVybuduj, btnOdeberMax, btnZrus, comboProhlidka, comboComparator);
        
        stage.show();
    }

    private void obnovLV(ListView<Obec> list){
        eTypProhl typ;
        String typComparator = "";
        list.getItems().clear();
        pocet = -1;
            if(comboProhlidka.getValue().equals("SIRKA")){
                typ =  eTypProhl.SIRKA;
            }else{
                typ =  eTypProhl.HLOUBKA;
            }
            if(comboComparator.getValue().equals("NAZEV")){
                typComparator =  "NAZEV";
            }else{
                typComparator =  "POCET";
            }
            obec.zmenRazeni(typComparator);
            Iterator<Obec> iterator = obec.vytvorTterator(typ);
            while(iterator.hasNext()){
                list.getItems().add(iterator.next());
                pocet++;
            }
        }
    
        
}