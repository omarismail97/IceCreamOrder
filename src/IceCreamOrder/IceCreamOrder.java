//Omar Ismail 
//Capstone Project
//CIS 1500
//Build a Ice Cream Order GUI


package IceCreamOrder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class IceCreamOrder extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Creates the original Scene that the user will see 
        Parent root = FXMLLoader.load(getClass().getResource("IceCreamOrderFXML.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Omar Ismail - Ice Cream");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
     public static void main (String[] args) {
     launch(args);
    }
}
