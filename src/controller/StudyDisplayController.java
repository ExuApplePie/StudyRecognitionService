/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import application.StudyDisplay;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
