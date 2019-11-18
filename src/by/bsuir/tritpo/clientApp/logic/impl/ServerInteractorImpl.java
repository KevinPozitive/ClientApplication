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
        System.out.println(socket);
        out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        System.out.println(in);
        System.out.println(out);
    }

    @Override
    public void sendMessage(String message) throws IOException {
        out.write("msg~"+ message + "\n");
        out.flush();
    }

    @Override
    public void exit() throws IOException {
        out.write("exit" + "\n");
        out.flush();
    }

    @Override
    public boolean registration(String login, String password) throws IOException {
        out.write("reg~"+login+"~"+password + "\n");
        out.flush();
        String str = in.readLine();
        if(str.equals("true")){
            return true;
        }
        return false;
    }

    @Override
    public boolean login(String login, String password) throws IOException {
        out.write("log~"+login+"~"+password+"\n");
        out.flush();
        String str = in.readLine();
        if(str.equals("true")){
            return true;
        }
        return false;
    }



//////////////////////////Next methods need start at new thread
    ///////////////we will need to know what kind of message we get

    @Override
    public String receiveMessage (String message) throws IOException {
        message.replace("~"," ");
        return message;
    }

    @Override
    public LinkedList<String> receiveNames(String message) throws IOException {
        String msg[] = message.split("~");
        LinkedList<String> names = new LinkedList<>();
        for(int i = 0;msg.length < i;i++){
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
