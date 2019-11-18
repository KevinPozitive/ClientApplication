package by.bsuir.tritpo.clientApp.gui.control;

import by.bsuir.tritpo.clientApp.logic.IServerInteractor;
import by.bsuir.tritpo.clientApp.logic.impl.ServerInteractorImpl;

import java.net.Socket;

public class Control {
    protected static IServerInteractor serverInteractor;

    public Control() {
    }
    public Control(IServerInteractor iServerInteractor) {
        this.serverInteractor = iServerInteractor;
    }
}
