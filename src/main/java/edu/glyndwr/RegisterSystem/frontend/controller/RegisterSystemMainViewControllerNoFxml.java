package edu.glyndwr.RegisterSystem.frontend.controller;

import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Course;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseDate;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseMember;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Student;
import edu.glyndwr.RegisterSystem.frontend.factories.CourseDateNewDataPaneFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.CourseDateTableViewFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.CourseMemberNewDataPaneFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.CourseMemberTableViewFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.CourseNewDataPaneFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.CourseTableViewFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.StudentNewDataPaneFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.StudentTableViewFactory;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import javafx.collections.ObservableList;

import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Component
public class RegisterSystemMainViewControllerNoFxml {

       
    private CourseDateTableViewFactory courseDateTableViewFactory;
    private CourseDateNewDataPaneFactory courseDateNewDataPaneFactory;
    private TableView<CourseDate> courseDateTable;
    private GridPane courseDateNewDataPane;
    
    private CourseMemberTableViewFactory courseMemberTableViewFactory;
    private CourseMemberNewDataPaneFactory courseMemberNewDataPaneFactory;
    private TableView<CourseMember> courseMemberTable;
    private GridPane courseMemberNewDataPane;

    private StudentTableViewFactory studentTableViewFactory;
    private StudentNewDataPaneFactory studentNewDataPaneFactory;

    @Getter
    private TableView<Student> studentTable;
    private GridPane studentNewDataPane;
    private CourseTableViewFactory courseTableViewFactory;
    private CourseNewDataPaneFactory courseNewDataPaneFactory;
    @Getter
    private TableView<Course> courseTable;
    private GridPane courseNewDataPane;

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

    public void initializeStage(Stage stage) {
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

        studentTableViewFactory = new StudentTableViewFactory();
        studentTable = studentTableViewFactory.getPrepopulatedEditableTable();
        studentNewDataPaneFactory = new StudentNewDataPaneFactory();
        studentNewDataPane = studentNewDataPaneFactory.buildNewPersonDataPane(this);

        courseTableViewFactory = new CourseTableViewFactory();
        courseTable = courseTableViewFactory.getPrepopulatedEditableTable();
        courseTable.prefWidthProperty().bind(stage.widthProperty().subtract(50.0));
        courseNewDataPaneFactory = new CourseNewDataPaneFactory();
        courseNewDataPane = courseNewDataPaneFactory.buildNewCourseDataPane(this);

        courseMemberTableViewFactory = new CourseMemberTableViewFactory();
        courseMemberTable = courseMemberTableViewFactory.getNewTable();
        courseMemberTable.prefWidthProperty().bind(stage.widthProperty().subtract(50.0));
        courseMemberNewDataPaneFactory = new CourseMemberNewDataPaneFactory();
        courseMemberNewDataPane = courseMemberNewDataPaneFactory.buildNewCourseMemberDataPane(this);

        courseDateTableViewFactory = new CourseDateTableViewFactory();
        courseDateTable = courseDateTableViewFactory.getNewTable();
        courseDateTable.prefWidthProperty().bind(stage.widthProperty().subtract(50.0));
        courseDateNewDataPaneFactory = new CourseDateNewDataPaneFactory();
        courseDateNewDataPane = courseDateNewDataPaneFactory.buildNewCourseDateDataPane(this);
        
        
        VBox root = new VBox();
        root.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");

        TabPane tabPane = new TabPane();
        Tab studentTab = new Tab();

        studentTab.setText("Register Student");
        FlowPane pane = new FlowPane();
        pane.getChildren().addAll(studentNewDataPane, studentTable);
        studentTab.setContent(pane);
        tabPane.getTabs().add(studentTab);

        Tab courseTab = new Tab();
        courseTab.setText("Register Course");
        FlowPane coursepane = new FlowPane();
        coursepane.getChildren().addAll(courseNewDataPane, courseTable);
        courseTab.setContent(coursepane);
        tabPane.getTabs().add(courseTab);

        Tab courseMemberTab = new Tab();
        courseMemberTab.setText("Register Course Members");
        FlowPane courseMemberPane = new FlowPane();
        courseMemberPane.getChildren().addAll(courseMemberNewDataPane, courseMemberTable);
        courseMemberTab.setContent(courseMemberPane);
        tabPane.getTabs().add(courseMemberTab);
        
        Tab courseDateTab = new Tab();
        courseDateTab.setText("Register Course Dates");
        FlowPane courseDatePane = new FlowPane();
        courseDatePane.getChildren().addAll(courseDateNewDataPane, courseDateTable);
        courseDateTab.setContent(courseDatePane);
        tabPane.getTabs().add(courseDateTab);

        root.getChildren().addAll(tabPane);
        root.setSpacing(5);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Student Registration");
        stage.show();
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
            studentTable.getItems().remove(selectedIndices[i].intValue());
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
            courseTable.getItems().remove(selectedIndices[i].intValue());
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
            courseMemberTable.getItems().remove(selectedIndices[i].intValue());
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
            courseDateTable.getItems().remove(selectedIndices[i].intValue());
        }
        courseMemberTable.refresh();
    }
}
