package by.bsuir.tritpo.clientApp.gui.control;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import by.bsuir.tritpo.clientApp.logic.impl.ServerInteractorImpl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatControl extends Control{
    Thread thread;

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
    void initialize() throws IOException, InterruptedException {
       // showHistory();
        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if(!textField.getText().equals("")) {
                        Control.serverInteractor.sendMessage(textField.getText());
                        textField.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Control.serverInteractor.startConversation();
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Control.serverInteractor.exit();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../fxml/Login.fxml"));
                    loader.load();
                    Parent root = loader.getRoot();
                    exitButton.getScene().setRoot(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    List<String> list = null;
                    try {
                        list = serverInteractor.getMessages();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for(String message:list){
                        showArea.appendText(message+"\n");
                    }

                }
            }
        });
        thread.start();
    }
}
