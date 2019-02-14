package edu.glyndwr.RegisterSystem.frontend.factories.facades;

import edu.glyndwr.RegisterSystem.frontend.controller.RegisterSystemMainViewController;
import edu.glyndwr.RegisterSystem.frontend.factories.AttendenceNewDataPaneFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.AttendenceTableViewFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.CourseDateNewDataPaneFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.CourseDateTableViewFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.CourseMemberNewDataPaneFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.CourseMemberTableViewFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.CourseNewDataPaneFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.CourseTableViewFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.ProfileDataTableViewFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.ProfileHeaderPaneFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.StudentNewDataPaneFactory;
import edu.glyndwr.RegisterSystem.frontend.factories.StudentTableViewFactory;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
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
    @Autowired
    private ProfileDataTableViewFactory profileDataTableViewFactory;
    @Autowired
    private ProfileHeaderPaneFactory profileHeaderPaneFactory;

    public Stage buildFrontendUI(RegisterSystemMainViewController controller, Stage stage) {
        VBox root = new VBox(3);
        root.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");

        GridPane courseMemberNewDataPane;
        GridPane studentNewDataPane;
        GridPane courseNewDataPane;
        GridPane courseDateNewDataPane;
        GridPane attendenceNewDataPane;
        GridPane profilAttendenceDataPane;

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

        controller.setWrappedAttendencesTable(profileDataTableViewFactory.getNewTable());
        controller.getWrappedAttendencesTable().prefWidthProperty().bind(stage.widthProperty().subtract(50.0));
        profilAttendenceDataPane = profileHeaderPaneFactory.buildNewPersonDataPane(controller);

        controller.getTabPane().setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        Tab studentTab = new Tab();
        studentTab.setText("Register Student");
        FlowPane pane = new FlowPane();
        pane.setVgap(5);
        pane.setHgap(5);
        pane.getChildren().addAll(studentNewDataPane, controller.getStudentTable());
        studentTab.setContent(pane);
        controller.getTabPane().getTabs().add(studentTab);

        Tab courseTab = new Tab();
        courseTab.setText("Register Course");
        FlowPane coursepane = new FlowPane();
        coursepane.setVgap(5);
        coursepane.setHgap(5);
        coursepane.getChildren().addAll(courseNewDataPane, controller.getCourseTable());
        courseTab.setContent(coursepane);
        controller.getTabPane().getTabs().add(courseTab);

        Tab courseMemberTab = new Tab();
        courseMemberTab.setId("courseMemberTab");
        courseMemberTab.setText("Register Course Members");
        FlowPane courseMemberPane = new FlowPane();
        courseMemberPane.setId("courseMemberPane");
        courseMemberPane.setVgap(5);
        courseMemberPane.setHgap(5);
        courseMemberPane.getChildren().addAll(courseMemberNewDataPane, controller.getCourseMemberTable());
        courseMemberTab.setContent(courseMemberPane);
        controller.getTabPane().getTabs().add(courseMemberTab);

        Tab courseDateTab = new Tab();
        courseDateTab.setText("Register Course Dates");
        FlowPane courseDatePane = new FlowPane();
        courseDatePane.setVgap(5);
        courseDatePane.setHgap(5);
        courseDatePane.getChildren().addAll(courseDateNewDataPane, controller.getCourseDateTable());
        courseDateTab.setContent(courseDatePane);
        controller.getTabPane().getTabs().add(courseDateTab);

        Tab attendenceTab = new Tab();
        attendenceTab.setText("Book Attendence");
        FlowPane attendencePane = new FlowPane();
        attendencePane.setVgap(5);
        attendencePane.setHgap(5);
        attendencePane.getChildren().addAll(attendenceNewDataPane, controller.getAttendenceTable());
        attendenceTab.setContent(attendencePane);
        controller.getTabPane().getTabs().add(attendenceTab);

        Tab profileTab = new Tab();
        profileTab.setText("Profile");
        FlowPane profilePane = new FlowPane();
        profilePane.setVgap(5);
        profilePane.setHgap(5);
        profilePane.getChildren().addAll(profilAttendenceDataPane, controller.getWrappedAttendencesTable());
        profileTab.setContent(profilePane);
        controller.getTabPane().getTabs().add(profileTab);

        root.getChildren().addAll(controller.getTabPane());
        root.setSpacing(5);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Student Registration");
        return stage;
    }
    
    public void rebuildCourseMemberAfterStudentAdded(RegisterSystemMainViewController controller){
        GridPane courseMemberNewDataPane;
        courseMemberNewDataPane = courseMemberNewDataPaneFactory.buildNewCourseMemberDataPane(controller);
        FlowPane courseMemberPane;
        courseMemberPane=(FlowPane) controller.getStage().getScene().lookup("#courseMemberPane");
        courseMemberPane.getChildren().clear();
        courseMemberPane.setId("courseMemberPane");
        courseMemberPane.setVgap(5);
        courseMemberPane.setHgap(5);
        courseMemberPane.getChildren().addAll(courseMemberNewDataPane, controller.getCourseMemberTable());
    }
}
