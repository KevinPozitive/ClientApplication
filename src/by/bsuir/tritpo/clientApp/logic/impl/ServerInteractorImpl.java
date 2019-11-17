package by.bsuir.tritpo.clientApp.logic.impl;

import by.bsuir.tritpo.clientApp.logic.IServerInteractor;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

public class ServerInteractorImpl implements IServerInteractor {
    private Socket socket;
    private BufferedWriter out;
    private BufferedReader in;


    public ServerInteractorImpl(Socket socket) throws IOException {
        this.socket = socket;
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void sendMessage(String message) throws IOException {
        out.write("msg~"+message);
        out.flush();
    }

    @Override
    public boolean registration(String login, String password) throws IOException {
        out.write("reg~"+login+"~"+password);
        out.flush();
        if(in.readLine()=="true"){
            return true;
        }
        return false;
    }

    @Override
    public boolean login(String login, String password) throws IOException {
        out.write("log~"+login+"~"+password);
        out.flush();
        if(in.readLine()=="true"){
            return true;
        }
        return false;
    }



    @Override
    public String receiveMessage () throws IOException {
        String msg = "";
        msg = in.readLine();
        msg.replace("~"," ");
        return msg;
    }

    @Override
    public LinkedList<String> receiveNames() throws IOException {
        String[] msg = in.readLine().split("~");
        LinkedList<String> names = new LinkedList<>();
        for(int i = 0;msg.length<i;i++){
            names.add(msg[i]);
        }
       return names;
    }

    @Override
    public LinkedList<String> receiveHistory() throws IOException {
        String[] msg = in.readLine().split("~");
        LinkedList<String> history = new LinkedList<>();
        for(int i = 0;msg.length<i;i++){
            history.add(msg[i]);
        }
        return history;
    }
}
