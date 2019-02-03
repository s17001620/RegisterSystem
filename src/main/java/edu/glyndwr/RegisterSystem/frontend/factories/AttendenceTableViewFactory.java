/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.glyndwr.RegisterSystem.frontend.factories;

import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Attendence;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseDate;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseMember;
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
public class AttendenceTableViewFactory {
        public TableView<Attendence> getNewTable() {
        TableView<Attendence> table = new TableView<>();
        table.getColumns().addAll(getIdColumn(),getCourseDateColumn(),getCourseMemberColumn());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("No visible columns and/or data exist."));
        return table;
    }
    
        public TableColumn<Attendence, Long> getIdColumn() {
        TableColumn<Attendence, Long> idCol = new TableColumn<>("Id");
        PropertyValueFactory<Attendence, Long> idCellValueFactory = new PropertyValueFactory<>("id");
        idCol.setCellValueFactory(idCellValueFactory);
        return idCol;
    }

    public TableColumn<Attendence, CourseDate> getCourseDateColumn() {
        TableColumn<Attendence, CourseDate> courseDateCol = new TableColumn<>("Date");
        PropertyValueFactory<Attendence, CourseDate> courseDateCellValueFactory = new PropertyValueFactory<>("courseDate");
        courseDateCol.setCellValueFactory(courseDateCellValueFactory);
        return courseDateCol;
    }

    public TableColumn<Attendence, CourseMember> getCourseMemberColumn() {
        TableColumn<Attendence, CourseMember> courseMemberCol = new TableColumn<>("CourseMember");
        PropertyValueFactory<Attendence, CourseMember> courseCellValueFactory = new PropertyValueFactory<>("courseMember");
        courseMemberCol.setCellValueFactory(courseCellValueFactory);
        return courseMemberCol;
    }
}
