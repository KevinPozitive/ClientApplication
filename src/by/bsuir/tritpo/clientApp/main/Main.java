package by.bsuir.tritpo.clientApp.main;

import by.bsuir.tritpo.clientApp.gui.control.Control;
import by.bsuir.tritpo.clientApp.logic.impl.ServerInteractorImpl;
import by.bsuir.tritpo.clientApp.main.configs.Configs;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/fxml/Login.fxml"));
        primaryStage.setTitle("Project");
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.minWidthProperty().setValue(810);
        primaryStage.minHeightProperty().setValue(640);
        new ClientConnect(Configs.ipAddr,Configs.PORT);
        primaryStage.show();
    }
}
