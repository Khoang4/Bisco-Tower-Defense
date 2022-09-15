package com.example.demo;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ConfigController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private Button playButton;

    private ObservableList<String> list = FXCollections.observableArrayList("Easy",
            "Medium", "Hard");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        combobox.setItems(list);
    }
    @FXML
    protected void playButtonClick() throws IOException {
        Stage stage;
        boolean correctName = false;
        boolean correctDifficulty = false;
        String player = name.getText();
        for (int i = 0; i < player.length(); i++) {
            if (player.charAt(i) != ' ')  {
                correctName = true;
                break;
            }
        }
        String difficulty = combobox.getValue();
        if (!Objects.equals(difficulty, "Select Difficulty")) {
            correctDifficulty = true;
        }
        if (!correctName || !correctDifficulty) {
            Alert error = new Alert(Alert.AlertType.ERROR, "Please enter a name and difficulty");
            error.showAndWait();
        } else {
            PlayerInformation playerInfo = new PlayerInformation(player, difficulty);
            stage = (Stage) playButton.getScene().getWindow();
            GameScreen nextScreen = new GameScreen();
            nextScreen.start(stage);
        }

    }
    public ObservableList<String> getList() {
        return this.list;
    }

    public boolean testNameAndDiff(String name, String diff) {
        boolean flag = false;
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) != ' ') {
                flag = true;
                break;
            }
        }
        if (!flag) {
            return false;
        }
        if (diff.equals("")) {
            return false;
        }
        return true;
    }
}
