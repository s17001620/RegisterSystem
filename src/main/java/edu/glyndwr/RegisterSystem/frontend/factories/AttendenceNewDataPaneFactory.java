/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.glyndwr.RegisterSystem.frontend.factories;

import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Attendence;
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
public class AttendenceNewDataPaneFactory {

    public GridPane buildNewAttendenceDataPane(RegisterSystemMainViewControllerNoFxml controller) {
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(5);
        controller.getAttendenceCourseDateBox().getItems().addAll(controller.getCourseDateTable().getItems());

        pane.addRow(0, new Label("Course Date: "), controller.getAttendenceCourseDateBox());
        pane.addRow(1, new Label("Course Member: "), controller.getAttendenceCourseMemberBox());

        controller.getAttendenceCourseDateBox().setOnAction(event -> {
            CourseDate date = (CourseDate) controller.getAttendenceCourseDateBox().getValue();
            Course course = date.getCourse();
            Stream<CourseMember> memberFilter = controller.getModel().getCourseMemberList().stream().filter(f -> f.getCourse().equals(course));
            controller.getAttendenceCourseMemberBox().getItems().clear();
            controller.getAttendenceCourseMemberBox().getItems().addAll(memberFilter.collect(Collectors.toList()));
            Stream<Attendence> filter = controller.getModel().getAttendenceList().stream().filter(m -> m.getCourseDate().equals((CourseDate) controller.getAttendenceCourseDateBox().getValue()));
            controller.getAttendenceTable().getItems().clear();
            controller.getAttendenceTable().getItems().addAll(filter.collect(Collectors.toList()));
            controller.getAttendenceTable().refresh();
        });

        Button addButton = new Button("Add");
        addButton.setOnAction((ActionEvent e) -> {
            controller.addAttendence();
        });
        Button deleteButton = new Button("Delete Selected Rows");
        deleteButton.setOnAction((ActionEvent e) -> {
            controller.deleteAttendence();
        });
        pane.add(addButton, 2, 0);
        pane.add(deleteButton, 1, 7);

        return pane;
    }
}
