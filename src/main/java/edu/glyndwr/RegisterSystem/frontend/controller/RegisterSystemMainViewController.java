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
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Log
@Component
@Getter
@Setter
public class RegisterSystemMainViewController {

    @Autowired
    private RegistryService service;
    @Autowired
    private FrontendFactoryFacade frontendFactoryFacade;
    @Autowired
    private ForntendUIModel model;
    private TabPane tabPane;
    private TableView<CourseDate> courseDateTable;
    private TableView<CourseMember> courseMemberTable;
    private TableView<Attendence> attendenceTable;
    private TableView<Student> studentTable;
    private TableView<Course> courseTable;
    private TableView<CourseDate> attendedCourseDateTable;
    private TableView<CourseDateAttendenceWrapper> wrappedAttendencesTable;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField streetField;
    private TextField zipCodeField;
    private TextField cityField;
    private TextField countryField;
    private TextField studentIDField;
    private TextField nameField;
    private TextField codeField;
    private TextField descriptionField;
    private ComboBox courseDateCourseBox;
    private DatePicker courseDateDatePicker;
    private ComboBox courseMemberCourseBox;
    private ComboBox courseMemberMemberBox;
    private ComboBox attendenceCourseMemberBox;
    private ComboBox attendenceCourseDateBox;
    private ComboBox studentProfilBox;
    private ComboBox studentCourseBox;
    Label profileHeaderAddressLabel;
    private Label profileHeaderCourseLabel;
    private Stage stage;

    public void initializeStage(Stage stage) {
        this.stage = stage;
        inititalizeFields();
        this.stage = frontendFactoryFacade.buildFrontendUI(this, this.stage);
        InputStream icon = null;
        try {
            icon = new DataInputStream(new FileInputStream(new ClassPathResource("icon.png").getFile()));
            
            if(null!=icon){
                Image imageIcon = new Image(icon);
                this.stage.getIcons().add(imageIcon); 
            }else{
                log.info("icon inputstream null");
            }
           
        } catch (IOException ex) {
            Logger.getLogger(RegisterSystemMainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.stage.show();
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
            Student student = studentTable.getItems().get(selectedIndices[i].intValue());
            studentTable.getItems().remove(student);
            model.getStudentList().remove(student);
            List<CourseMember> courseMemberToDelete = getModel().getCourseMemberList().stream().filter(m -> m.getStudent().equals(student)).collect(Collectors.toList());
            courseMemberTable.getItems().removeAll(courseMemberToDelete);
            getModel().getCourseMemberList().removeAll(courseMemberToDelete);
            List<Attendence> attendenceToDelete = getModel().getAttendenceList().stream().filter(m -> courseMemberToDelete.contains(m.getCourseMember())).collect(Collectors.toList());
            attendenceTable.getItems().removeAll(attendenceToDelete);
            getModel().getAttendenceList().removeAll(attendenceToDelete);
            getStudentProfilBox().getItems().remove(student);
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
        frontendFactoryFacade.rebuildCourseMemberAfterStudentAdded(this);
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
            List<CourseMember> courseMemberToDelete = getModel().getCourseMemberList().stream().filter(m -> m.getCourse().equals(course)).collect(Collectors.toList());
            courseMemberTable.getItems().removeAll(courseMemberToDelete);
            getModel().getCourseMemberList().removeAll(courseMemberToDelete);
            List<Attendence> attendenceToDelete = getModel().getAttendenceList().stream().filter(m -> courseMemberToDelete.contains(m.getCourseMember())).collect(Collectors.toList());
            attendenceTable.getItems().removeAll(attendenceToDelete);
            getModel().getAttendenceList().removeAll(attendenceToDelete);
            List<CourseDate> courseDateToDelete = getModel().getCourseDateList().stream().filter(m -> m.getCourse().equals(course)).collect(Collectors.toList());
            courseDateTable.getItems().removeAll(courseDateToDelete);
            getModel().getCourseDateList().removeAll(courseDateToDelete);
            getStudentCourseBox().getItems().remove(course);
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
        if (null != courseMemberCourseBox.getValue() && null != courseMemberMemberBox.getValue()) {
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
            CourseMember member = courseMemberTable.getItems().get(selectedIndices[i].intValue());
            courseMemberTable.getItems().remove(member);
            model.getCourseMemberList().remove(member);
            List<Attendence> attendenceToDelete = getModel().getAttendenceList().stream().filter(f -> f.getCourseMember().equals(member)).collect(Collectors.toList());
            getModel().getAttendenceList().removeAll(attendenceToDelete);
            attendenceTable.getItems().removeAll(attendenceToDelete);
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
            List<Attendence> attendenceToDelete = getModel().getAttendenceList().stream().filter(f -> f.getCourseDate().equals(date)).collect(Collectors.toList());
            getModel().getAttendenceList().removeAll(attendenceToDelete);
            attendenceTable.getItems().removeAll(attendenceToDelete);
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
        if (null != attendenceCourseDateBox.getValue() && null != attendenceCourseMemberBox.getValue()) {
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

    public void inititalizeFields() {
        tabPane = new TabPane();
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
