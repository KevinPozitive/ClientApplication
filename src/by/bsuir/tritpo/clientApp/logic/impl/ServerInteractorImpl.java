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
    private List<String> messages;
    private List<String> users;
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
                    out.write("onlineUsers\n");
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (true) {
                    String msg;
                    String msg2;
                    if(!socket.isClosed()){
                    try {
                        msg = in.readLine();
                        msg2 = in.readLine();
                        if(msg2==null){
                            try {
                                synchronized (thread) {
                                    thread.wait();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return;
                        }
                        String[] message = msg.split("~");
                        String[] message2 = msg2.split("~");
                        if (message[0].equals("msgHistory")) {
                            index += message.length - 1;
                            messages = new ArrayList<>();
                            for (int i = 1; i < message.length; i++) {
                                messages.add(message[i]);
                            }
                            try {
                                synchronized (thread) {
                                    thread.wait();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if(message2[0].equals("onlineUsers")){
                            users = new ArrayList<>();
                            for(int i = 1;i<message2.length;i++){
                                users.add(message2[i]);
                            }
                            try {
                                synchronized (thread) {
                                    thread.wait();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
        out.write("exit\n");
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

    public void endConversation() throws IOException {
        if(thread.isAlive()) {
            thread.interrupt();
        }
    }
    @Override
    public List<String> getOnlineUsers() {
        while (thread.getState() != Thread.State.WAITING){
        }
        List retVal = users;
        try{
            synchronized (thread){
                thread.notify();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if(!socket.isClosed()) {
                out.write("onlineUsers\n");
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retVal;
    }
    public List<String> getMessages(){
        while (thread.getState() != Thread.State.WAITING){
        }
        List retVal = messages;
        try{
            synchronized (thread){
                thread.notify();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if(!socket.isClosed()) {
                out.write("msgHistory~" + index + "\n");
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retVal;
    }

    public void close() throws IOException {
        try {
            out.write("exit\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        socket.close();
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
