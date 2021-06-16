/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import controller.TimerRunner;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author 84038001
 */
public class PopupWindows {

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

    public static void showCreateReminderWindow() {
        Platform.runLater(() -> {
            Stage popupWindow = new Stage();
            popupWindow.initModality(Modality.APPLICATION_MODAL);
            popupWindow.setTitle("Create a New Reminder");
            TextField titleField = new TextField();
            titleField.setPromptText("Enter the title of the reminder");
            TextField contentField = new TextField();
            contentField.setPromptText("Enter what you want the reminder to display");
            DatePicker datePicker = new DatePicker();
            ObservableList<String> options = FXCollections.observableArrayList();
            for (int i = 0; i < 24; i++) {
                options.add(i + ":00");
                options.add(i + ":15");
                options.add(i + ":30");
                options.add(i + ":45");
            }
            ComboBox timeComboBox = new ComboBox(options);
            timeComboBox.setPromptText("Time in (HH:MM)");
            timeComboBox.setEditable(true);
            Button createReminderButton = new Button("Create Reminder and Exit");
            createReminderButton.setOnAction((ActionEvent event) -> {
                try {
                    LocalDate ld = datePicker.getValue();
                    String time = (String) timeComboBox.getValue();
                    LocalDateTime ldt = ld.atTime(Integer.parseInt(time.substring(0, time.indexOf(":"))), Integer.parseInt(time.substring(time.indexOf(":") + 1, time.length())));
                    OffsetDateTime odt = OffsetDateTime.now(ZoneId.systemDefault());
                    Instant instant = ldt.toInstant(odt.getOffset());
                    Date date = Date.from(instant);
                    //add to Reminders.allReminders
                    TimerRunner.scheduleReminders(titleField.getText(), contentField.getText(), date);
                    popupWindow.close();
                } catch (Exception e) {
                    //likely means user entered an improper string
                }
            });
            VBox layout = new VBox(10);
            layout.getChildren().addAll(titleField, contentField, datePicker, timeComboBox, createReminderButton);
            layout.setAlignment(Pos.CENTER);
            Scene scene1 = new Scene(layout, 300, 250);
            popupWindow.setScene(scene1);
            popupWindow.showAndWait();
        });
    }
    /*
    private void startForm() {
    controller.initData(data, StudyDisplay()); //move this method around???? i need to make it so i can initialize with my models;
    studySet.initData(data);
    StudyFiles.loadUserData(data);
    gridPane = new GridPane();
    Scene scene = new Scene(gridPane, 1600, 900);
    Stage stage = new Stage();
    stage.setOnCloseRequest((WindowEvent t) -> {
    StudyFiles.saveUserData(data.formatData()); //save user data on close
    Platform.exit();
    System.exit(0);
    });
    stage.setTitle("Study Program");
    stage.setScene(scene);
    addControls();
    stage.show();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
    startForm();
    }
     */
    //    public void setText(TextField txtFld, String newText) {
    //        Platform.runLater(() -> {
    //            txtFld.setText(newText);
    //        });
    //    }
    
}
