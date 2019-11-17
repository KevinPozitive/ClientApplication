package by.bsuir.tritpo.clientApp.logic;

import java.io.IOException;
import java.util.LinkedList;

public interface IServerInteractor {
    void sendMessage(String message) throws IOException;
    boolean registration(String login, String password) throws IOException;
    boolean login(String login, String password) throws IOException;
    String receiveMessage() throws IOException;
    LinkedList<String> receiveNames() throws IOException;
    LinkedList<String> receiveHistory() throws IOException;
}
