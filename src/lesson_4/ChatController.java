package lesson_4;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ChatController {
    @FXML
    VBox box;

    @FXML
    ScrollPane scrollPane;

    @FXML
    TextField textField;

    @FXML
    Button btn;

    Boolean answer = false;

    public void sendMsg() {
        String text = textField.getText().trim();
        if(text.length() > 0){
            answer = !answer;
            Label label = new Label(textField.getText());
            label.getStyleClass().add("message");
            if(answer){
                label.getStyleClass().add("answer");
            }
            VBox.setMargin(label, new Insets(5));
            box.getChildren().add(label);

            textField.clear();
            textField.requestFocus();

            scrollPane.vvalueProperty().bind(box.heightProperty());
        }
    }
}
