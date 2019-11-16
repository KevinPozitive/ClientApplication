package by.bsuir.tritpo.clientApp.logic.impl;

import by.bsuir.tritpo.clientApp.logic.IServerInteractor;

public class ServerInteractorImpl implements IServerInteractor {

    public ServerInteractorImpl(String addr, int port) {

    }

    @Override
    public boolean sendMessage(String message) {
        return false;
    }

    @Override
    public boolean registration(String login, String password) {
        return false;
    }

    @Override
    public boolean login(String login, String password) {
        return false;
    }

    @Override
    public boolean receiveMessage() {
        return false;
    }

    @Override
    public boolean receiveNames() {
        return false;
    }
}
