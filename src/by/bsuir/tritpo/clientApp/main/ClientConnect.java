package by.bsuir.tritpo.clientApp.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnect {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public ClientConnect(String addr,int port){
        try {
            this.socket = new Socket(addr,port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
