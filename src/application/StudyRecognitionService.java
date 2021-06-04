package application;

import controller.StudyDisplayController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
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

        GridPane gridPane = new GridPane();
        TermData data = new TermData();
        SRS studySet = new SRS(data);
        StudyDisplay mainDisplay = new StudyDisplay();
        StudyDisplayController controller = new StudyDisplayController(data, mainDisplay, studySet);
        StudyFiles.loadUserData(data);

        Scene scene = new Scene(gridPane, 1600, 900);
        Stage stage = new Stage();

        stage.setOnCloseRequest(
                (WindowEvent t) -> {
                    StudyFiles.saveUserData(data.formatData()); //save user data on close
                    Platform.exit();
                    System.exit(0);
                }
        );

        mainDisplay.addControls(gridPane);
        controller.initControls();
        stage.setTitle("Study Program");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
