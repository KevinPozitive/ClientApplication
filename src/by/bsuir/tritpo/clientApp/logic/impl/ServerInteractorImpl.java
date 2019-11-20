package by.bsuir.tritpo.clientApp.logic.impl;

import by.bsuir.tritpo.clientApp.gui.control.ChatControl;
import by.bsuir.tritpo.clientApp.logic.IServerInteractor;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ServerInteractorImpl implements IServerInteractor {
    private Socket socket;
    private BufferedWriter out;
    private BufferedReader in;
    private List<String> result;
    private Thread thread;
    private int index = 0;


    public ServerInteractorImpl(Socket socket) throws IOException {
        this.socket = socket;
        System.out.println(socket);
        out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        System.out.println(in);
        System.out.println(out);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    out.write("msgHistory~"+index);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (true) {
                    String msg;
                    try {
                        msg = in.readLine();
                        String[] message = msg.split("~");
                        switch (message[0]){
                            case "msgHistory":
                                index +=message.length-1;
                                result = new ArrayList<>();
                                for(int i = 1; i < message.length; i++){
                                        result.add(message[i]);
                                }
                                thread.wait();
                                out.write("msgHistory~"+index);
                                break;
                            case "users":
                        }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
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
        System.out.println(str);
        if(str.equals("true")){
            return true;
        }
        return false;
    }

    public void startConversation(){
        if(!thread.isAlive()) {
            thread.start();
        }
    }
    public void endConversation() throws IOException {
        if(thread.isAlive()) {
            thread.interrupt();
        }
    }

    public List<String> getMessages(){
        while (thread.getState() != Thread.State.WAITING);
        List retVal = result;
        thread.notify();
        return retVal;
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
}
