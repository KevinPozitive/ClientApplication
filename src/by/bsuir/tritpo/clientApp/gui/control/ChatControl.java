package by.bsuir.tritpo.clientApp.gui.control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatControl {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Button exitButton;

    @FXML
    private ListView<?> listView;

    @FXML
    private TextField textField;

    @FXML
    private TextArea showArea;

    @FXML
    private Button sendButton;

    @FXML
    void initialize() {
    }
}
