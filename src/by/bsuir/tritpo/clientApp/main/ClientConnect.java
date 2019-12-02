package by.bsuir.tritpo.clientApp.main;

import by.bsuir.tritpo.clientApp.gui.control.Control;
import by.bsuir.tritpo.clientApp.logic.impl.ServerInteractorImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnect {
    private Socket socket;
    ServerInteractorImpl sI;

    public ClientConnect(String addr, int port) throws IOException {
        try {
            this.socket = new Socket(addr,port);
            sI = new ServerInteractorImpl(socket);
            new Control(sI);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeConnection() {
        try {
            sI.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
