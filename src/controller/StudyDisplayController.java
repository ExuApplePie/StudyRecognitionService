/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import application.StudyDisplay;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.TermData;
import models.SRS;

/**
 *
 * @author 84038001
 */
public class StudyDisplayController {

    private TermData data;
    private StudyDisplay mainDisplay;
    private SRS studySet;

    public StudyDisplayController(TermData data, StudyDisplay mainDisplay, SRS studySet) {
        this.mainDisplay = mainDisplay;
        this.data = data;
        this.studySet = studySet;
    }

    public void initControls(Stage primaryStage) {
        showDefinition();
        showValue();
        checkAnswerOnPress();
        importData();
        saveData();
        startTimer();
        changeToScene1(primaryStage);
        changeToScene2(primaryStage);
        toggleFullScreen(primaryStage);
    }

    public void showDefinition() {
        this.mainDisplay.definitionButton.setOnAction(
                (ActionEvent event) -> {
                    this.mainDisplay.questionLabel.setText(this.studySet.displayDefinition());
                    this.mainDisplay.ansField.requestFocus();
                    this.mainDisplay.hideImages();
                    System.out.println(this.data.formatData());
                });
    }

    public void showValue() {
        this.mainDisplay.valueButton.setOnAction(
                (ActionEvent event) -> {
                    this.mainDisplay.questionLabel.setText(this.studySet.displayValue());
                    this.mainDisplay.ansField.requestFocus();
                    this.mainDisplay.hideImages();
                });

    }

    public void checkAnswerOnPress() {
        this.mainDisplay.ansField.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    boolean b = this.studySet.checkAns(this.mainDisplay.ansField.getText(), this.mainDisplay.questionLabel.getText()); //replace the two parameters with getting them from the text areas
                    if (b) {
                        this.mainDisplay.scoreField.setText("correct (+1) Score: " + this.studySet.getScore(this.mainDisplay.questionLabel.getText()));
                        this.mainDisplay.checkMarkImage.setVisible(b);
                    } else {
                        this.mainDisplay.scoreField.setText("incorrect (-1) Score: " + this.studySet.getScore(this.mainDisplay.questionLabel.getText()));
                        this.mainDisplay.redXImage.setVisible(!b);
                    }
                } catch (IllegalArgumentException e) {
//                    System.out.println("pressed enter with nothing"); 
                }
                this.mainDisplay.clearText();
            }
        });
    }

    public void importData() {
        this.mainDisplay.importDataButton.setOnAction(
                (ActionEvent event) -> {
                    File file = StudyDisplayController.showOpenDialog();
                    try {
                        this.data.loadData(file);
                    } catch (NullPointerException | FileNotFoundException e) {
                        //do nothing if user chooses nothing
                    } catch (NumberFormatException e) {
                        this.data.initializeData(file);
                    }
                }
        );
    }

    public void saveData() {
        this.mainDisplay.saveDataButton.setOnAction((ActionEvent event) -> {
            File file = StudyDisplayController.showSaveDialog();
            this.data.saveData(file);
        });
    }

    public void startTimer() {
        this.mainDisplay.startTimerButton.setOnAction((ActionEvent event) -> {
            TimerRunner tr = new TimerRunner();
            try {
                tr.startTimer(Double.parseDouble(this.mainDisplay.timerField.getText()));
            } catch (NumberFormatException e) {
                this.mainDisplay.timerField.setText("use a real decimal number please");
            }
        });
    }

    public void changeToScene1(Stage primaryStage) {
        this.mainDisplay.scene1Button.setOnAction(e -> primaryStage.setScene(this.mainDisplay.scene1));
    }

    public void changeToScene2(Stage primaryStage) {
        this.mainDisplay.scene2Button.setOnAction(e -> primaryStage.setScene(this.mainDisplay.scene2));

    }

    public void toggleFullScreen(Stage primaryStage) {
        this.mainDisplay.toggleFullScreenButton.setOnAction((ActionEvent event) -> {
            boolean b = primaryStage.isFullScreen();
            primaryStage.setFullScreen(!b);
        });
    }

    public static void showTimerEndWindow() {
        Platform.runLater(() -> {
            Stage popupWindow = new Stage();
            popupWindow.initModality(Modality.APPLICATION_MODAL);
            popupWindow.setTitle("The timer has ended");
            Label label1 = new Label("Timer End!");
            Button button1 = new Button("close this window");
            button1.setOnAction(e -> {
                popupWindow.close();
            });

            VBox layout = new VBox(10);
            layout.getChildren().addAll(label1, button1);
            layout.setAlignment(Pos.CENTER);
            Scene scene1 = new Scene(layout, 300, 250);
            popupWindow.setScene(scene1);
            popupWindow.showAndWait();
        });
    }

    public static File showSaveDialog() {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fc.getExtensionFilters().add(extFilter);
        File file = fc.showSaveDialog(null);
        return file;
    }

    public static File showOpenDialog() {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fc.getExtensionFilters().add(extFilter);
        File file = fc.showOpenDialog(null);
        return file;
    }
}
