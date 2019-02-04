package edu.glyndwr.RegisterSystem;

import edu.glyndwr.RegisterSystem.frontend.controller.RegisterSystemMainViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@Log
@SpringBootApplication
@ComponentScan("edu.glyndwr")
@EnableJpaRepositories
public class RegisterSystemApplication extends Application {
    private ConfigurableApplicationContext context;
    @Autowired
    private RegisterSystemMainViewController fegisterSystemMainViewControllerNoFxml;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        fegisterSystemMainViewControllerNoFxml = (RegisterSystemMainViewController) context.getBean(RegisterSystemMainViewController.class);
        fegisterSystemMainViewControllerNoFxml.initializeStage(primaryStage);
    }
    
    @Override
    public void init() throws Exception {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(RegisterSystemApplication.class);
        context = builder.run(getParameters().getRaw().toArray(new String[0]));
    }
    
      @Override
    public void stop() throws Exception {
        context.close();
    }

}
