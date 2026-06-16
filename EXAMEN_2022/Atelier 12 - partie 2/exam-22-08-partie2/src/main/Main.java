package main;

import domain.Factory;

public class Main {

    public static void main(String[] args) {
        Factory factory = new Factory();
        Server server = new Server(factory);
        server.listenKeyboard();
    }

}
