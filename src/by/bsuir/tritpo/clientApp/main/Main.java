package by.bsuir.tritpo.clientApp.main;

import by.bsuir.tritpo.clientApp.logic.impl.ServerInteractorImpl;
import by.bsuir.tritpo.clientApp.main.configs.Configs;

public class Main {
    public static void main(String[] args) {
        new ClientConnect(Configs.ipAddr,Configs.PORT);
    }
}
