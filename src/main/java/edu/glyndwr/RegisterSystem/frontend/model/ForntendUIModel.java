

package edu.glyndwr.RegisterSystem.frontend.model;

import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Attendence;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Course;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseDate;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseMember;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Student;
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
public class ForntendUIModel {
    @Getter
    @Setter
    private ObservableList<CourseDate> courseDateList;
    @Getter
    @Setter
    private ObservableList<CourseMember> courseMemberList;
    @Getter
    @Setter
    private ObservableList<Attendence> attendenceList;
    @Getter
    @Setter
    private ObservableList<Student> studentList;
    @Getter
    @Setter
    private ObservableList<Course> courseList;
    
    public ForntendUIModel(){
        courseDateList = FXCollections.observableArrayList() ;
        courseMemberList = FXCollections.observableArrayList() ;
        attendenceList  = FXCollections.observableArrayList() ;
        studentList = FXCollections.observableArrayList() ;
        courseList  = FXCollections.observableArrayList() ;
    }
}
