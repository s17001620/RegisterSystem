package edu.glyndwr.RegisterSystem.frontend.factories;

import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Course;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseMember;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Student;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Component
public class CourseMemberTableViewFactory {

    public TableView<CourseMember> getNewTable() {
        TableView<CourseMember> table = new TableView<>();
        table.getColumns().addAll(getIdColumn(), getStudentColumn(), getCourseColumn());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("No visible columns and/or data exist."));
        return table;
    }

    public TableColumn<CourseMember, Long> getIdColumn() {
        TableColumn<CourseMember, Long> idCol = new TableColumn<>("Id");
        PropertyValueFactory<CourseMember, Long> idCellValueFactory = new PropertyValueFactory<>("id");
        idCol.setCellValueFactory(idCellValueFactory);
        return idCol;
    }

    public TableColumn<CourseMember, Student> getStudentColumn() {
        TableColumn<CourseMember, Student> studentCol = new TableColumn<>("Student");
        PropertyValueFactory<CourseMember, Student> studentCellValueFactory = new PropertyValueFactory<>("student");
        studentCol.setCellValueFactory(studentCellValueFactory);
        return studentCol;
    }

    public TableColumn<CourseMember, Course> getCourseColumn() {
        TableColumn<CourseMember, Course> courseCol = new TableColumn<>("Course");
        PropertyValueFactory<CourseMember, Course> courseCellValueFactory = new PropertyValueFactory<>("course");
        courseCol.setCellValueFactory(courseCellValueFactory);
        return courseCol;
    }
}
