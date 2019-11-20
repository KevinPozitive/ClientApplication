package by.bsuir.tritpo.clientApp.logic;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public interface IServerInteractor {
    void sendMessage(String message) throws IOException;
    void exit() throws IOException;
    boolean registration(String login, String password) throws IOException;
    boolean login(String login, String password) throws IOException;
    String receiveMessage(String message) throws IOException;
    LinkedList<String> receiveNames(String names) throws IOException;
    List<String> getMessages() throws IOException;
    void startConversation();
}
