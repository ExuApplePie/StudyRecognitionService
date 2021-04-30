/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Data;
import models.SRS;

/**
 *
 * @author 84038001
 */
public class StudyDisplayController {

    private Data data;

    public void initData(Data data) {
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

    public void showTerm(Button button, TextInputControl textControl, SRS srs) {
        button.setOnAction(
                (ActionEvent event) -> {
                    textControl.setText(srs.displayTerm());
                });
    }

    public void showDefinition(Button button, TextInputControl textControl, SRS srs) {
        button.setOnAction(
                (ActionEvent event) -> {
                    textControl.setText(srs.displayDefinition());
                });

    }

    public void checkAnswerOnPress(TextInputControl questionField, TextInputControl ansField, SRS srs) {
        ansField.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                boolean b = srs.checkAns(ansField.getText(), questionField.getText()); //replace the two parameters with getting them from the text areas
                System.out.println(b);
            }
        });
    }

}
