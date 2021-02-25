package controller.view;

import controller.StudyFiles;
import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 84038001
 */
public class StudyDisplay extends Application {

    public StudyDisplay() {
    }

    public void updateDisplay() {
    }

    private GridPane gridPane;

//    private void keyEvent (KeyEvent evt) {
//        if (evt.getCode() == KeyCode.ENTER) {
//            System.out.println("Enter was pressed");
//        }
//    }
    private TextArea textArea() {
        TextArea textArea = new TextArea();
        textArea.setLayoutX(0);
        textArea.setLayoutY(0);
        textArea.setMaxWidth(450);
        textArea.setMinHeight(380);
        textArea.setText("this is a text area");
//        textArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
////               if (event.getCode() == KeyCode.ENTER) {
////                   System.out.println("Enter Pressed");
////               }
//                System.out.println(event.getCode().toString());
//            }
//        });
        return textArea;
    }

    private TextField textField() {
        TextField textField = new TextField();
        textField.setLayoutX(0);
        textField.setLayoutY(1);
        textField.setMinWidth(450);
        textField.setMinHeight(25);
        return textField;
    }

    private Button button() {
        Button button = new Button("Print Hello World");
        button.setPrefSize(200, 200);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World");
            }
        });
        return button;
    }

    private Button button2() {
        Button button = new Button("Print Bye World");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Bye World");
                StudyDisplay.showSaveDialog();
            }
        });
        return button;
    }

    private void addControls() {
//        gridPane.getRowConstraints().add(new RowConstraints(100));
        gridPane.add(button(), 0, 0);
        gridPane.add(button2(), 0, 1);
    }

    private void startForm() {
        gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 1600, 900);
        Stage stage = new Stage();
        stage.setTitle("Study Program");
        stage.setScene(scene);
        addControls();
        stage.show();
    }

    public void start(Stage primaryStage) throws Exception {
        startForm();
    }

    public static void showSaveDialog() {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fc.getExtensionFilters().add(extFilter);
        File file = fc.showSaveDialog(null);
        StudyFiles.saveFile(file);
    }
}