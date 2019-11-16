package by.bsuir.tritpo.clientApp.logic;

public interface IServerInteractor {
    boolean sendMessage(String message);
    boolean registration(String login, String password);
    boolean login(String login, String password);
    boolean receiveMessage();
    boolean receiveNames();
}
