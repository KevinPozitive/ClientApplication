package by.bsuir.tritpo.clientApp.gui.control;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import by.bsuir.tritpo.clientApp.gui.animation.Shake;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RegisterControl extends Control{

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
        createAccountButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if(!loginTextField.getText().equals("")&&!passwordTextField.getText().equals("")&&!replyPasswordTextField.getText().equals("")){
                        if(passwordTextField.getText().equals(replyPasswordTextField.getText())){
                            if(Control.serverInteractor.registration(loginTextField.getText(),passwordTextField.getText())==true){
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("../fxml/Chat.fxml"));
                                loader.load();
                                Parent root = loader.getRoot();
                                createAccountButton.getScene().setRoot(root);
                            }else{
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
                    }else{
                        Shake userRepAnim = new Shake(replyPasswordTextField);
                        Shake userPasAnim = new Shake(passwordTextField);
                        loginTextField.setStyle("-fx-border-color: RED;-fx-border-radius:5");
                        passwordTextField.setStyle("-fx-border-color: RED;-fx-border-radius:5");
                        userRepAnim.playAnim();
                        userPasAnim.playAnim();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../fxml/Login.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                backButton.getScene().setRoot(root);
            }
        });
    }
}
