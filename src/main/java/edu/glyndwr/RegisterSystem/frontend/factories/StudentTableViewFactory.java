package edu.glyndwr.RegisterSystem.frontend.factories;

import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Student;
import edu.glyndwr.RegisterSystem.frontend.controller.RegisterSystemMainViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Component
public class StudentTableViewFactory {
// Returns an observable list of persons

    private ObservableList<Student> studentList;
    private RegisterSystemMainViewController controller;

    public StudentTableViewFactory() {
        studentList = FXCollections.<Student>observableArrayList();
    }

    public TableView<Student> getPrepopulatedTable() {
        TableView<Student> table = new TableView<>();
        this.prepopulateListWithDummyData();
        table.getItems().addAll(getStudentList());
        table.getColumns().addAll(getIdColumn(), getFirstNameColumn(),
                getLastNameColumn(), getStreetColumn(),
                getZipCodeColumn(), getCityColumn(), getCountryColumn());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("No visible columns and/or data exist."));
        return table;
    }

    public TableView<Student> getPrepopulatedEditableTable(RegisterSystemMainViewController controller) {
        TableView<Student> table = new TableView<>();
        this.prepopulateListWithDummyData();
        this.controller = controller;
        this.controller.getModel().getStudentList().addAll(getStudentList());
        table.getItems().addAll(getStudentList());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("No visible columns and/or data exist."));
        table.setEditable(true);
        addIdColumn(table);
        addFirstNameColumn(table);
        addLastNameColumn(table);
        addStreetColumn(table);
        addZipCodeColumn(table);
        addCityColumn(table);
        addCountryColumn(table);
        addStudentIDColumn(table);
        return table;
    }

    public TableView<Student> getNewTable() {
        TableView<Student> table = new TableView<>();
        this.prepopulateListWithDummyData();
        table.getColumns().addAll(getIdColumn(), getFirstNameColumn(),
                getLastNameColumn(), getStreetColumn(),
                getZipCodeColumn(), getCityColumn(), getCountryColumn());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("No visible columns and/or data exist."));
        return table;
    }

    public TableView<Student> getNewEditableTable(RegisterSystemMainViewController controller) {
        TableView<Student> table = new TableView<>();
        this.prepopulateListWithDummyData();
        this.controller = controller;
        table.getColumns().addAll(getIdColumn(), getFirstNameColumn(),
                getLastNameColumn(), getStreetColumn(),
                getZipCodeColumn(), getCityColumn(), getCountryColumn());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("No visible columns and/or data exist."));
        table.setEditable(true);
        addIdColumn(table);
        addFirstNameColumn(table);
        addLastNameColumn(table);
        addStreetColumn(table);
        addZipCodeColumn(table);
        addCityColumn(table);
        addCountryColumn(table);
        addStudentIDColumn(table);
        return table;
    }

    public ObservableList<Student> getStudentList() {
        return this.studentList;
    }

    public void getStudentList(ObservableList<Student> studentList) {
        this.studentList = studentList;
    }

    public TableColumn<Student, Long> getIdColumn() {
        TableColumn<Student, Long> idCol = new TableColumn<>("Id");
        PropertyValueFactory<Student, Long> idCellValueFactory = new PropertyValueFactory<>("id");
        idCol.setCellValueFactory(idCellValueFactory);
        return idCol;
    }

    public TableColumn<Student, String> getFirstNameColumn() {
        TableColumn<Student, String> firstNameCol = new TableColumn<>("First Name");
        PropertyValueFactory<Student, String> firstNameCellValueFactory = new PropertyValueFactory<>("firstName");
        firstNameCol.setCellValueFactory(firstNameCellValueFactory);
        return firstNameCol;
    }

    public TableColumn<Student, String> getLastNameColumn() {
        TableColumn<Student, String> lastNameCol = new TableColumn<>("Last Name");
        PropertyValueFactory<Student, String> lastNameCellValueFactory = new PropertyValueFactory<>("lastName");
        lastNameCol.setCellValueFactory(lastNameCellValueFactory);
        return lastNameCol;
    }

    public TableColumn<Student, String> getStreetColumn() {
        TableColumn<Student, String> streetCol = new TableColumn<>("Street");
        PropertyValueFactory<Student, String> streetCellValueFactory = new PropertyValueFactory<>("street");
        streetCol.setCellValueFactory(streetCellValueFactory);
        return streetCol;
    }

    public TableColumn<Student, String> getZipCodeColumn() {
        TableColumn<Student, String> zipCodeCol = new TableColumn<>("Zip Code");
        PropertyValueFactory<Student, String> zipCodeCellValueFactory = new PropertyValueFactory<>("zipCode");
        zipCodeCol.setCellValueFactory(zipCodeCellValueFactory);
        return zipCodeCol;
    }

    public TableColumn<Student, String> getCityColumn() {
        TableColumn<Student, String> cityCol = new TableColumn<>("City");
        PropertyValueFactory<Student, String> cityCellValueFactory = new PropertyValueFactory<>("city");
        cityCol.setCellValueFactory(cityCellValueFactory);
        return cityCol;
    }

    public TableColumn<Student, String> getCountryColumn() {
        TableColumn<Student, String> countryCol = new TableColumn<>("Country");
        PropertyValueFactory<Student, String> countryCellValueFactory = new PropertyValueFactory<>("country");
        countryCol.setCellValueFactory(countryCellValueFactory);
        return countryCol;
    }

    public TableColumn<Student, String> getStudentIDColumn() {
        TableColumn<Student, String> studentIDCol = new TableColumn<>("StudentID");
        PropertyValueFactory<Student, String> studentIDCellValueFactory = new PropertyValueFactory<>("studentID");
        studentIDCol.setCellValueFactory(studentIDCellValueFactory);
        return studentIDCol;
    }

    public void addIdColumn(TableView<Student> table) {
        table.getColumns().add(getIdColumn());
    }

    public void addFirstNameColumn(TableView<Student> table) {
        TableColumn<Student, String> firstNameCol = getFirstNameColumn();
        firstNameCol.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
        firstNameCol.setOnEditCommit((CellEditEvent<Student, String> t) -> {
            Student s = ((Student) t.getTableView().getItems().get(
                    t.getTablePosition().getRow()));
            Student s2 = this.controller.getModel().getStudentList().get(this.controller.getModel().getStudentList().indexOf(s));
            s.setFirstName(t.getNewValue());
            s2.setFirstName(t.getNewValue());

        });

        table.getColumns().add(firstNameCol);
    }

    public void addLastNameColumn(TableView<Student> table) {
        TableColumn<Student, String> lastNameCol = getLastNameColumn();
        lastNameCol.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
        lastNameCol.setOnEditCommit((CellEditEvent<Student, String> t) -> {
            Student s = ((Student) t.getTableView().getItems().get(
                    t.getTablePosition().getRow()));
            Student s2 = this.controller.getModel().getStudentList().get(this.controller.getModel().getStudentList().indexOf(s));
            s.setLastName(t.getNewValue());
            s2.setLastName(t.getNewValue());
        });

        table.getColumns().add(lastNameCol);
    }

    public void addStreetColumn(TableView<Student> table) {
        TableColumn<Student, String> streetCol = getStreetColumn();
        streetCol.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
        streetCol.setOnEditCommit((CellEditEvent<Student, String> t) -> {
            Student s = ((Student) t.getTableView().getItems().get(
                    t.getTablePosition().getRow()));
            Student s2 = this.controller.getModel().getStudentList().get(this.controller.getModel().getStudentList().indexOf(s));
            s.setStreet(t.getNewValue());
            s2.setStreet(t.getNewValue());
        });

        table.getColumns().add(streetCol);
    }

    public void addZipCodeColumn(TableView<Student> table) {
        TableColumn<Student, String> zipCodeCol = getZipCodeColumn();
        zipCodeCol.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
        zipCodeCol.setOnEditCommit((CellEditEvent<Student, String> t) -> {
            Student s = ((Student) t.getTableView().getItems().get(
                    t.getTablePosition().getRow()));
            Student s2 = this.controller.getModel().getStudentList().get(this.controller.getModel().getStudentList().indexOf(s));
            s.setZipCode(t.getNewValue());
            s2.setZipCode(t.getNewValue());
        });

        table.getColumns().add(zipCodeCol);
    }

    public void addCityColumn(TableView<Student> table) {
        TableColumn<Student, String> cityCol = getCityColumn();
        cityCol.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
        cityCol.setOnEditCommit((CellEditEvent<Student, String> t) -> {
            Student s = ((Student) t.getTableView().getItems().get(
                    t.getTablePosition().getRow()));
            Student s2 = this.controller.getModel().getStudentList().get(this.controller.getModel().getStudentList().indexOf(s));
            s.setCity(t.getNewValue());
            s2.setCity(t.getNewValue());
        });

        table.getColumns().add(cityCol);
    }

    public void addCountryColumn(TableView<Student> table) {
        TableColumn<Student, String> countryCol = getCountryColumn();
        countryCol.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
        countryCol.setOnEditCommit((CellEditEvent<Student, String> t) -> {
            Student s = ((Student) t.getTableView().getItems().get(
                    t.getTablePosition().getRow()));
            Student s2 = this.controller.getModel().getStudentList().get(this.controller.getModel().getStudentList().indexOf(s));
            s.setCountry(t.getNewValue());
            s2.setCountry(t.getNewValue());
        });

        table.getColumns().add(countryCol);
    }

    public void addStudentIDColumn(TableView<Student> table) {
        TableColumn<Student, String> studentIDCol = getStudentIDColumn();
        studentIDCol.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
        studentIDCol.setOnEditCommit((CellEditEvent<Student, String> t) -> {
            Student s = ((Student) t.getTableView().getItems().get(
                    t.getTablePosition().getRow()));
            Student s2 = this.controller.getModel().getStudentList().get(this.controller.getModel().getStudentList().indexOf(s));
            s.setStudentID(t.getNewValue());
            s2.setStudentID(t.getNewValue());
        });

        table.getColumns().add(studentIDCol);
    }

    public void prepopulateListWithDummyData() {
        Student p1 = new Student(Long.valueOf(1), "Mark", "Pearson", "First Avenue 2", "1200", "Los Angeles", "USA", "s1234123423");
        Student p2 = new Student(Long.valueOf(2), "Tom", "Hoover", "Kings Cross 3", "2350", "Denver", "USA", "s1234123424");
        Student p3 = new Student(Long.valueOf(3), "David", "Mason", "Bond Street 5", "1100", "Manchester", "Great Britain", "s1234123425");
        Student p4 = new Student(Long.valueOf(4), "Mary", "Miller", "Baker Street 86", "1200", "London", "Great Britain", "s1234123426");
        Student p5 = new Student(Long.valueOf(5), "Martha", "Lancester", "Main Street 375", "9923", "Sidney", "Australia", "s1234123427");
        Student p6 = new Student(Long.valueOf(6), "Henry", "Forsyth", "Main Street 3", "37472", "Toronto", "Canada", "s1234123428");

        studentList.add(p1);
        studentList.add(p2);
        studentList.add(p3);
        studentList.add(p4);
        studentList.add(p5);
        studentList.add(p6);
    }
}
