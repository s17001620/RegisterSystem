
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
public class CourseDateNewDataPaneFactory {
 public GridPane buildNewCourseDateDataPane(RegisterSystemMainViewControllerNoFxml controller){
    GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(5);
        controller.getCourseDateCourseBox().getItems().addAll(controller.getCourseTable().getItems());
        pane.addRow(0, new Label("Course: "), controller.getCourseDateCourseBox());
        pane.addRow(1, new Label("Date: "), controller.getCourseDateDatePicker());
        Button addButton = new Button("Add");       
        addButton.setOnAction((ActionEvent e) -> {
           controller.addCourseDate();
        });     
       Button deleteButton = new Button("Delete Selected Rows");
        deleteButton.setOnAction((ActionEvent e) -> {
            controller.deleteCourseDate();
        }); 
        pane.add(addButton, 2, 0);
        pane.add(deleteButton, 1, 7);
     
        return pane;
 }
}
