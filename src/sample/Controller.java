package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    @FXML
    private ComboBox<Integer> wXBox;

    @FXML
    private ComboBox<Integer> XwXBox1;

    @FXML
    private ComboBox<Integer> XwXBox2;

    @FXML
    private ComboBox<Integer> XwXXBox1;

    @FXML
    private ComboBox<Integer> XwXXBox2;

    @FXML
    private ComboBox<Integer> XwXXBox3;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private TextField output;

    private final Random random = new Random();

    public Controller() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < 201; i++) {
            ints.add(i);
        }
        XwXXBox3.setItems(FXCollections.observableList(new ArrayList<>(ints)));
        ints.remove(0);
        XwXBox1.setItems(FXCollections.observableList(new ArrayList<>(ints)));
        XwXXBox1.setItems(FXCollections.observableList(new ArrayList<>(ints)));
        ints.remove(0);
        XwXBox2.setItems(FXCollections.observableList(new ArrayList<>(ints)));
        XwXXBox2.setItems(FXCollections.observableList(new ArrayList<>(ints)));
        wXBox.setItems(FXCollections.observableList(new ArrayList<>(ints)));
        wXBox.setOnAction(event -> btn1.setText("W" + wXBox.getSelectionModel().getSelectedItem()));
        XwXBox1.setOnAction(event -> btn2.setText(getText(XwXBox1, XwXBox2)));
        XwXBox2.setOnAction(event -> btn2.setText(getText(XwXBox1, XwXBox2)));
        XwXXBox1.setOnAction(event -> btn3.setText(getText(XwXXBox1, XwXXBox2, XwXXBox3)));
        XwXXBox2.setOnAction(event -> btn3.setText(getText(XwXXBox1, XwXXBox2, XwXXBox3)));
        XwXXBox3.setOnAction(event -> btn3.setText(getText(XwXXBox1, XwXXBox2, XwXXBox3)));
        wXBox.getSelectionModel().select(8);
        XwXBox1.getSelectionModel().select(0);
        XwXBox2.getSelectionModel().select(4);
        XwXXBox1.getSelectionModel().select(0);
        XwXXBox2.getSelectionModel().select(4);
        XwXXBox3.getSelectionModel().select(0);
    }

    @FXML
    public void roll(ActionEvent event) {
        int id = Integer.parseInt(String.valueOf(((Button) event.getSource()).getId().charAt(3)));
        String res = getResult(id);
        output.setText(res);
    }

    private String getResult(int id) {
        String result = "";
        switch (id) {
            case 1 -> result = String.valueOf(random.nextInt(wXBox.getSelectionModel().getSelectedItem()) + 1);
            case 2 -> {
                StringJoiner joiner = new StringJoiner(", ");
                int x = XwXBox1.getSelectionModel().getSelectedItem();
                int total = 0;
                for (int i = 0; i < x; i++) {
                    int tmp = random.nextInt(XwXBox2.getSelectionModel().getSelectedItem()) + 1;
                    total += tmp;
                    joiner.add(String.valueOf(tmp));
                }
                if (x != 1) {
                    result = joiner + " = " + total;
                } else {
                    result += total;
                }
            }
            case 3 -> {
                int x = XwXXBox1.getSelectionModel().getSelectedItem();
                int y = XwXXBox2.getSelectionModel().getSelectedItem();
                int z = XwXXBox3.getSelectionModel().getSelectedItem();
                int res = 0;
                for (int i = 0; i < x; i++) {
                    res += random.nextInt(y) + 1;
                }
                result = String.valueOf((res + z));
            }
        }
        return result;
    }

    @SafeVarargs
    private String getText(ComboBox<Integer>... boxes) {
        String result = "";
        if (boxes.length == 2) {
            result += boxes[0].getSelectionModel().getSelectedItem() + "W" +
                    boxes[1].getSelectionModel().getSelectedItem();
        } else if (boxes.length == 3) {
            result += boxes[0].getSelectionModel().getSelectedItem() + "W" +
                    boxes[1].getSelectionModel().getSelectedItem() + "+" +
                    boxes[2].getSelectionModel().getSelectedItem();
        }
        return result;
    }
}
