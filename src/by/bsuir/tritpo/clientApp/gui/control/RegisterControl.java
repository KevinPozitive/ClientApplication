package by.bsuir.tritpo.clientApp.gui.control;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import by.bsuir.tritpo.client.gui.animation.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RegisterControl {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Label errorLabel;

    @FXML
    private TextField replyPasswordTextField;

    @FXML
    private Button createAccountButton;

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
    }
}
