package application;

import controller.TimerRunner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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
public class StudyDisplay {

    public void updateDisplay() {
    }

//    private GridPane gridPane;
//    private TermData data = new TermData();
//    private StudyDisplayController controller = new StudyDisplayController();
//    private SRS studySet = new SRS();
//    private void keyEvent (KeyEvent evt) {
//        if (evt.getCode() == KeyCode.ENTER) {
//            System.out.println("Enter was pressed");
//        }
//    }
    private Label label(String text) {
        Label label = new Label();
//        label.setLayoutX(0);
//        label.setLayoutY(0);
//        label.setMaxWidth(500);
//        label.setMinHeight(400);
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        label.setText(text);

        return label;
    }

    private TextArea textArea(String text) {
        TextArea textArea = new TextArea();
//        textArea.setLayoutX(0);
//        textArea.setLayoutY(0);
//        textArea.setMaxWidth(450);
//        textArea.setMinHeight(380);
//        textArea.setText("this is a text area");
        textArea.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        textArea.setPromptText(text);
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
//        textField.setLayoutX(0);
//        textField.setLayoutY(1);
//        textField.setMinWidth(450);
//        textField.setMinHeight(25);
        textField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        textField.setText(text);
        textField.setPromptText(text);
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
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

//        button.setPrefSize(200, 200);
        return button;
    }

    private ImageView imageView(String pathToImage) {
        FileInputStream input = null;
        try {
            input = new FileInputStream(pathToImage);
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setVisible(false);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        return imageView;
    }

    private void setConstraints(GridPane gp) {
        gp.getColumnConstraints().clear();
        gp.getRowConstraints().clear();
        for (int j = 0; j < gp.getColumnCount(); j++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setHalignment(HPos.CENTER);
            cc.setPercentWidth(50);
            cc.setFillWidth(false);
//            cc.setHgrow(Priority.SOMETIMES);
            gp.getColumnConstraints().add(cc);
        }
        for (int j = 0; j < gp.getRowCount(); j++) {
            RowConstraints rc = new RowConstraints();
            rc.setValignment(VPos.CENTER);
            rc.setFillHeight(false);
//            rc.setVgrow(Priority.ALWAYS);
            rc.setPercentHeight(50);
            gp.getRowConstraints().add(rc);
        }
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
    public Button valueButton = button("Random Definition"); //corresponds to values - name appearing on button is different for user convenience
    public Button definitionButton = button("Random Term"); //corresponds to definitions
    public Button importDataButton = button("Import word list");
    public Button saveDataButton = button("Save word list");
    public Button startTimerButton = button("Start Timer");
    public Button setDailyReminderButton = button("Set daily reminder time");
    public Button addTermButton = button("Press to add entered term");
    public Button removeTermButton = button("Press to remove selected term");
    public Button createReminderButton = button("Create a reminder");
    public Label questionLabel = label("Questions Appear Here");
    public TextField ansField = textField("Type your answers here");
    public TextField scoreField = textField("Your score shows up here");
    public TextField timerField = textField("type how long the timer should be");
    public TextField dateField = textField("Enter 24H time (HH:MM)");
    public TextField valueField = textField("Enter Definition");//comment below
    public TextField definitionField = textField("Enter Term"); //again confusing naming but refer to above comment
    public ImageView checkMarkImage = imageView(System.getProperty("user.dir") + "/images/check_mark.jpg");
    public ImageView redXImage = imageView(System.getProperty("user.dir") + "/images/red_x.png");

    public GridPane gridPane1 = new GridPane();
    public GridPane gridPane2 = new GridPane();
    public Scene scene1 = new Scene(gridPane1, 800, 800);
    public Scene scene2 = new Scene(gridPane2, 800, 800);
    public Button scene1Button = button("Studying");
    public Button scene2Button = button("Settings");
//    public Button doOCRButton = button("Do OCR");
    public ListView termList = new ListView();
    public Button toggleFullScreenButton = button("Toggle Full Screen");

    public void addControls() {
//        gridPane.getRowConstraints().add(new RowConstraints(100));
//        setAction();
//        gridPane1.setGridLinesVisible(true);
//        gridPane2.setGridLinesVisible(true);

        gridPane1.add(scene2Button, 3, 0);
        gridPane2.add(scene1Button, 3, 0);

        gridPane1.add(valueButton, 0, 0);
//        controller.showValue(valueButton, questionLabel, ansField, studySet);

        gridPane1.add(definitionButton, 1, 0);
//        controller.showDefinition(definitionButton, questionLabel, ansField, studySet);

        gridPane1.add(questionLabel, 0, 1);
        gridPane1.add(ansField, 0, 2);
        gridPane1.add(scoreField, 0, 3);
        gridPane1.add(toggleFullScreenButton, 0, 4);
//        controller.checkAnswerOnPress(questionLabel, ansField, scoreField, studySet);
        gridPane1.add(checkMarkImage, 1, 1);
//        checkMarkImage.fitWidthProperty().bind(gridPane1.widthProperty());
//        checkMarkImage.fitHeightProperty().bind(gridPane1.heightProperty());
        gridPane1.add(redXImage, 2, 1);

        gridPane2.add(importDataButton, 0, 0);
//        controller.importData(importDataButton);

        gridPane2.add(saveDataButton, 0, 1);
//        controller.saveData(saveDataButton);

        gridPane2.add(startTimerButton, 2, 0);
        gridPane2.add(timerField, 2, 1);
        gridPane2.add(setDailyReminderButton, 1, 0);
        gridPane2.add(dateField, 1, 1);
        gridPane2.add(termList, 0, 2, 1, 2);
        gridPane2.add(definitionField, 1, 2);
        gridPane2.add(valueField, 1, 3);
        gridPane2.add(addTermButton, 2, 2);
        gridPane2.add(removeTermButton, 2, 3);
//        gridPane2.add(doOCRButton, 3, 4);
        gridPane2.add(createReminderButton, 3, 1);
//        controller.startTimer(startTimerButton, timerField);

        setConstraints(gridPane1);
        setConstraints(gridPane2);
    }

    public void clearText() {
        questionLabel.setText("");
        ansField.setText("");
    }

    public void hideImages() {
        checkMarkImage.setVisible(false);
        redXImage.setVisible(false);
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

    public void showCreateReminderWindow() {
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
                    LocalDateTime ldt = ld.atTime(
                            Integer.parseInt(time.substring(0, time.indexOf(":"))),
                            Integer.parseInt(time.substring(time.indexOf(":") + 1, time.length())));
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
