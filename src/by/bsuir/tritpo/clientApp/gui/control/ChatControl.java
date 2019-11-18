package by.bsuir.tritpo.clientApp.gui.control;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

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
    void initialize() throws IOException {
       // showHistory();
        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if(!textField.getText().equals("")) {
                        Control.serverInteractor.sendMessage(textField.getText());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Control.serverInteractor.exit();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public void showHistory() throws IOException {
        LinkedList<String> history = new LinkedList<>();
        history = Control.serverInteractor.receiveHistory();
        for(String msgUnit: history){
            if(!(history==null)){
            showArea.setText(msgUnit + "\n");
            }
        }
    }
}
