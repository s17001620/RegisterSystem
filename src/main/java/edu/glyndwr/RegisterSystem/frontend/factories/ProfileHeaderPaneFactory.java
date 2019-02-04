package edu.glyndwr.RegisterSystem.frontend.factories;

import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Attendence;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Course;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseDate;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Student;
import edu.glyndwr.RegisterSystem.frontend.controller.RegisterSystemMainViewControllerNoFxml;
import edu.glyndwr.RegisterSystem.frontend.model.wrapper.CourseDateAttendenceWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Component
public class ProfileHeaderPaneFactory {

    public GridPane buildNewPersonDataPane(RegisterSystemMainViewControllerNoFxml controller) {
        GridPane pane = new GridPane();

        // Set the hgap and vgap properties
        pane.setHgap(10);
        pane.setVgap(5);
        controller.getStudentProfilBox().getItems().addAll(controller.getModel().getStudentList());
        pane.addRow(0, new Label("Student: "), controller.getStudentProfilBox());
        pane.addRow(1, new Label("Address: "), controller.getProfileHeaderAddressLabel());
        pane.addRow(2, new Label("Courses: "), controller.getProfileHeaderCourseLabel());
        pane.addRow(3, new Label("Check Attendence for Course: "), controller.getStudentCourseBox());

        controller.getStudentProfilBox().setOnAction(event -> {
            Student headerStudent = (Student) controller.getStudentProfilBox().getValue();
            controller.getProfileHeaderAddressLabel().setText(headerStudent.getStreet() + " " + headerStudent.getZipCode() + " " + headerStudent.getCity() + " " + headerStudent.getCountry());
            String courseString = controller.getModel().getCourseMemberList().stream().filter(f -> f.getStudent().equals(headerStudent)).map(e -> e.getCourse().getName()).collect( Collectors.joining(", "));
           
            controller.getProfileHeaderCourseLabel().setText(courseString);
            controller.getStudentCourseBox().getItems().clear();
            List<Course> courses = controller.getModel().getCourseMemberList().stream().filter(f -> f.getStudent().equals(headerStudent)).map(e -> e.getCourse()).collect(Collectors.toList());
            controller.getStudentCourseBox().getItems().addAll(courses);
        });
        
        controller.getStudentCourseBox().setOnAction((Event event) -> {
            List<CourseDate> dates = controller.getModel().getCourseDateList().stream().filter(f -> f.getCourse().equals((Course)controller.getStudentCourseBox().getValue())).collect(Collectors.toList());
            dates.forEach(d -> controller.getModel().getAttendenceList().stream().filter(a -> a.getCourseDate().equals(d) && a.getCourseMember().getStudent().equals((Student) controller.getStudentProfilBox().getValue())));
            
            ArrayList<CourseDateAttendenceWrapper> wrappedAttndences = new ArrayList<CourseDateAttendenceWrapper>();
            dates.forEach((date) -> {
                CourseDateAttendenceWrapper wrapper = new CourseDateAttendenceWrapper();
                wrapper.setCourseDate(date);
                Optional<Attendence> attn = controller.getModel().getAttendenceList().stream().filter(a -> a.getCourseDate().equals(date) && a.getCourseMember().getStudent().equals((Student) controller.getStudentProfilBox().getValue())).collect(Collectors.toList()).stream().findFirst();
                if(attn.isEmpty()){
                    wrapper.setAttendence(null);
                }else{
                    wrapper.setAttendence(attn.get());
                }
                wrappedAttndences.add(wrapper);
            });
            controller.getWrappedAttendencesTable().getItems().clear();
            controller.getWrappedAttendencesTable().getItems().addAll(wrappedAttndences);
            controller.getWrappedAttendencesTable().refresh();
        });
        
        
        return pane;
    }

}
