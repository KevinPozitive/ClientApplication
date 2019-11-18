package by.bsuir.tritpo.clientApp.gui.control;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import by.bsuir.tritpo.clientApp.gui.animation.Shake;
import by.bsuir.tritpo.clientApp.logic.impl.ServerInteractorImpl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginControl extends Control{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private TextField loginTextField;

    @FXML
    private Label Label;

    @FXML
    void initialize() {
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if(!(loginTextField.getText() ==null) || !(passwordTextField.getText() ==null)){
                        if (serverInteractor.login(loginTextField.getText(), passwordTextField.getText())){
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("../fxml/Chat.fxml"));
                            loader.load();
                            Parent root = loader.getRoot();
                            loginButton.getScene().setRoot(root);
                        }
                        else{
                            Shake userLogAnim = new Shake(loginTextField);
                            Shake userPasAnim = new Shake(passwordTextField);
                            loginTextField.setStyle("-fx-border-color: RED;-fx-border-radius:5");
                            passwordTextField.setStyle("-fx-border-color: RED;-fx-border-radius:5");
                            userLogAnim.playAnim();
                            userPasAnim.playAnim();
                        }
                    }else{
                        Shake userLogAnim = new Shake(loginTextField);
                        Shake userPasAnim = new Shake(passwordTextField);
                        loginTextField.setStyle("-fx-border-color: RED;-fx-border-radius:5");
                        passwordTextField.setStyle("-fx-border-color: RED;-fx-border-radius:5");
                        userLogAnim.playAnim();
                        userPasAnim.playAnim();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../fxml/Register.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                loginButton.getScene().setRoot(root);
            }
        });
    }
}
