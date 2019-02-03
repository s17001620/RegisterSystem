

package edu.glyndwr.RegisterSystem.frontend.factories.facades;

import edu.glyndwr.RegisterSystem.frontend.controller.RegisterSystemMainViewControllerNoFxml;
import edu.glyndwr.RegisterSystem.frontend.factories.AttendenceNewDataPaneFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.AttendenceTableViewFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.CourseDateNewDataPaneFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.CourseDateTableViewFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.CourseMemberNewDataPaneFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.CourseMemberTableViewFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.CourseNewDataPaneFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.CourseTableViewFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.StudentNewDataPaneFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.StudentTableViewFactory;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Component
public class FrontendFactoryFacade {
    @Autowired
    private AttendenceTableViewFactory attendenceTableViewFactory;
    @Autowired
    private AttendenceNewDataPaneFactory attendenceNewDataPaneFactory;
    @Autowired
    private CourseDateTableViewFactory courseDateTableViewFactory;
    @Autowired
    private CourseDateNewDataPaneFactory courseDateNewDataPaneFactory;
     @Autowired
    private CourseMemberTableViewFactory courseMemberTableViewFactory;
    @Autowired
    private CourseMemberNewDataPaneFactory courseMemberNewDataPaneFactory;
    @Autowired
    private StudentTableViewFactory studentTableViewFactory;
    @Autowired
    private StudentNewDataPaneFactory studentNewDataPaneFactory;
    @Autowired
    private CourseTableViewFactory courseTableViewFactory;
    @Autowired
    private CourseNewDataPaneFactory courseNewDataPaneFactory;
    
    public Stage buildFrontendUI(RegisterSystemMainViewControllerNoFxml controller, Stage stage){
        VBox root = new VBox();
        root.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");

        GridPane courseMemberNewDataPane;
        GridPane studentNewDataPane;
        GridPane courseNewDataPane;
        GridPane courseDateNewDataPane;
        GridPane attendenceNewDataPane;
        
        controller.setStudentTable(studentTableViewFactory.getPrepopulatedEditableTable(controller));
        studentNewDataPane = studentNewDataPaneFactory.buildNewPersonDataPane(controller);
        
        controller.setCourseTable(courseTableViewFactory.getPrepopulatedEditableTable(controller));
        controller.getCourseTable().prefWidthProperty().bind(stage.widthProperty().subtract(50.0));
        courseNewDataPane = courseNewDataPaneFactory.buildNewCourseDataPane(controller);
        
        controller.setCourseMemberTable(courseMemberTableViewFactory.getNewTable());
        controller.getCourseMemberTable().prefWidthProperty().bind(stage.widthProperty().subtract(50.0));
        courseMemberNewDataPane = courseMemberNewDataPaneFactory.buildNewCourseMemberDataPane(controller);

        controller.setCourseDateTable(courseDateTableViewFactory.getNewTable());
        controller.getCourseDateTable().prefWidthProperty().bind(stage.widthProperty().subtract(50.0));
        courseDateNewDataPane = courseDateNewDataPaneFactory.buildNewCourseDateDataPane(controller);
        
        controller.setAttendenceTable(attendenceTableViewFactory.getNewTable());
        controller.getAttendenceTable().prefWidthProperty().bind(stage.widthProperty().subtract(50.0));
        attendenceNewDataPane = attendenceNewDataPaneFactory.buildNewAttendenceDataPane(controller);
        
        TabPane tabPane = new TabPane();
        Tab studentTab = new Tab();
        studentTab.setText("Register Student");
        FlowPane pane = new FlowPane();
        pane.getChildren().addAll(studentNewDataPane, controller.getStudentTable());
        studentTab.setContent(pane);
        tabPane.getTabs().add(studentTab);

        Tab courseTab = new Tab();
        courseTab.setText("Register Course");
        FlowPane coursepane = new FlowPane();
        coursepane.getChildren().addAll(courseNewDataPane, controller.getCourseTable());
        courseTab.setContent(coursepane);
        tabPane.getTabs().add(courseTab);

        Tab courseMemberTab = new Tab();
        courseMemberTab.setText("Register Course Members");
        FlowPane courseMemberPane = new FlowPane();
        courseMemberPane.getChildren().addAll(courseMemberNewDataPane, controller.getCourseMemberTable());
        courseMemberTab.setContent(courseMemberPane);
        tabPane.getTabs().add(courseMemberTab);

        Tab courseDateTab = new Tab();
        courseDateTab.setText("Register Course Dates");
        FlowPane courseDatePane = new FlowPane();
        courseDatePane.getChildren().addAll(courseDateNewDataPane, controller.getCourseDateTable());
        courseDateTab.setContent(courseDatePane);
        tabPane.getTabs().add(courseDateTab);

        Tab attendenceTab = new Tab();
        attendenceTab.setText("Book Attendence");
        FlowPane attendencePane = new FlowPane();
        attendencePane.getChildren().addAll(attendenceNewDataPane, controller.getAttendenceTable());
        attendenceTab.setContent(attendencePane);
        tabPane.getTabs().add(attendenceTab);
        
        root.getChildren().addAll(tabPane);
        root.setSpacing(5);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Student Registration");
        return stage;
    }
}
