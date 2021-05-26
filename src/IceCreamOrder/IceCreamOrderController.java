/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IceCreamOrder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;





/**
 * FXML Controller class
 * @author omarismail
 */
public class IceCreamOrderController implements Initializable {
//declaring variable for save and restore buttons
    String flavorType = null;
   
    @FXML
    private RadioButton chocolateFlav;

    @FXML
    private ToggleGroup flavor;

    @FXML
    private RadioButton vanillaFlav;

    @FXML
    private RadioButton strawberryFlav;

    @FXML
    private CheckBox nutsTopping;

    @FXML
    private CheckBox cherryTopping;

    @FXML
    private Button calculateCostButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button restoreButton;

    @FXML
    void onCalculateCost(ActionEvent event) {
    //calcualting the cost of the order    
    final double flavorCost = 2.25;
    final double toppingCost = 0.5;
    final double MI_Sales_Tax = 0.06;
    double addOns = 0;
    //if toppings are selected, adds .5 per topping 
    if (nutsTopping.isSelected())
        addOns = addOns + toppingCost;
    if (cherryTopping.isSelected())
        addOns = addOns + toppingCost;
       
    double order = flavorCost + addOns;
    double tax = order * MI_Sales_Tax;
    double totalPrice = order + tax;
    //Creating alert with formatted values and labels 
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Your Order");
      alert.setHeaderText(String.format("Total $%.2f", totalPrice));
      alert.setContentText(String.format("Order: $%.2f\nTax: $%.2f\nTotal: $%.2f",order, tax, totalPrice));
      alert.showAndWait();
    }

    @FXML
    public void onRestore(ActionEvent event) {
    //Restores the saved order from .txt file
    File file = new File ("icecream.txt");
    if(file.exists()){
        try{
            try(Scanner inputFile = new Scanner(new File("icecream.txt"))) {
                while (inputFile.hasNext()){
                    //reading file and determining the values of flavor and toppings
                    String inputFlavor  = inputFile.nextLine();
                    String inputNuts = inputFile.nextLine();
                    String inputCherries = inputFile.nextLine();
                    if(flavorType.equals("Chocolate"))
                        chocolateFlav.setSelected(true);
                    else if (flavorType.equals("Vanilla"))
                        vanillaFlav.setSelected(true);
                    else if (flavorType.equals("Strawberry"))
                        strawberryFlav.setSelected(true);
                    if (inputNuts.equals("With_Nuts"))
                        nutsTopping.setSelected(true);
                    else 
                        nutsTopping.setSelected(false);
                    if (inputCherries.equals("With_Cherries"))
                        cherryTopping.setSelected(true);
                    else
                        cherryTopping.setSelected(false);
            }
        }
        }
    //alert box if the .txt file is not found 
        catch(FileNotFoundException e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Restoring File");
        alert.setContentText(""+e);
        alert.showAndWait();
        }
                        
        
    }
    }

    @FXML
    public void onSave(ActionEvent event) {
        //Saves the Order in a .txt file
        try{
            try(PrintWriter out = new PrintWriter("icecream.txt")){
                
                 if (chocolateFlav.isSelected()){
                     flavorType = "Chocolate";
                 }
                 else if (vanillaFlav.isSelected()){
                     flavorType = "Vanilla";
                 }
                 else if (strawberryFlav.isSelected()){
                     flavorType = "Strawberry";
                 }
                 out.println(flavorType);
                 
                 if (nutsTopping.isSelected())
                       out.println("With_Nuts");
                 else
                     out.println("Without_Nuts");
                 if(cherryTopping.isSelected())
                     out.println("With_Cherries");
                 else
                     out.println("Without_Cherries");
            } 
        }            
        //error alert if .txt file could not be saved 
       catch(FileNotFoundException e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Saving File");
        alert.setContentText(""+e);
        alert.showAndWait();
        }
        
    }
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}

  
    
    