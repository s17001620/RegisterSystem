package edu.glyndwr.RegisterSystem.frontend.model;

import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Attendence;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Course;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseDate;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseMember;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Student;
import edu.glyndwr.RegisterSystem.frontend.model.wrapper.CourseDateAttendenceWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Component
@Getter
@Setter
public class ForntendUIModel {

    private ObservableList<CourseDate> courseDateList;
    private ObservableList<CourseMember> courseMemberList;
    private ObservableList<Attendence> attendenceList;
    private ObservableList<Student> studentList;
    private ObservableList<Course> courseList;
    private ObservableList<CourseDateAttendenceWrapper> wrappedAttendencesList;

    public ForntendUIModel() {
        courseDateList = FXCollections.observableArrayList();
        courseMemberList = FXCollections.observableArrayList();
        attendenceList = FXCollections.observableArrayList();
        studentList = FXCollections.observableArrayList();
        courseList = FXCollections.observableArrayList();
    }
}
