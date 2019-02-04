
package edu.glyndwr.RegisterSystem.frontend.factories;

import edu.glyndwr.RegisterSystem.frontend.controller.RegisterSystemMainViewControllerNoFxml;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Component
public class StudentNewDataPaneFactory {
public GridPane buildNewPersonDataPane(RegisterSystemMainViewControllerNoFxml controller){
       GridPane pane = new GridPane();
         
        // Set the hgap and vgap properties
        pane.setHgap(10);
        pane.setVgap(5);
         
        // Add the TextFields to the Pane
        pane.addRow(0, new Label("First Name:"), controller.getFirstNameField());
        pane.addRow(1, new Label("Last Name:"), controller.getLastNameField());
        pane.addRow(2, new Label("Street:"), controller.getStreetField());
        pane.addRow(3, new Label("Zip Code:"), controller.getZipCodeField());
        pane.addRow(4, new Label("City:"), controller.getCityField());
        pane.addRow(5, new Label("Country:"), controller.getCountryField());
        pane.addRow(6, new Label("Student ID:"), controller.getStudentIDField());
         
        // Create the Add Button and add Event-Handler
        Button addButton = new Button("Add");       
        addButton.setOnAction((ActionEvent e) -> {
           controller.addStudent();
        });     
       Button deleteButton = new Button("Delete Selected Rows");
        deleteButton.setOnAction((ActionEvent e) -> {
            controller.deleteStudent();
        }); 
        // Add the Add Button to the GridPane
        pane.add(addButton, 2, 0);
        pane.add(deleteButton, 1, 7);
     
        return pane;
    }
}
