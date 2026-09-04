package nano.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import nano.Nano;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private Nano nano;

    private Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));

    private Image nanoImage =
            new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Nano instance.
     */
    public void setNano(Nano nano) {
        this.nano = nano;
    }

    /**
     * Processes the user's input and displays Nano's response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = nano.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNanoDialog(response, nanoImage)
        );

        userInput.clear();
    }
}
