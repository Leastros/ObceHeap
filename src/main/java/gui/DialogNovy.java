
package gui;


import Obce.Obec;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class DialogNovy {
    
    static int id, muzu, zen;
    static String cas, osob;
    static Obec proces = null;
 
    public static Obec zobraz() {
         
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        
        GridPane dialog = new GridPane();
         
        dialog.setPadding(new Insets(10, 10, 10, 10)); 
        dialog.setVgap(5); 
        dialog.setHgap(5); 
         
        TextField tfid = new TextField();
        TextField tfcas = new TextField();
        TextField tfosob = new TextField();
        TextField tfZen = new TextField();
        TextField tfMuzu = new TextField();
         
     
        Label lid = new Label("cislo kraje:");
        Label lcas = new Label("psc:");
        Label losob = new Label("nazev:");
        Label lzen = new Label("počet mužů:");
        Label lmuzu = new Label("počet žen:");
        
        Button button = new Button("Ok");
        button.setOnAction(e -> {
            try{
                id = Integer.parseInt(tfid.getText());
                cas = tfcas.getText();
                osob = tfosob.getText();
                zen = Integer.parseInt(tfZen.getText());
                muzu = Integer.parseInt(tfMuzu.getText());
                
                proces = new Obec(id, cas, osob, zen, muzu ,zen+muzu);

                stage.close();
            }catch(Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("chyba");
                alert.setContentText("chybně zadané údaje");
                alert.showAndWait();
                return;
            }
            
        });
         
        dialog.add(lid, 0,1);
        dialog.add(lcas, 0,2);
        dialog.add(losob, 0,3);
        dialog.add(lzen, 0,4);
        dialog.add(lmuzu, 0,5);
        dialog.add(tfid, 1,1);
        dialog.add(tfcas, 1,2);
        dialog.add(tfosob, 1,3);
        dialog.add(tfZen, 1,4);
        dialog.add(tfMuzu, 1,5);
        dialog.add(button, 1,6);
        
        Scene scene = new Scene(dialog);          
        stage.setScene(scene);
        stage.showAndWait();
         
        return proces;
    }
    
}
