/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import application.StudyDisplay;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
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
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.TermData;
import models.SRS;
import models.Term;

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
        this.showDefinition();
        this.showValue();
        this.checkAnswerOnPress();
        this.importData();
        this.saveData();
        this.startTimer();
        this.setDailyReminders();
        this.changeToScene1(primaryStage);
        this.changeToScene2(primaryStage);
        this.toggleFullScreen(primaryStage);
        this.addTerm();
        this.removeTerm();
        this.doOCR();
    }

    public void showDefinition() {
        this.mainDisplay.definitionButton.setOnAction(
                (ActionEvent event) -> {
                    this.mainDisplay.questionLabel.setText(this.studySet.displayDefinition());
                    this.mainDisplay.ansField.requestFocus();
//                    this.mainDisplay.hideImages();
                });
    }

    public void showValue() {
        this.mainDisplay.valueButton.setOnAction(
                (ActionEvent event) -> {
                    this.mainDisplay.questionLabel.setText(this.studySet.displayValue());
                    this.mainDisplay.ansField.requestFocus();
//                    this.mainDisplay.hideImages();
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
                this.displayNewQuestion();
            }
        });
    }

    public void displayNewQuestion() {
        if (this.studySet.getDefaultMode()) {
            this.mainDisplay.valueButton.fire();
        } else {
            this.mainDisplay.definitionButton.fire();
        }
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
                    this.updateTermList();
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

    public void setDailyReminders() {
        this.mainDisplay.setDailyReminderButton.setOnAction((ActionEvent event) -> {
            TimerRunner tr = new TimerRunner();
            tr.scheduleDailyReminders(this.mainDisplay.dateField.getText());
            this.mainDisplay.dateField.setText("Sucess!");
        });
    }

    public void changeToScene1(Stage primaryStage) {
        this.mainDisplay.scene1Button.setOnAction(e -> primaryStage.setScene(this.mainDisplay.scene1));
    }

    public void changeToScene2(Stage primaryStage) {
        this.mainDisplay.scene2Button.setOnAction((ActionEvent event) -> {
            primaryStage.setScene(this.mainDisplay.scene2);
            this.updateTermList();
        });
    }

    public void toggleFullScreen(Stage primaryStage) {
        this.mainDisplay.toggleFullScreenButton.setOnAction((ActionEvent event) -> {
            boolean b = primaryStage.isFullScreen();
            primaryStage.setFullScreen(!b);
        });
    }

    public void updateTermList() {
        this.mainDisplay.termList.getItems().clear();
        Scanner sc = new Scanner(this.data.formatData());
        while (sc.hasNext()) {
            sc.useDelimiter(";");
            this.mainDisplay.termList.getItems().add(sc.next() + " " + sc.next() + " " + sc.next());
            sc.useDelimiter("\n");
            sc.nextLine();
        }
        sc.close();
    }

    public void addTerm() {
        this.mainDisplay.addTermButton.setOnAction((ActionEvent event) -> {
            this.data.getTermList()
                    .add(new Term(this.mainDisplay.definitionField.getText(), this.mainDisplay.valueField.getText(), 0));
            this.updateTermList();
        });
    }

    public void removeTerm() {
        this.mainDisplay.removeTermButton.setOnAction((ActionEvent event) -> {
            int selectedIndx = this.mainDisplay.termList.getSelectionModel().getSelectedIndex();
            this.mainDisplay.termList.getItems().remove(selectedIndx);
            this.data.getTermList().remove(selectedIndx);
        });
    }

    public void doOCR() {
        this.mainDisplay.doOCRButton.setOnAction((ActionEvent event) -> {
            File file = StudyDisplayController.showOpenDialog();
            System.out.println(models.imageOCR.scanImage(file));
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
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");
//FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("jpg", "*.jpg");
        fc.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.tiff"),
                new ExtensionFilter("All Files", "*.*"));
        File file = fc.showOpenDialog(null);
        return file;
    }
}
