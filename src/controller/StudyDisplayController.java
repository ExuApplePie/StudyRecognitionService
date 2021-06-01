/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import application.StudyDisplay;
import java.io.File;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Popup;
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

    public void initData(TermData data) {
        if (this.data != null) {
            throw new IllegalStateException("Data can only be initialized once");
        }

        this.data = data;
    }

    public void load() {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
    }

    public void save() {
        FileChooser chooser = new FileChooser();
        File file = chooser.showSaveDialog(null);
    }

    public void setPressAction(Button button, String str) {
        button.setOnAction(
                (ActionEvent event) -> {
                    System.out.println(str);
                }
        );
    }

    public void showDefinition(Button button, Label label, TextInputControl textControl, SRS srs) {
        button.setOnAction(
                (ActionEvent event) -> {
                    label.setText(srs.displayDefinition());
                    textControl.requestFocus();
                });
    }

    public void showValue(Button button, Label label, TextInputControl textControl, SRS srs) {
        button.setOnAction(
                (ActionEvent event) -> {
                    label.setText(srs.displayValue());
                    textControl.requestFocus();
                });

    }

    public void checkAnswerOnPress(Label questionLabel, TextInputControl ansField, TextInputControl scoreField, SRS srs) {
        ansField.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    boolean b = srs.checkAns(ansField.getText(), questionLabel.getText()); //replace the two parameters with getting them from the text areas
                    if (b) {
                        scoreField.setText("correct (+1) Score: " + srs.getScore(questionLabel.getText()));
                    } else {
                        scoreField.setText("incorrect (-1) Score: " + srs.getScore(questionLabel.getText()));
                    }
                } catch (IllegalArgumentException e) {
//                    System.out.println("pressed enter with nothing"); 
                }
                questionLabel.setText(""); //clear it so that you can't add to the score for the same answer multiple times
                ansField.setText("");
            }
        });
    }

    public void importData(Button button) {
        button.setOnAction(
                (ActionEvent event) -> {
                    File file = StudyDisplayController.showOpenDialog();
                    try {
                        System.out.println("1");
                        this.data.loadData(file);
                    } catch (NullPointerException e) {
                        System.out.println("2");
                    } catch (NumberFormatException e) {
                        System.out.println("3");
                        this.data.initializeData(file);
                    }
                    System.out.println("4 ");
                }
        );
    }

    public void saveData(Button button) {
        button.setOnAction((ActionEvent event) -> {
            File file = StudyDisplayController.showSaveDialog();
            this.data.saveData(file);
        });
    }

    public void startTimer(Button button, TextInputControl timeParam) {
        button.setOnAction((ActionEvent event) -> {
            TimerRunner tr = new TimerRunner();
            try {
                tr.startTimer(Double.parseDouble(timeParam.getText()), button.getScene().getWindow());
            } catch (NumberFormatException e) {
                timeParam.setText("use a real decimal number please");
            }
        });
    }

    public static void showTimerEndWindow(Window window) {
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
