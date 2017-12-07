package ua.khpi.oop.Rybiezhyn16;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ua.khpi.oop.Rybiezhyn07.CorrectPrint;
import ua.khpi.oop.Rybiezhyn07.Person;
import ua.khpi.oop.Rybiezhyn07.PrisonerInfo;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.LinkedList;

/**
 * Main class that start work
 * and extend class application for creating
 * frame app
 *
 * @author Rubezhin Evgeniy
 * Data 07.12.2017
 * */
public class Main extends Application {
    private ListView<String> list = new ListView<>();// list of commands
    private ScrollBar sc = new ScrollBar();// special scrollbar for scroll frame
    private ObservableList<String> items = FXCollections.observableArrayList(
            "Просмотр инфы","Добавить","Удалить","Сортировать");// parametrizing-list of commands
    private ArrayList<PrisonerInfo> prisoners = new ArrayList<>();// list of prisoners
    private int indexToDelete;// index for deleting elements

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root);

        primaryStage.setTitle("Lab №16");
        primaryStage.setWidth(900);
        primaryStage.setHeight(600);

        list.setPrefHeight(600);
        list.setPrefWidth(150);
        list.setItems(items);

        sc.setMin(0);
        sc.setMax(500);
        sc.setOrientation(Orientation.VERTICAL);
        sc.setPrefHeight(560);
        sc.setTranslateX(870);

        Label label = new Label();
        label.setTranslateX(152);

        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    if (list.getSelectionModel().getSelectedItem().equals("Просмотр инфы")) {
                        printData(label);
                    } else if (list.getSelectionModel().getSelectedItem().equals("Добавить")) {
                        createWindowWithAdding();
                    } else if (list.getSelectionModel().getSelectedItem().equals("Удалить")) {
                        createWindowWithDeleting();
                    } else if (list.getSelectionModel().getSelectedItem().equals("Сортировать")) {
                        if (prisoners != null) {
                            prisoners.sort(new ua.khpi.oop.Rubiezhyn15.Main.myCompare());
                            label.setText("Готово!!!");
                        } else {
                            label.setText("Список пуст!!!");
                        }
                    }
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
            }
        });

        sc.valueProperty().addListener((observable, oldValue, newValue) -> root.setLayoutY(-newValue.doubleValue()));

        root.getChildren().addAll(list, label,sc);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Method that print data from list to label
     * @param label will be have text that will be generated
     * */
    private void printData(Label label){
        StringBuilder text = new StringBuilder("Нечего показать");
        if (prisoners.size() != 0) {
            text.delete(0,text.length());
        }
        for (PrisonerInfo prisonerInfo : prisoners){
            text.append(getTextPrisoner(prisonerInfo)).append("\n");
        }
        label.setText(text.toString());
    }

    /**
     * Method that print data from list to label
     * @param prisoner prisoner that will be splited on special string
     * @return string
     * */
    private String getTextPrisoner(PrisonerInfo prisoner){
        Formatter formatter = new Formatter();
        formatter.format("%s%27s%11s%16s%22s%24s\n","ИФО (или кличка)","День рождения",
                "Рост","Цвет глаз","Дата заключения","Дата освобождения");
        StringBuilder text = new StringBuilder(formatter.toString());

        Person person = prisoner.getPerson();
        CorrectPrint print = new CorrectPrint(new int[]{27,11,16,22,24});
        formatter = new Formatter();
        formatter.format("%s%27s%11s%16s%22s%24s\n",person.getMPIB(),person.getMDateOfBirthd(),
                person.getMGrowth(),person.getMColorEyes(),
                prisoner.getMDateOfGoToJail(),prisoner.getMDateOfGoFromJail());
        text.append(formatter);
        LinkedList<String> list = prisoner.getMListOfSpecialSigns();
        text.append("Особые примечания:\n");
        for (int j = 0; j < list.size(); j++) {
            text.append(j).append(") ").append(list.get(j)).append("\n");
        }
        return text.toString();
    }

    /**
     * Method that create window for deleting
     * object in list, if list don`t has this index
     * method create alert dialog for warn the error
     * */
    private void createWindowWithDeleting() {
        Stage stage = new Stage();
        stage.setTitle("Удалить элемент");
        stage.setHeight(400);
        stage.setWidth(400);

        Pane root = new Pane();
        Scene scene = new Scene(root);

        Button btnOK = new Button();
        TextField editText = new TextField();
        editText.setTranslateX(100);
        editText.setTranslateY(20);
        Label label = new Label();
        label.setTranslateX(70);
        label.setText("Введите индекс элемента для удаления");

        btnOK.setText("Удалить");
        btnOK.setTranslateX(310);
        btnOK.setTranslateY(325);

        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    indexToDelete = Integer.parseInt(editText.getText());
                    try {
                        prisoners.remove(indexToDelete);
                        stage.close();
                    }catch (IndexOutOfBoundsException ex){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Ошибка удаления");
                        alert.setHeaderText("ERROR");
                        alert.setContentText("Нeвозможно удалить по заданному индексу " + indexToDelete);
                        alert.showAndWait();
                    }
                }catch (Exception ex){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("Введено не число");
                    alert.showAndWait();
                }

            }
        });

        root.getChildren().addAll(btnOK,label,editText);
        stage.setScene(scene);

        stage.show();
    }

    /**
     * Method that create window for adding
     * object in list, if object has error
     * method create alert dialog for warn the error
     * */
    private void createWindowWithAdding() {
        Person person = new Person();
        Stage stage = new Stage();
        stage.setTitle("Добавить новый элемент");
        stage.setHeight(400);
        stage.setWidth(400);

        Pane root = new Pane();
        Scene scene = new Scene(root);
        Button btnOK = new Button();
        btnOK.setText("Добавить");
        btnOK.setTranslateX(310);
        btnOK.setTranslateY(325);

        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();
        TextField textField5 = new TextField();
        TextField textField6 = new TextField();
        TextField textField7 = new TextField();

        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    PrisonerInfo prisoner = new PrisonerInfo();
                    person.setMPIB(textField1.getText());
                    person.setMDateOfBirthd(textField2.getText());
                    person.setMGrowth(Float.parseFloat(textField3.getText()));
                    person.setMColorEyes(textField4.getText());
                    prisoner.setPerson(person);
                    prisoner.setMDateOfGoToJail(textField5.getText());
                    prisoner.setMDateOfGoFromJail(textField6.getText());
                    LinkedList<String> signs = new LinkedList<>();
                    signs.add(textField7.getText());
                    prisoner.setMListOfSpecialSigns(signs);
                    prisoners.add(prisoner);
                    stage.close();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка добавления");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("Произошла ошибка добавления элемента");
                    alert.showAndWait();
                }
            }
        });
        Label label = new Label();
        label.setText("ФИО(Кличка):");
        textField1.setTranslateX(125);
        textField1.setPrefWidth(200);

        Label labe2 = new Label();
        double tr1 = label.getTranslateY() + 20;
        labe2.setTranslateY(tr1);
        labe2.setText("День рождения:");
        textField2.setTranslateX(125);
        textField2.setPrefWidth(200);
        textField2.setTranslateY(tr1);

        Label labe3 = new Label();
        double tr2 = labe2.getTranslateY() + 20;
        labe3.setTranslateY(tr2);
        labe3.setText("Рост:");
        textField3.setTranslateX(125);
        textField3.setPrefWidth(200);
        textField3.setTranslateY(tr2);

        Label labe4 = new Label();
        double tr3 = labe3.getTranslateY() + 20;
        labe4.setTranslateY(tr3);
        labe4.setText("Цвет глаз:");
        textField4.setTranslateX(125);
        textField4.setPrefWidth(200);
        textField4.setTranslateY(tr3);

        Label labe5 = new Label();
        double tr4 = labe4.getTranslateY() + 20;
        labe5.setTranslateY(tr4);
        labe5.setText("Дата заключения:");
        textField5.setTranslateX(125);
        textField5.setPrefWidth(200);
        textField5.setTranslateY(tr4);

        Label labe6 = new Label();
        double tr5 = labe5.getTranslateY() + 20;
        labe6.setTranslateY(tr5);
        labe6.setText("Дата освобождения:");
        textField6.setTranslateX(125);
        textField6.setPrefWidth(200);
        textField6.setTranslateY(tr5);

        Label labe7 = new Label();
        double tr6 = labe6.getTranslateY() + 30;
        labe7.setTranslateY(tr6);
        labe7.setText("Особые примечания:");
        textField7.setPrefWidth(380);
        textField7.setPrefHeight(150);
        textField7.setTranslateY(tr6+20);
        textField7.setTranslateX(3);

        root.getChildren().addAll(btnOK,
                label, textField1,
                labe2, textField2,
                labe3, textField3,
                labe4, textField4,
                labe5, textField5,
                labe6, textField6,
                labe7, textField7);
        stage.setScene(scene);
        stage.show();
    }
}
