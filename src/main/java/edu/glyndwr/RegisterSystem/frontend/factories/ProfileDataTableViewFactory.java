

package edu.glyndwr.RegisterSystem.frontend.factories;

import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Attendence;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseDate;
import edu.glyndwr.RegisterSystem.frontend.model.wrapper.CourseDateAttendenceWrapper;
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
public class ProfileDataTableViewFactory {

    
        public TableView<CourseDateAttendenceWrapper> getNewTable() {
        TableView<CourseDateAttendenceWrapper> table = new TableView<>();
        table.getColumns().addAll(getCourseDateColumn(),getCourseAttendenceColumn());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("No visible columns and/or data exist."));
        return table;
    }
        public TableColumn<CourseDateAttendenceWrapper, CourseDate> getCourseDateColumn() {
        TableColumn<CourseDateAttendenceWrapper, CourseDate> courseDateCol = new TableColumn<>("Course Date");
        PropertyValueFactory<CourseDateAttendenceWrapper, CourseDate> courseDayCellValueFactory = new PropertyValueFactory<>("courseDate");
        courseDateCol.setCellValueFactory(courseDayCellValueFactory);
        return courseDateCol;
    }

    public TableColumn<CourseDateAttendenceWrapper, Attendence> getCourseAttendenceColumn() {
        TableColumn<CourseDateAttendenceWrapper, Attendence> attendenceCol = new TableColumn<>("Attendence");
        PropertyValueFactory<CourseDateAttendenceWrapper, Attendence> courseCellValueFactory = new PropertyValueFactory<>("attendence");
        attendenceCol.setCellValueFactory(courseCellValueFactory);
        return attendenceCol;
    }
}
