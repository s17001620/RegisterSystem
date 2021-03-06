package edu.glyndwr.RegisterSystem.frontend.factories;

import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Course;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseMember;
import edu.glyndwr.RegisterSystem.frontend.controller.RegisterSystemMainViewController;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Log
@Component
public class CourseMemberNewDataPaneFactory {

    public GridPane buildNewCourseMemberDataPane(RegisterSystemMainViewController controller) {
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(5);
        if(controller.getModel().getCourseList().isEmpty()){
          controller.getModel().getCourseList().addAll(controller.getCourseTable().getItems());
        log.info("Warning: the Course List was empty!");
        }
        controller.getCourseMemberCourseBox().setItems(controller.getModel().getCourseList());
        controller.getCourseMemberMemberBox().setItems(controller.getModel().getStudentList());
        pane.addRow(0, new Label("Course: "), controller.getCourseMemberCourseBox());
        pane.addRow(1, new Label("Member: "), controller.getCourseMemberMemberBox());

          controller.getCourseMemberCourseBox().setOnAction(event->{
            Stream<CourseMember> filter = controller.getModel().getCourseMemberList().stream().filter(m -> m.getCourse().equals((Course)controller.getCourseMemberCourseBox().getValue()));
            controller.getCourseMemberTable().getItems().clear();
            controller.getCourseMemberTable().getItems().addAll(filter.collect(Collectors.toList()));
            controller.getCourseMemberTable().refresh();
          });
          
        
        Button addButton = new Button("Add");
        addButton.setOnAction((ActionEvent e) -> {
            controller.addCourseMember();
        });
        Button deleteButton = new Button("Delete Selected Rows");
        deleteButton.setOnAction((ActionEvent e) -> {
            controller.deleteCourseMember();
        });
        pane.add(addButton, 2, 0);
        pane.add(deleteButton, 1, 7);

        return pane;
    }

}
