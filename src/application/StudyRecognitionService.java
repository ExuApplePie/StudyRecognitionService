package application;

import controller.StudyDisplayController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.SRS;
import models.StudyFiles;
import models.TermData;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 84038001
 */
public class StudyRecognitionService extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        TermData data = new TermData();
        SRS studySet = new SRS(data);
        StudyDisplay mainDisplay = new StudyDisplay();
        StudyDisplayController controller = new StudyDisplayController(data, mainDisplay, studySet);
        StudyFiles.loadUserData(data);
        controller.updateTermList();


        primaryStage.setOnCloseRequest(
                (WindowEvent t) -> {
                    StudyFiles.saveUserData(data.formatData()); //save user data on close TODO add the reminders
                    Platform.exit();
                    System.exit(0);
                }
        );

        mainDisplay.addControls();
        controller.initControls(primaryStage);
        primaryStage.setTitle("Study Program");
        primaryStage.setScene(mainDisplay.scene1);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
