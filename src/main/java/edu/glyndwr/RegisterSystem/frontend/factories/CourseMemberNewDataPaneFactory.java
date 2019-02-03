
package edu.glyndwr.RegisterSystem.frontend.factories;

import edu.glyndwr.RegisterSystem.frontend.controller.RegisterSystemMainViewControllerNoFxml;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
public class CourseMemberNewDataPaneFactory {
 public GridPane buildNewCourseMemberDataPane(RegisterSystemMainViewControllerNoFxml controller){
    GridPane pane = new GridPane();
         
        pane.setHgap(10);
        pane.setVgap(5);
        controller.getCourseMemberCourseBox().getItems().addAll(controller.getCourseTable().getItems());
        controller.getCourseMemberMemberBox().getItems().addAll(controller.getStudentTable().getItems());
        pane.addRow(0, new Label("Course: "), controller.getCourseMemberCourseBox());
        pane.addRow(1, new Label("Member: "), controller.getCourseMemberMemberBox());


         
        // Create the Add Button and add Event-Handler
        Button addButton = new Button("Add");       
        addButton.setOnAction((ActionEvent e) -> {
           controller.addCourseMember();
        });     
       Button deleteButton = new Button("Delete Selected Rows");
        deleteButton.setOnAction((ActionEvent e) -> {
            controller.deleteCourseMember();
        }); 
        // Add the Add Button to the GridPane
        pane.add(addButton, 2, 0);
        pane.add(deleteButton, 1, 7);
     
        return pane;
 }
 
}
