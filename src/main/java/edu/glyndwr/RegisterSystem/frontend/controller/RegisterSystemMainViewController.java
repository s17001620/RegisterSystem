package edu.glyndwr.RegisterSystem.frontend.controller;

import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Attendence;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Course;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseDate;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseMember;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Student;
import edu.glyndwr.RegisterSystem.backend.data.services.RegistryService;
import edu.glyndwr.RegisterSystem.frontend.factories.facades.FrontendFactoryFacade;
import edu.glyndwr.RegisterSystem.frontend.model.ForntendUIModel;
import edu.glyndwr.RegisterSystem.frontend.model.wrapper.CourseDateAttendenceWrapper;
import java.util.Arrays;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Component("fegisterSystemMainViewControllerNoFxml")
public class RegisterSystemMainViewController {
    @Autowired
    private RegistryService service;
    @Autowired
    private FrontendFactoryFacade frontendFactoryFacade;
    @Autowired
    @Getter
    private ForntendUIModel model;
    @Getter
    @Setter
    private TableView<CourseDate> courseDateTable;
    @Getter
    @Setter
    private TableView<CourseMember> courseMemberTable;
    @Getter
    @Setter
    private TableView<Attendence> attendenceTable;
    @Getter
    @Setter
    private TableView<Student> studentTable;
    @Getter
    @Setter
    private TableView<Course> courseTable;
    @Getter
    @Setter
    private TableView<CourseDate> attendedCourseDateTable;
    @Getter
    @Setter
    private TableView<CourseDateAttendenceWrapper> wrappedAttendencesTable;
    
    @Getter
    private TextField firstNameField;
    @Getter
    private TextField lastNameField;
    @Getter
    private TextField streetField;
    @Getter
    private TextField zipCodeField;
    @Getter
    private TextField cityField;
    @Getter
    private TextField countryField;
    @Getter
    private TextField studentIDField;
    @Getter
    private TextField nameField;
    @Getter
    private TextField codeField;
    @Getter
    private TextField descriptionField;
    @Getter
    private ComboBox courseDateCourseBox;
    @Getter
    private DatePicker courseDateDatePicker;
    @Getter
    private ComboBox courseMemberCourseBox;
    @Getter
    private ComboBox courseMemberMemberBox;
    @Getter
    private ComboBox attendenceCourseMemberBox;
    @Getter
    private ComboBox attendenceCourseDateBox;
    @Getter
    private ComboBox studentProfilBox;
    @Getter
    private ComboBox studentCourseBox;
    @Getter
    @Setter
    Label profileHeaderAddressLabel;
    @Getter
    @Setter
    private Label profileHeaderCourseLabel;

    public void initializeStage(Stage stage) {
        inititalizeFields();
        frontendFactoryFacade.buildFrontendUI(this, stage).show();
    }

    public void deleteStudent() {
        TableViewSelectionModel tsm = studentTable.getSelectionModel();
        if (tsm.isEmpty()) {
            System.out.println("Please select a row to delete.");
            return;
        }
        ObservableList<Integer> list = tsm.getSelectedIndices();
        Integer[] selectedIndices = new Integer[list.size()];
        selectedIndices = list.toArray(selectedIndices);
        Arrays.sort(selectedIndices);
        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            tsm.clearSelection(selectedIndices[i]);
           Student student= studentTable.getItems().get(selectedIndices[i].intValue());
            studentTable.getItems().remove(student);
            model.getStudentList().remove(student);
        }
        studentTable.refresh();
    }

    public void addStudent() {
        Long currentId = Long.valueOf(0);
        for (Student p : studentTable.getItems()) {
            if (p.getId() > currentId) {
                currentId = p.getId();
            }
        }
        Student Student = new Student(currentId + 1, firstNameField.getText(), lastNameField.getText(), streetField.getText(),
                zipCodeField.getText(), cityField.getText(), countryField.getText());
        Student.setStudentID(studentIDField.getText());
        studentTable.getItems().add(Student);
        model.getStudentList().add(Student);
        firstNameField.setText(null);
        lastNameField.setText(null);
        streetField.setText(null);
        zipCodeField.setText(null);
        cityField.setText(null);
        countryField.setText(null);
        studentIDField.setText(null);
        studentTable.refresh();
    }

    public void deleteCourse() {
        TableViewSelectionModel tsm = courseTable.getSelectionModel();
        if (tsm.isEmpty()) {
            System.out.println("Please select a row to delete.");
            return;
        }
        ObservableList<Integer> list = tsm.getSelectedIndices();
        Integer[] selectedIndices = new Integer[list.size()];
        selectedIndices = list.toArray(selectedIndices);
        Arrays.sort(selectedIndices);
        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            tsm.clearSelection(selectedIndices[i]);
            Course course = courseTable.getItems().get(selectedIndices[i].intValue());
            courseTable.getItems().remove(course);
            model.getCourseList().remove(course);
        }
        courseTable.refresh();
    }

    public void addCourse() {
        Long currentId = Long.valueOf(0);
        for (Course p : courseTable.getItems()) {
            if (p.getId() > currentId) {
                currentId = p.getId();
            }
        }
        Course course = new Course(currentId + 1, nameField.getText(), codeField.getText(), descriptionField.getText());
        courseTable.getItems().add(course);
        model.getCourseList().add(course);
        nameField.setText(null);
        codeField.setText(null);
        descriptionField.setText(null);
        courseTable.refresh();
    }

    public void addCourseMember() {
        Long currentId = Long.valueOf(0);
        for (CourseMember p : courseMemberTable.getItems()) {
            if (p.getId() > currentId) {
                currentId = p.getId();
            }
        }
        CourseMember courseMember = new CourseMember(currentId + 1, (Course) courseMemberCourseBox.getValue(), (Student) courseMemberMemberBox.getValue());
        for (CourseMember member : courseMemberTable.getItems()) {
            if (member.getStudent().equals(courseMember.getStudent()) && member.getCourse().equals(courseMember.getCourse())) {
                return;
            }
        }
        courseMemberTable.getItems().add(courseMember);
        model.getCourseMemberList().add(courseMember);
        courseMemberTable.refresh();
    }

    public void deleteCourseMember() {
        TableViewSelectionModel tsm = courseMemberTable.getSelectionModel();
        if (tsm.isEmpty()) {
            System.out.println("Please select a row to delete.");
            return;
        }
        
        
        ObservableList<Integer> list = tsm.getSelectedIndices();
        Integer[] selectedIndices = new Integer[list.size()];
        selectedIndices = list.toArray(selectedIndices);
        Arrays.sort(selectedIndices);
        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            tsm.clearSelection(selectedIndices[i]);
           CourseMember member =  courseMemberTable.getItems().get(selectedIndices[i].intValue());
           courseMemberTable.getItems().remove(member);
           model.getCourseMemberList().remove(member);
        }
        courseMemberTable.refresh();
    }

    public void addCourseDate() {
        Long currentId = Long.valueOf(0);
        for (CourseDate p : courseDateTable.getItems()) {
            if (p.getId() > currentId) {
                currentId = p.getId();
            }
        }
        CourseDate courseDate = new CourseDate(currentId + 1, (Course) courseDateCourseBox.getValue(), courseDateDatePicker.getValue());
        for (CourseDate date : courseDateTable.getItems()) {
            if (date.getCourse().equals(courseDate.getCourse()) && date.getCourseDay().equals(courseDateDatePicker.getValue())) {
                return;
            }
        }
        courseDateTable.getItems().add(courseDate);
        model.getCourseDateList().add(courseDate);
        courseDateTable.refresh();
    }

    public void deleteCourseDate() {
        TableViewSelectionModel tsm = courseDateTable.getSelectionModel();
        if (tsm.isEmpty()) {
            System.out.println("Please select a row to delete.");
            return;
        }
        ObservableList<Integer> list = tsm.getSelectedIndices();
        Integer[] selectedIndices = new Integer[list.size()];
        selectedIndices = list.toArray(selectedIndices);
        Arrays.sort(selectedIndices);
        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            tsm.clearSelection(selectedIndices[i]);
            CourseDate date = courseDateTable.getItems().get(selectedIndices[i].intValue());
            courseDateTable.getItems().remove(date);
            model.getCourseDateList().remove(date);
        }
        courseMemberTable.refresh();
    }

    public void addAttendence() {
        Long currentId = Long.valueOf(0);
        for (Attendence p : attendenceTable.getItems()) {
            if (p.getId() > currentId) {
                currentId = p.getId();
            }
        }
        Attendence attendence = new Attendence(currentId + 1, (CourseDate) attendenceCourseDateBox.getValue(), (CourseMember) attendenceCourseMemberBox.getValue());
        for (Attendence att : attendenceTable.getItems()) {
            if (att.getCourseDate().equals(attendence.getCourseDate()) && att.getCourseMember().equals(attendence.getCourseMember())) {
                return;
            }
        }
        attendence.setAttended(Boolean.TRUE);
        attendenceTable.getItems().add(attendence);
        model.getAttendenceList().add(attendence);
        attendenceTable.refresh();
    }

    public void deleteAttendence() {
        TableViewSelectionModel tsm = attendenceTable.getSelectionModel();
        if (tsm.isEmpty()) {
            System.out.println("Please select a row to delete.");
            return;
        }
        ObservableList<Integer> list = tsm.getSelectedIndices();
        Integer[] selectedIndices = new Integer[list.size()];
        selectedIndices = list.toArray(selectedIndices);
        Arrays.sort(selectedIndices);
        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            tsm.clearSelection(selectedIndices[i]);
            Attendence attn = attendenceTable.getItems().get(selectedIndices[i].intValue());
            attendenceTable.getItems().remove(attn);
            model.getAttendenceList().remove(attn);
        }
        attendenceTable.refresh();
    }
    
    public void inititalizeFields(){
        firstNameField = new TextField();
        lastNameField = new TextField();
        streetField = new TextField();
        zipCodeField = new TextField();
        cityField = new TextField();
        countryField = new TextField();
        studentIDField = new TextField();
        nameField = new TextField();
        codeField = new TextField();
        descriptionField = new TextField();
        courseDateCourseBox = new ComboBox();
        courseDateDatePicker = new DatePicker();
        courseMemberCourseBox = new ComboBox();
        courseMemberMemberBox = new ComboBox();
        attendenceCourseMemberBox = new ComboBox();
        attendenceCourseDateBox = new ComboBox();
        studentProfilBox = new ComboBox();
        studentCourseBox = new ComboBox();
        profileHeaderAddressLabel = new Label();
        profileHeaderCourseLabel = new Label();
    }
}
