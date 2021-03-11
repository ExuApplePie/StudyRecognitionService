/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.models.BackendModels;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 *
 * @author 84038001
 */
public class StudyDisplayController {

    public StudyDisplayController(BackendModels bm) {
        this.bm = bm;
    }
    BackendModels bm;

    public void setPressAction(Button button, String str) {
        button.setOnAction(
                (ActionEvent event) -> {
//                    textField1.setText(bm.service.displayQuestion());
                    System.out.println(str);
                }
        );
    }
}
