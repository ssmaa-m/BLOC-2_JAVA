package main;

import domaine.QueryFactoryImpl;
import server.ProxyServer;

public class Main {
    public static void main(String[] args) {

        QueryFactoryImpl queryFactory  = new QueryFactoryImpl();
        ProxyServer proxyServer = new ProxyServer(queryFactory);
        proxyServer.startServer();

    }
}
