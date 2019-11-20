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
    private List<String> onlineUsers;
    private Thread thread;
    private int index = 0;


    public ServerInteractorImpl(Socket socket) throws IOException {
        this.socket = socket;
        out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    out.write("msgHistory~"+index+"\n");
                    out.flush();
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
                                try{
                                    synchronized (thread){
                                        thread.wait();
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                out.write("msgHistory~"+index+"\n");
                                out.flush();
                                break;
                            case "onlUsers":
                        }
                    } catch (IOException e) {
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

    @Override
    public List<String> getOnlineUsers() {
        return null;
    }

    public void endConversation() throws IOException {
        if(thread.isAlive()) {
            thread.interrupt();
        }
    }

    public List<String> getMessages(){
        while (thread.getState() != Thread.State.WAITING){
        }
        List retVal = result;
        try{
            synchronized (thread){
                thread.notify();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }
}
