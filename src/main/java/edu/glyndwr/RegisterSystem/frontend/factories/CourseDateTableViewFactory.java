
package edu.glyndwr.RegisterSystem.frontend.factories;

import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Course;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseDate;
import java.time.LocalDate;
import java.util.Date;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
public class CourseDateTableViewFactory {
  
    public TableView<CourseDate> getNewTable() {
        TableView<CourseDate> table = new TableView<>();
        table.getColumns().addAll(getIdColumn(),getCourseDayColumn(),getCourseColumn());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("No visible columns and/or data exist."));
        return table;
    }
    
        public TableColumn<CourseDate, Long> getIdColumn() {
        TableColumn<CourseDate, Long> idCol = new TableColumn<>("Id");
        PropertyValueFactory<CourseDate, Long> idCellValueFactory = new PropertyValueFactory<>("id");
        idCol.setCellValueFactory(idCellValueFactory);
        return idCol;
    }

    public TableColumn<CourseDate, LocalDate> getCourseDayColumn() {
        TableColumn<CourseDate, LocalDate> courseDayCol = new TableColumn<>("Date");
        PropertyValueFactory<CourseDate, LocalDate> courseDayCellValueFactory = new PropertyValueFactory<>("courseDay");
        courseDayCol.setCellValueFactory(courseDayCellValueFactory);
        return courseDayCol;
    }

    public TableColumn<CourseDate, Course> getCourseColumn() {
        TableColumn<CourseDate, Course> courseCol = new TableColumn<>("Course");
        PropertyValueFactory<CourseDate, Course> courseCellValueFactory = new PropertyValueFactory<>("course");
        courseCol.setCellValueFactory(courseCellValueFactory);
        return courseCol;
    }
}
