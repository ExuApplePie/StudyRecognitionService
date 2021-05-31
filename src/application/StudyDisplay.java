package application;

import controller.StudyDisplayController;
import controller.StudyFiles;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.SRS;
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
public class StudyDisplay extends Application {
    
    public void updateDisplay() {
    }
    
    private GridPane gridPane;
    private TermData data = new TermData();
    private StudyDisplayController controller = new StudyDisplayController();
    private SRS studySet = new SRS();

//    private void keyEvent (KeyEvent evt) {
//        if (evt.getCode() == KeyCode.ENTER) {
//            System.out.println("Enter was pressed");
//        }
//    }
    private Label label(String text) {
        Label label = new Label();
        label.setLayoutX(0);
        label.setLayoutY(0);
        label.setMaxWidth(500);
        label.setMinHeight(400);
        label.setText(text);
        
        return label;
    }
    
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
    
    private TextField textField(String text) {
        TextField textField = new TextField();
        textField.setLayoutX(0);
        textField.setLayoutY(1);
        textField.setMinWidth(450);
        textField.setMinHeight(25);
        textField.setText(text);
//        textField.setOnKeyPressed(new EventHandler<KeyEvent>(){
//            @Override
//            public void handle(KeyEvent e) {
//                textField.setText("oops");
//            }
//        });
        return textField;
    }
    
    private Button button(String title) {
        Button button = new Button(title);
        button.setPrefSize(200, 200);
        return button;
    }

//
//    private Button definitionButton() {
//        Button button = new Button("Print Bye World");
//        button.setOnAction((ActionEvent event) -> {
//            System.out.println("Bye World");
//            StudyDisplay.showSaveDialog();
//        });
//        return button;
//    }
    Button valueButton = button("Random Definition"); //corresponds to values - name appearing on button is different for user convenience
    Button definitionButton = button("Random Term"); //corresponds to definitions
    Button importDataButton = button("Import word list");
    Button saveDataButton = button("Save word list");
    Label questionLabel = label("Questions Appear Here");
    TextField ansField = textField("Type your answers here");
    TextField scoreField = textField("Your score for the term shows up here");
    
    private void addControls() {
//        gridPane.getRowConstraints().add(new RowConstraints(100));
//        setAction();
        gridPane.add(valueButton, 0, 0);
        controller.showValue(valueButton, questionLabel, ansField, studySet);
        
        gridPane.add(definitionButton, 0, 1);
        controller.showDefinition(definitionButton, questionLabel, ansField, studySet);
        
        gridPane.add(questionLabel, 1, 1);
        gridPane.add(ansField, 1, 2);
        gridPane.add(scoreField, 1, 3);
        controller.checkAnswerOnPress(questionLabel, ansField, scoreField, studySet);
        
        gridPane.add(importDataButton, 3, 0);
        controller.importData(importDataButton);
        
        gridPane.add(saveDataButton, 3, 1);
        controller.saveData(saveDataButton);
    }
    
    private void startForm() {
        controller.initData(data);
        studySet.initData(data);
        gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 1600, 900);
        Stage stage = new Stage();
        stage.setTitle("Study Program");
        stage.setScene(scene);
        addControls();
        stage.show();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        startForm();
    }

//    public void setText(TextField txtFld, String newText) {
//        Platform.runLater(() -> {
//            txtFld.setText(newText);
//        });
//    }
}
