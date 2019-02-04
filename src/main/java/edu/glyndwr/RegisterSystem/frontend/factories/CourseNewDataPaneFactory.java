
package edu.glyndwr.RegisterSystem.frontend.factories;

import edu.glyndwr.RegisterSystem.frontend.controller.RegisterSystemMainViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Component
public class CourseNewDataPaneFactory {
public GridPane buildNewCourseDataPane(RegisterSystemMainViewController controller){
       GridPane pane = new GridPane();
         
        // Set the hgap and vgap properties
        pane.setHgap(10);
        pane.setVgap(5);
         
        // Add the TextFields to the Pane
        pane.addRow(0, new Label("Name: "), controller.getNameField());
        pane.addRow(1, new Label("Code: "), controller.getCodeField());
        pane.addRow(2, new Label("Description: "), controller.getDescriptionField());

  
         
        // Create the Add Button and add Event-Handler
        Button addButton = new Button("Add");       
        addButton.setOnAction((ActionEvent e) -> {
           controller.addCourse();
        });     
       Button deleteButton = new Button("Delete Selected Rows");
        deleteButton.setOnAction((ActionEvent e) -> {
            controller.deleteCourse();
        }); 
        // Add the Add Button to the GridPane
        pane.add(addButton, 2, 0);
        pane.add(deleteButton, 1, 7);
     
        return pane;
    }

}
