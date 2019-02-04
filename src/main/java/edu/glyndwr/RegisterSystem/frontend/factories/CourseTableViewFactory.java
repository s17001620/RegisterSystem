package edu.glyndwr.RegisterSystem.frontend.factories;

import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Course;
import edu.glyndwr.RegisterSystem.frontend.controller.RegisterSystemMainViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Component
public class CourseTableViewFactory {

    @Getter
    private ObservableList<Course> courseList;
    private RegisterSystemMainViewController controller;

    public CourseTableViewFactory() {
        courseList = FXCollections.<Course>observableArrayList();
    }

    public TableView<Course> getPrepopulatedEditableTable(RegisterSystemMainViewController controller) {
        TableView<Course> table = new TableView<>();
        this.prepopulateListWithDummyData();
        this.controller = controller;
        this.controller.getModel().getCourseList().addAll(getCourseList());
        table.getItems().addAll(getCourseList());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("No visible columns and/or data exist."));
        table.setEditable(true);
        addIdColumn(table);
        addNameColumn(table);
        addCodeColumn(table);
        addDescriptionColumn(table);
        return table;
    }

    public TableView<Course> getNewEditableTable(RegisterSystemMainViewController controller) {
        TableView<Course> table = new TableView<>();
        this.controller = controller;
        table.getItems().addAll(getCourseList());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("No visible columns and/or data exist."));
        table.setEditable(true);
        addIdColumn(table);
        addNameColumn(table);
        addCodeColumn(table);
        addDescriptionColumn(table);
        return table;
    }

    public TableColumn<Course, Long> getIdColumn() {
        TableColumn<Course, Long> idCol = new TableColumn<>("Id");
        PropertyValueFactory<Course, Long> idCellValueFactory = new PropertyValueFactory<>("id");
        idCol.setCellValueFactory(idCellValueFactory);
        return idCol;
    }

    public TableColumn<Course, String> getNameColumn() {
        TableColumn<Course, String> nameCol = new TableColumn<>("Name");
        PropertyValueFactory<Course, String> nameCellValueFactory = new PropertyValueFactory<>("name");
        nameCol.setCellValueFactory(nameCellValueFactory);
        return nameCol;
    }

    public TableColumn<Course, String> getCodeColumn() {
        TableColumn<Course, String> codeCol = new TableColumn<>("Code");
        PropertyValueFactory<Course, String> codeCellValueFactory = new PropertyValueFactory<>("code");
        codeCol.setCellValueFactory(codeCellValueFactory);
        return codeCol;
    }

    public TableColumn<Course, String> getDescriptionColumn() {
        TableColumn<Course, String> descriptionCol = new TableColumn<>("Description");
        PropertyValueFactory<Course, String> descriptionCellValueFactory = new PropertyValueFactory<>("description");
        descriptionCol.setCellValueFactory(descriptionCellValueFactory);
        return descriptionCol;
    }

    public void addIdColumn(TableView<Course> table) {
        table.getColumns().add(getIdColumn());
    }

    public void addNameColumn(TableView<Course> table) {
        TableColumn<Course, String> nameCol = getNameColumn();
        nameCol.setCellFactory(TextFieldTableCell.<Course>forTableColumn());
        nameCol.setOnEditCommit((CellEditEvent<Course, String> t) -> {
            Course c = ((Course) t.getTableView().getItems().get(
                    t.getTablePosition().getRow()));
            Course c2 = this.controller.getModel().getCourseList().get(this.controller.getModel().getCourseList().indexOf(c));
            c.setName(t.getNewValue());
            c2.setName(t.getNewValue());
        });

        table.getColumns().add(nameCol);
    }

    public void addCodeColumn(TableView<Course> table) {
        TableColumn<Course, String> codeCol = getCodeColumn();
        codeCol.setCellFactory(TextFieldTableCell.<Course>forTableColumn());
        codeCol.setOnEditCommit((CellEditEvent<Course, String> t) -> {
            Course c = ((Course) t.getTableView().getItems().get(
                    t.getTablePosition().getRow()));
            Course c2 = this.controller.getModel().getCourseList().get(this.controller.getModel().getCourseList().indexOf(c));
            c.setCode(t.getNewValue());
            c2.setCode(t.getNewValue());

        });

        table.getColumns().add(codeCol);
    }

    public void addDescriptionColumn(TableView<Course> table) {
        TableColumn<Course, String> descriptionCol = getDescriptionColumn();
        descriptionCol.setCellFactory(TextFieldTableCell.<Course>forTableColumn());
        descriptionCol.setOnEditCommit((CellEditEvent<Course, String> t) -> {
            Course c = ((Course) t.getTableView().getItems().get(
                    t.getTablePosition().getRow()));
            Course c2 = this.controller.getModel().getCourseList().get(this.controller.getModel().getCourseList().indexOf(c));
            c.setDescription(t.getNewValue());
            c2.setDescription(t.getNewValue());

        });

        table.getColumns().add(descriptionCol);
    }

    public void prepopulateListWithDummyData() {
        Course p1 = new Course(Long.valueOf(1), "Databases", "DB6", "Oracle Databases");
        Course p2 = new Course(Long.valueOf(2), "Future and Emerging Technologies", "FET7", "New Technologies");
        Course p3 = new Course(Long.valueOf(3), "21st century Computing", "21CC", "Presentations and Homework");
        Course p4 = new Course(Long.valueOf(4), "Project Management", "PM7", "Managing a project");
        Course p5 = new Course(Long.valueOf(5), "Object Oriented Development", "OOD7", "OOP in action");
        Course p6 = new Course(Long.valueOf(6), "Mobile app Development", "MAD7", "android apps for advanced and intermediate programmers");

        courseList.add(p1);
        courseList.add(p2);
        courseList.add(p3);
        courseList.add(p4);
        courseList.add(p5);
        courseList.add(p6);
    }
}
