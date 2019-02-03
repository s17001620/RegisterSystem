
package edu.glyndwr.RegisterSystem.frontend.factories;

import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Course;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseDate;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseMember;
import edu.glyndwr.RegisterSystem.frontend.controller.RegisterSystemMainViewControllerNoFxml;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
public class CourseDateNewDataPaneFactory {
 public GridPane buildNewCourseDateDataPane(RegisterSystemMainViewControllerNoFxml controller){
    GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(5);
        controller.getCourseDateCourseBox().getItems().addAll(controller.getCourseTable().getItems());
        pane.addRow(0, new Label("Course: "), controller.getCourseDateCourseBox());
        pane.addRow(1, new Label("Date: "), controller.getCourseDateDatePicker());
        
        controller.getCourseDateCourseBox().setOnAction(event->{
            Stream<CourseDate> filter = controller.getModel().getCourseDateList().stream().filter(m -> m.getCourse().equals((Course) controller.getCourseDateCourseBox().getValue()));
            controller.getCourseDateTable().getItems().clear();
            controller.getCourseDateTable().getItems().addAll(filter.collect(Collectors.toList()));
            controller.getCourseDateTable().refresh();
          });
       
        Button addButton = new Button("Add");       
        addButton.setOnAction((ActionEvent e) -> {
           controller.addCourseDate();
           controller.getAttendenceCourseDateBox().getItems().clear();
           controller.getAttendenceCourseDateBox().getItems().addAll(controller.getModel().getCourseDateList());
        });     
       Button deleteButton = new Button("Delete Selected Rows");
        deleteButton.setOnAction((ActionEvent e) -> {
           controller.deleteCourseDate();
           controller.getAttendenceCourseDateBox().getItems().clear();
           controller.getAttendenceCourseDateBox().getItems().addAll(controller.getModel().getCourseDateList());
        }); 
        pane.add(addButton, 2, 0);
        pane.add(deleteButton, 1, 7);
     
        return pane;
 }
}
