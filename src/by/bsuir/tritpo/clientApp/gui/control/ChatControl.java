package by.bsuir.tritpo.clientApp.gui.control;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ChatControl extends Control{
    Thread thread;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Button exitButton;

    @FXML
    private TextArea listView;

    @FXML
    private TextField textField;

    @FXML
    private TextArea showArea;

    @FXML
    private Button sendButton;

    @FXML
    void initialize() throws IOException {

        sendButton.setOnAction(event -> {
            try {
                if(!textField.getText().equals("")) {
                    Control.serverInteractor.sendMessage(textField.getText());
                    textField.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
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
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    List<String> msg = null;
                    List<String> users = null;
                    try {
                        msg = serverInteractor.getMessages();
                        users = serverInteractor.getOnlineUsers();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for(String message:msg){
                        showArea.appendText(message+"\n");
                    }
                    listView.clear();
                    for(String user:users){
                        listView.appendText(user+"\n");
                    }
                }
            }
        });
        thread.start();
    }



}
